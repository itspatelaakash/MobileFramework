package RelianceFoundation.JioRFI;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.ExcelReader;
import utilities.ExtentManager;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class EnvironmentSetup {
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\RF_DataProvider.xlsx");
    //ExtentReport intances
    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;

    public AndroidDriver driver;

    public AndroidDriver<AndroidElement> setup(String port,String udid)throws Exception{

        Thread.sleep(5000);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", "Android Device");
        cap.setCapability("platformName", "Android");
        cap.setCapability(MobileCapabilityType.UDID,udid);
        //cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //cap.setCapability(MobileCapabilityType.AUTO_WEBVIEW,true);
        //cap.setCapability("autoWebviewTimeout", "6000");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("noReset", false);
        cap.setCapability("appPackage", "");
        cap.setCapability("appActivity", "");
        //cap.setCapability("skipDeviceInitialization", true);
        /*cap.setCapability("automationName", "UiAutomator2");*/
        //cap.setCapability("skipServerInstallation", true);

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }

    //Starting appium server
    public static AppiumDriverLocalService service;
    public void startServer(){
        service=AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    public  void scrollToText(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }

    public void startServerOnPortOne(String port) {

        int intPort = Integer.parseInt(port);
        //Set Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(intPort);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

    public void startServerOnPortTwo() {
        //Set Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.2");
        builder.usingPort(3456);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }

    public void startServerO() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 -bp 2345 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\" --suppress-adb-kill-server");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void startServerT() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4727 -bp 4567 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\" --suppress-adb-kill-server");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void startServerC() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4827 -bp 6789 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\" --suppress-adb-kill-server");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void startServerB() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4927 -bp 5678 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\" --suppress-adb-kill-server");
            Thread.sleep(10000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void killAppiumServerAtAllPorts() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("taskkill /F /IM node.exe");
            runtime.exec("taskkill /F /IM cmd.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
