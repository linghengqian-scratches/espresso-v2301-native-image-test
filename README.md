# espresso-v2301-native-image-test

- For https://github.com/apache/shardingsphere/pull/27487 to test

```shell
sudo apt install unzip zip curl sed -y
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.7-graalce
sudo apt-get install build-essential libz-dev zlib1g-dev -y
gu install espresso

git clone git@github.com:linghengqian/espresso-v2301-native-image-test.git
cd ./espresso-v2301-native-image-test/
./mvnw clean test
./mvnw -Pnative clean test
```