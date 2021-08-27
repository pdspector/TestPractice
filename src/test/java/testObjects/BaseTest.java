package testObjects;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.LinkedHashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import resources.BaseMethods;
import resources.BaseProperties;
import resources.android.AndroidBaseMethods;

public class BaseTest {

    public static boolean isIos = false;
    public static String appPath = "";
    protected static AppiumDriver<?> driver;
    private static AppiumDriverLocalService service;
    protected File app = null;
    protected AndroidBaseMethods androidBase;
    protected BaseMethods base = new BaseMethods();
    protected BaseProperties baseProperties;
    private LinkedHashMap<String, String> caps = new LinkedHashMap<String, String>();
    public static String suiteName = "";
    
    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            // If control comes here, then it means that the port is in use
            e.getMessage();
            isServerRunning = true;

        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
    
    @Parameters({"platform"})
    @BeforeSuite(alwaysRun= true)
    public void startDriver(String context, ITestContext context2) throws IOException, InterruptedException {
        boolean flag = checkIfServerIsRunnning(4723);
        baseProperties = new BaseProperties();
        System.out.println("The platform is... " + context);
        caps.put(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "120");
        if (context.equals("ios")) {
            BaseTest.isIos = true;
            app = new File(baseProperties.getValue("IOS_IPA_PATH"));
        } else if (context.equals("android")) {
            BaseTest.isIos = false;
            app = new File(baseProperties.getValue("ANDROID_APK_PATH"));
        }
        if (!app.getAbsolutePath().contains("private") && !app.getAbsolutePath().contains("tmp") && BaseTest.isIos == true) {
            if (!flag) {
                service = AppiumDriverLocalService.buildDefaultService();
                service.start();
            }
            app = new File(baseProperties.getValue("IOS_IPA_PATH"));

            boolean ios_def_app = Boolean.parseBoolean(baseProperties.getValue("IOS_DEFAULT_APP"));
            String ios_dev_name = "";
            String ios_app_path = "";
            if (ios_def_app) {
                ios_dev_name = baseProperties.getValue("IOS_DEVICE_NAME");
                ios_app_path = baseProperties.getValue("IOS_APP");
            } else { //Image Processing Nick's POC
                ios_dev_name = baseProperties.getValue("IOS_IMAGE_PROCESSING_DEVICE_NAME");
                ios_app_path = baseProperties.getValue("IOS_IMAGE_PROCESSING_APP");
            }

            caps.put(MobileCapabilityType.DEVICE_NAME, ios_dev_name);
            caps.put(MobileCapabilityType.PLATFORM_NAME, baseProperties.getValue("IOS_PLATFORM_NAME"));
            caps.put(MobileCapabilityType.PLATFORM_VERSION, baseProperties.getValue("IOS_PLATFORM_VERSION"));
            caps.put(MobileCapabilityType.AUTOMATION_NAME, baseProperties.getValue("IOS_AUTOMATION_NAME"));
            caps.put(MobileCapabilityType.APP, ios_app_path);
        } else if (!app.getAbsolutePath().contains("private") && !app.getAbsolutePath().contains("tmp") && BaseTest.isIos == false) {
            if (!flag) {
                service = AppiumDriverLocalService.buildDefaultService();
                service.start();
            }
            app = new File(baseProperties.getValue("ANDROID_APK_PATH"));
            caps.put(MobileCapabilityType.DEVICE_NAME, baseProperties.getValue("ANDROID_DEVICE_NAME"));
            caps.put(MobileCapabilityType.PLATFORM_NAME, baseProperties.getValue("ANDROID_PLATFORM_NAME"));
            BaseTest.appPath = app.getAbsolutePath();
            caps.put(MobileCapabilityType.APP, BaseTest.appPath);
        }
        if (BaseTest.isIos == true) {

            try {
                caps.put("noReset", "true");
                caps.put(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true");
                driver = new IOSDriver<>(new URL(baseProperties.getValue("APPIUM_HUB")), base.getCapabilities(caps));
            } catch (Exception e) {
                throw new RuntimeException(base.getStackTraceAsString(e));
            }

        } else if (BaseTest.isIos == false) {

            try {
                caps.put("fullReset", "true");
                //caps.put(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, "60000");
                caps.put(AndroidMobileCapabilityType.DONT_STOP_APP_ON_RESET, "false");
                caps.put("--session-override", "true");
                DesiredCapabilities androidCaps = new DesiredCapabilities();
                Object currentCaps = base.getCapabilities(caps);
                androidCaps = (DesiredCapabilities) currentCaps;
                androidCaps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                driver = new AndroidDriver<>(new URL(baseProperties.getValue("APPIUM_HUB")), androidCaps);
            } catch (Exception e) {
                throw new RuntimeException(base.getStackTraceAsString(e));
            }
            try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
	            suiteName = context2.getCurrentXmlTest().getSuite().getName();
            } catch (Exception e) {
            	throw new RuntimeException(base.getStackTraceAsString(e));
            }
        }
    }
    
    protected void restartIfFailed() {
    	driver.closeApp();
    	driver.launchApp();
    }
	
	public LinkedHashMap<String, String> getCaps() {
		return this.caps;
	}
	
 	@AfterMethod
    public void afterMethod(ITestResult iTestResult) throws Exception {
    	if (iTestResult.getStatus() != 1) {
	        String filename = iTestResult.getMethod().getMethodName();
	        if (iTestResult.getStatus() == 3) {
	        	filename = filename + "Skipped";
	        }
	        base.getScreenshot(driver, filename);
    	}
    }
    
    @AfterSuite(alwaysRun= true)
    public void tearDown() {
    	driver.closeApp();
    }

}
