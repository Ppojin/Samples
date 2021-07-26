echo ">>>>>ppojin-receiver<<<<<"
cd ./ppojin-receiver
./gradlew clean generateProto build -x test

echo ">>>>>ppojin-sender<<<<<"
cd ../ppojin-sender
./gradlew clean generateProto build -x test