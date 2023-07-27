# espresso-v2301-native-image-test

- For https://github.com/apache/shardingsphere/pull/27487 to test.
- Under Ubuntu 22.04 LTS, perform the following steps to complete the environment configuration and unit testing.

```shell
sudo apt install unzip zip curl sed -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.7-graalce
sudo apt-get install build-essential libz-dev zlib1g-dev -y
gu install espresso

git clone git@github.com:linghengqian/espresso-v2301-native-image-test.git
cd ./espresso-v2301-native-image-test/
./mvnw -PgenerateMetadata clean test native:metadata-copy
./mvnw -Pnative clean test
```

- Error Log appears when `./mvnw -PgenerateMetadata clean test native:metadata-copy`.
<details>
<summary>Error Log</summary>

```shell
$ ./mvnw -PgenerateMetadata clean test native:metadata-copy
[INFO] Scanning for projects...
[INFO] Found GraalVM installation from JAVA_HOME variable.
[INFO] 
[INFO] -------------< com.lingh:espresso-v2301-native-image-test >-------------
[INFO] Building espresso-v2301-native-image-test 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ espresso-v2301-native-image-test ---
[INFO] Deleting /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ espresso-v2301-native-image-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ espresso-v2301-native-image-test ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ espresso-v2301-native-image-test ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ espresso-v2301-native-image-test ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/test-classes
[INFO] 
[INFO] --- maven-dependency-plugin:3.5.0:copy (copy) @ espresso-v2301-native-image-test ---
[INFO] Configured Artifact: org.apache.groovy:groovy:4.0.10:jar
[INFO] Copying groovy-4.0.10.jar to /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/classes/espresso-need-libs/groovy.jar
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0:test (default-test) @ espresso-v2301-native-image-test ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.lingh.EspressoInlineExpressionParserTest
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 970.804 s - in com.lingh.EspressoInlineExpressionParserTest
[ERROR] Surefire is going to kill self fork JVM. The exit has elapsed 30 seconds after System.exit(0).
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 11, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- native-maven-plugin:0.9.23:merge-agent-files (test-native) @ espresso-v2301-native-image-test ---
[INFO] Merging agent 1 files into /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test
java.lang.OutOfMemoryError: Java heap space
        at java.base/java.util.Arrays.copyOf(Arrays.java:3537)
        at java.base/java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:228)
        at java.base/java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:740)
        at java.base/java.lang.StringBuilder.append(StringBuilder.java:233)
        at jdk.internal.vm.compiler/org.graalvm.util.json.JSONParser.readFully(JSONParser.java:483)
        at jdk.internal.vm.compiler/org.graalvm.util.json.JSONParser.<init>(JSONParser.java:56)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.configure.ConfigurationParser.parseAndRegister(ConfigurationParser.java:72)
        at org.graalvm.nativeimage.configure/com.oracle.svm.configure.command.ConfigurationGenerateConditionalsCommand.apply(ConfigurationGenerateConditionalsCommand.java:119)
        at org.graalvm.nativeimage.configure/com.oracle.svm.configure.ConfigurationTool.main(ConfigurationTool.java:84)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  19:42 min
[INFO] Finished at: 2023-07-27T15:59:24+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.graalvm.buildtools:native-maven-plugin:0.9.23:merge-agent-files (test-native) on project espresso-v2301-native-image-test: Execution of /home/linghengqian/.sdkman/candidates/java/current/bin/native-image-configure generate-conditional --user-code-filter=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/user-code-filter.json --input-dir=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test/session-135354-20230727T073945Z --output-dir=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test returned non-zero result -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
```
</details>
