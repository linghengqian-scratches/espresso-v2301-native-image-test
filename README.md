# espresso-v2301-native-image-test

- For https://github.com/apache/shardingsphere/pull/27487 to test.
- Under Ubuntu 22.04 LTS, perform the following steps to complete the environment configuration and unit testing.

```shell
sudo apt install unzip zip curl sed -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.8-graalce
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
linghengqian@DESKTOP-PNK8EKR:~/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test$ ./mvnw -PgenerateMetadata clean test native:metadata-copy
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
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.215 s - in com.lingh.EspressoInlineExpressionParserTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- native-maven-plugin:0.9.23:merge-agent-files (test-native) @ espresso-v2301-native-image-test ---
[INFO] Merging agent 1 files into /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test
[INFO] 
[INFO] --- native-maven-plugin:0.9.23:test (test-native) @ espresso-v2301-native-image-test ---
[INFO] ====================
[INFO] Initializing project: espresso-v2301-native-image-test
[INFO] ====================
[WARNING] Properties file at 'jar:file:///home/linghengqian/.m2/repository/org/graalvm/truffle/truffle-api/23.0.1/truffle-api-23.0.1.jar!/META-INF/native-image/org.graalvm.shadowed.org.jcodings/native-image.properties' does not match the recommended 'META-INF/native-image/org.graalvm.truffle/truffle-api/native-image.properties' layout.
[WARNING] Properties file at 'jar:file:///home/linghengqian/.m2/repository/org/graalvm/sdk/graal-sdk/23.0.1/graal-sdk-23.0.1.jar!/META-INF/native-image/org.graalvm.polyglot/native-image.properties' does not match the recommended 'META-INF/native-image/org.graalvm.sdk/graal-sdk/native-image.properties' layout.
[WARNING] Properties file at 'jar:file:///home/linghengqian/.m2/repository/org/graalvm/sdk/graal-sdk/23.0.1/graal-sdk-23.0.1.jar!/META-INF/native-image/org.graalvm.home/native-image.properties' does not match the recommended 'META-INF/native-image/org.graalvm.sdk/graal-sdk/native-image.properties' layout.
[INFO] Executing: /home/linghengqian/.sdkman/candidates/java/current/bin/native-image -cp /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/classes:/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/test-classes:/home/linghengqian/.m2/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/home/linghengqian/.m2/repository/org/hamcrest/hamcrest/2.2/hamcrest-2.2.jar:/home/linghengqian/.m2/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/home/linghengqian/.m2/repository/org/apache/groovy/groovy/4.0.10/groovy-4.0.10.jar:/home/linghengqian/.m2/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/home/linghengqian/.m2/repository/org/graalvm/truffle/truffle-api/23.0.1/truffle-api-23.0.1.jar:/home/linghengqian/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-engine/1.9.2/junit-platform-engine-1.9.2.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-commons/1.9.2/junit-platform-commons-1.9.2.jar:/home/linghengqian/.m2/repository/com/google/errorprone/error_prone_annotations/2.3.4/error_prone_annotations-2.3.4.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter/5.9.2/junit-jupiter-5.9.2.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.9.2/junit-jupiter-engine-5.9.2.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.9.2/junit-jupiter-params-5.9.2.jar:/home/linghengqian/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/home/linghengqian/.m2/repository/com/google/guava/guava/30.0-jre/guava-30.0-jre.jar:/home/linghengqian/.m2/repository/org/checkerframework/checker-qual/3.5.0/checker-qual-3.5.0.jar:/home/linghengqian/.m2/repository/com/google/j2objc/j2objc-annotations/1.3/j2objc-annotations-1.3.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.9.2/junit-jupiter-api-5.9.2.jar:/home/linghengqian/.m2/repository/org/graalvm/sdk/graal-sdk/23.0.1/graal-sdk-23.0.1.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/native-maven-plugin/0.9.23/native-maven-plugin-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/junit-platform-native/0.9.23/junit-platform-native-0.9.23.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-console/1.8.1/junit-platform-console-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-reporting/1.8.1/junit-platform-reporting-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-launcher/1.8.1/junit-platform-launcher-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-engine/1.8.1/junit-platform-engine-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-commons/1.8.1/junit-platform-commons-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter/5.8.1/junit-jupiter-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.8.1/junit-jupiter-api-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.8.1/junit-jupiter-params-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.8.1/junit-jupiter-engine-5.8.1.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/utils/0.9.23/utils-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/graalvm-reachability-metadata/0.9.23/graalvm-reachability-metadata-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/junit-platform-native/0.9.23/junit-platform-native-0.9.23.jar --no-fallback -H:Path=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target -H:Name=native-tests -Djunit.platform.listeners.uid.tracking.output.dir=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/test-ids -H:Class=org.graalvm.junit.platform.NativeImageJUnitLauncher --features=org.graalvm.junit.platform.JUnitPlatformFeature -H:ConfigurationFileDirectories=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test -H:+AllowIncompleteClasspath
Error: Class-path entry file:///home/linghengqian/.m2/repository/org/graalvm/sdk/graal-sdk/23.0.1/graal-sdk-23.0.1.jar contains class com.oracle.svm.core.annotate.TargetElement. This class is part of the image builder itself (in jrt:/org.graalvm.sdk) and must not be passed via -cp. This can be caused by a fat-jar that illegally includes svm.jar (or graal-sdk.jar) due to its build-time dependency on it. As a workaround, -H:+AllowDeprecatedBuilderClassesOnImageClasspath allows turning this error into a warning. Note that this option is deprecated and will be removed in a future version.
Error: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  8.138 s
[INFO] Finished at: 2023-08-09T00:59:16+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.graalvm.buildtools:native-maven-plugin:0.9.23:test (test-native) on project espresso-v2301-native-image-test: Execution of /home/linghengqian/.sdkman/candidates/java/current/bin/native-image -cp /home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/classes:/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/test-classes:/home/linghengqian/.m2/repository/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/home/linghengqian/.m2/repository/org/hamcrest/hamcrest/2.2/hamcrest-2.2.jar:/home/linghengqian/.m2/repository/com/google/guava/failureaccess/1.0.1/failureaccess-1.0.1.jar:/home/linghengqian/.m2/repository/org/apache/groovy/groovy/4.0.10/groovy-4.0.10.jar:/home/linghengqian/.m2/repository/com/google/guava/listenablefuture/9999.0-empty-to-avoid-conflict-with-guava/listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar:/home/linghengqian/.m2/repository/org/graalvm/truffle/truffle-api/23.0.1/truffle-api-23.0.1.jar:/home/linghengqian/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-engine/1.9.2/junit-platform-engine-1.9.2.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-commons/1.9.2/junit-platform-commons-1.9.2.jar:/home/linghengqian/.m2/repository/com/google/errorprone/error_prone_annotations/2.3.4/error_prone_annotations-2.3.4.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter/5.9.2/junit-jupiter-5.9.2.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.9.2/junit-jupiter-engine-5.9.2.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.9.2/junit-jupiter-params-5.9.2.jar:/home/linghengqian/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/home/linghengqian/.m2/repository/com/google/guava/guava/30.0-jre/guava-30.0-jre.jar:/home/linghengqian/.m2/repository/org/checkerframework/checker-qual/3.5.0/checker-qual-3.5.0.jar:/home/linghengqian/.m2/repository/com/google/j2objc/j2objc-annotations/1.3/j2objc-annotations-1.3.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.9.2/junit-jupiter-api-5.9.2.jar:/home/linghengqian/.m2/repository/org/graalvm/sdk/graal-sdk/23.0.1/graal-sdk-23.0.1.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/native-maven-plugin/0.9.23/native-maven-plugin-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/junit-platform-native/0.9.23/junit-platform-native-0.9.23.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-console/1.8.1/junit-platform-console-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-reporting/1.8.1/junit-platform-reporting-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-launcher/1.8.1/junit-platform-launcher-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-engine/1.8.1/junit-platform-engine-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/platform/junit-platform-commons/1.8.1/junit-platform-commons-1.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter/5.8.1/junit-jupiter-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.8.1/junit-jupiter-api-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.8.1/junit-jupiter-params-5.8.1.jar:/home/linghengqian/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.8.1/junit-jupiter-engine-5.8.1.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/utils/0.9.23/utils-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/graalvm-reachability-metadata/0.9.23/graalvm-reachability-metadata-0.9.23.jar:/home/linghengqian/.m2/repository/org/graalvm/buildtools/junit-platform-native/0.9.23/junit-platform-native-0.9.23.jar --no-fallback -H:Path=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target -H:Name=native-tests -Djunit.platform.listeners.uid.tracking.output.dir=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/test-ids -H:Class=org.graalvm.junit.platform.NativeImageJUnitLauncher --features=org.graalvm.junit.platform.JUnitPlatformFeature -H:ConfigurationFileDirectories=/home/linghengqian/TwinklingLiftWorks/git/public/espresso-v2301-native-image-test/target/native/agent-output/test -H:+AllowIncompleteClasspath returned non-zero result -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
```
</details>
