# TestPractice
AppiumJavaTestNGExample

# Setup
Software:
Maven 3.6.3 version
Java JDK 1.8 version
NPM 6.14.4 version
npm module appium 1.21.0 version

Edit config.properties file in src/test/java/resources package and change the LOCAL_PATH:private with your username like: LOCAL_PATH:Username

# To Run Tests Start Appium Server and use: "mvn clean test" command while standing inside the project or by using your IDE's Maven Test command shortcut

# To Package Tests for AWS use: "mvn clean package -DskipTests=true" command
This will generate a zip-with-dependencies.zip file at /target/test-output/ to be used against AWS DeviceFarm
AWS DeviceFarm testSpec for Android Java TestNG Appium is at /awsDeviceFarmTestSpecAndroidSmoke.yml
APK to use in the practice is at /me.wolszon.fastshopping_23.apk

# Report Generated at /target/test-output/AutomationReport.html
