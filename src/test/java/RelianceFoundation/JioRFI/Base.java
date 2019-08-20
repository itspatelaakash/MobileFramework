package RelianceFoundation.JioRFI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import RelianceFoundation.JioRFI.Locators.LandingScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class Base extends EnvironmentSetup{

	Properties prop = new Properties();
	FileInputStream fis;
	//Screen classes objects
	public LandingScreen landingScreen;


	@BeforeSuite
	public void appium()throws Exception{
        System.out.println("Before Suit");
		killAppiumServerAtAllPorts();
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
		prop.load(fis);

		if(prop.getProperty("noOfDevices").equals("1")){
			System.out.println("One device connected");
			Thread.sleep(3000);
			startServerT();
		}
		if(prop.getProperty("noOfDevices").equals("4")){
			System.out.println("Four devices connected");
			Thread.sleep(3000);
			startServerO();
			Thread.sleep(3000);
			startServerT();
			Thread.sleep(3000);
			startServerB();
			Thread.sleep(3000);
			startServerC();
			Thread.sleep(3000);
		}

	}
	@Parameters({ "port","udid" })
	@BeforeClass
	public void initializer(String port,String udid) throws Exception{
		System.out.println("Before Test");

	/*	if(prop.getProperty("noOfDevices").equals(1)){
			System.out.println("One device");
		}
		if(prop.getProperty("noOfDevices").equals(4)){
			System.out.println("Four device");

		}*/
		driver=setup(port,udid);
		landingScreen = new LandingScreen(driver);
	}


	/*public void killAppiumServerAtAllPorts() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/


	/*public AndroidDriver<AndroidElement> setup()throws Exception{
		killAppiumServerAtAllPorts();
		startServerOnPortOne();
		startServerOnPortTwo();
		*//*startServerO();
		startServerT();*//*
		//startServer();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Device");
		cap.setCapability("platformName", "Android");
		//cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		//cap.setCapability(MobileCapabilityType.AUTO_WEBVIEW,true);
		//cap.setCapability("autoWebviewTimeout", "6000");
		cap.setCapability("autoGrantPermissions", "true");
		//cap.setCapability("noReset", true);
		cap.setCapability("appPackage", "com.rfh.vaccination");
		cap.setCapability("appActivity", "com.rfh.vaccination.tutorial.ui.TutorialActivity");
		cap.setCapability("skipDeviceInitialization", true);
		//cap.setCapability("automationName", "UiAutomator2");
		//cap.setCapability("skipServerInstallation", true);

		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:1234/wd/hub"), cap);
		//driver = (AndroidDriver) driver2;
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		return driver;

	}*/

	@AfterSuite
	public void tearDown() {
		System.out.println("After suite");
		/*driver.quit();
		killAppiumServerAtAllPorts();*/
		//service.stop();

	}

	/*//Starting appium server
	public static AppiumDriverLocalService service;
	public void startServer(){
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}

	public  void scrollToText(String text){
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
	}

	public void startServerOnPortOne() {
        //Set Capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");

        //Build the Appium service

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(1234);
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
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 1234 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void startServerT() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.2 -p 3456 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}*/

	public  String screenShotName;
	public  String screenShotpath;
	//This method takes screen shots
	public void takeScreenShot(String methodName,AndroidDriver driver) throws IOException {

		System.out.println("driver in screenshot method"+driver);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenShotName =d.toString().replace(":","_").replace(" ","_")+"_"+methodName+".jpg";
		screenShotpath = System.getProperty("user.dir")+"\\src\\test\\resources\\logs\\ScreenShots\\"+screenShotName;

		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\src\\test\\resources\\logs\\ScreenShots\\"+screenShotName));

	}

}
