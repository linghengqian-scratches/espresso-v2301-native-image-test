package com.lingh;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import groovy.lang.GroovyShell;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Espresso inline expression parser.
 */
public final class EspressoInlineExpressionParser {
    
    private static final String JAVA_CLASSPATH;
    
    private static final Context ESPRESSO_CONTEXT;
    
    private static final char SPLITTER = ',';
    
    private static final Map<String, Value> SCRIPTS = new ConcurrentHashMap<>();
    
    static {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("espresso-need-libs");
        String dir = null != resource ? resource.getPath() : null;
        JAVA_CLASSPATH = dir + File.separator + "groovy.jar";
        ESPRESSO_CONTEXT = createContext();
    }

    public String handlePlaceHolder(final String inlineExpression) {
        return inlineExpression.contains("$->{") ? inlineExpression.replaceAll("\\$->\\{", "\\$\\{") : inlineExpression;
    }

    public List<String> splitAndEvaluate(final String inlineExpression) {
        return Strings.isNullOrEmpty(inlineExpression) ? Collections.emptyList() : flatten(evaluate(split(inlineExpression)));
    }
    
    private static Context createContext() {
        // TODO https://github.com/oracle/graal/issues/4555 not yet closed
        return Context.newBuilder()
                .allowAllAccess(true)
                .option("java.Properties.org.graalvm.home", System.getenv("JAVA_HOME"))
                .option("java.Classpath", JAVA_CLASSPATH)
                .build();
    }
    
    private List<String> split(final String inlineExpression) {
        List<String> result = new ArrayList<>();
        StringBuilder segment = new StringBuilder();
        int bracketsDepth = 0;
        for (int i = 0; i < inlineExpression.length(); i++) {
            char each = inlineExpression.charAt(i);
            switch (each) {
                case SPLITTER:
                    if (bracketsDepth > 0) {
                        segment.append(each);
                    } else {
                        result.add(segment.toString().trim());
                        segment.setLength(0);
                    }
                    break;
                case '$':
                    if ('{' == inlineExpression.charAt(i + 1)) {
                        bracketsDepth++;
                    }
                    if ("->{".equals(inlineExpression.substring(i + 1, i + 4))) {
                        bracketsDepth++;
                    }
                    segment.append(each);
                    break;
                case '}':
                    if (bracketsDepth > 0) {
                        bracketsDepth--;
                    }
                    segment.append(each);
                    break;
                default:
                    segment.append(each);
                    break;
            }
        }
        if (segment.length() > 0) {
            result.add(segment.toString().trim());
        }
        return result;
    }
    
    private List<Value> evaluate(final List<String> inlineExpressions) {
        List<Value> result = new ArrayList<>(inlineExpressions.size());
        for (String each : inlineExpressions) {
            StringBuilder expression = new StringBuilder(handlePlaceHolder(each));
            if (!each.startsWith("\"")) {
                expression.insert(0, '"');
            }
            if (!each.endsWith("\"")) {
                expression.append('"');
            }
            result.add(evaluate(expression.toString()));
        }
        return result;
    }
    
    private Value evaluate(final String expression) {
        Value script;
        if (SCRIPTS.containsKey(expression)) {
            script = SCRIPTS.get(expression);
        } else {
            script = ESPRESSO_CONTEXT.getBindings("java")
                    .getMember(GroovyShell.class.getName())
                    .newInstance()
                    .invokeMember("parse", expression);
            SCRIPTS.put(expression, script);
        }
        return script.invokeMember("run");
    }
    
    private List<String> flatten(final List<Value> segments) {
        List<String> result = new ArrayList<>();
        for (Value each : segments) {
            if (!each.isString()) {
                result.addAll(assemblyCartesianSegments(each));
            } else {
                result.add(each.toString());
            }
        }
        return result;
    }
    
    private List<String> assemblyCartesianSegments(final Value segment) {
        Set<List<String>> cartesianValues = getCartesianValues(segment);
        List<String> result = new ArrayList<>(cartesianValues.size());
        for (List<String> each : cartesianValues) {
            result.add(assemblySegment(each, segment));
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    private Set<List<String>> getCartesianValues(final Value segment) {
        Object[] temp = segment.invokeMember("getValues").as(Object[].class);
        List<Set<String>> result = new ArrayList<>(temp.length);
        for (Object each : temp) {
            if (null == each) {
                continue;
            }
            if (each instanceof Collection) {
                result.add(((Collection<Object>) each).stream().map(Object::toString).collect(Collectors.toCollection(LinkedHashSet::new)));
            } else {
                result.add(Sets.newHashSet(each.toString()));
            }
        }
        return Sets.cartesianProduct(result);
    }
    
    private String assemblySegment(final List<String> cartesianValue, final Value segment) {
        String[] temp = segment.invokeMember("getStrings").as(String[].class);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < temp.length; i++) {
            result.append(temp[i]);
            if (i < cartesianValue.size()) {
                result.append(cartesianValue.get(i));
            }
        }
        return result.toString();
    }

    public String getType() {
        return "ESPRESSO";
    }
}
