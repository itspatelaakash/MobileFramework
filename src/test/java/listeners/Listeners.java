package listeners;

import RelianceFoundation.JioRFI.Base;
import RelianceFoundation.JioRFI.EnvironmentSetup;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtil;

import java.io.IOException;


public class Listeners extends Base implements ITestListener {



    @Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("On Test Start"+result.getName());
		test=rep.startTest(result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("On Test Success"+result.getName());
		
	    test.log(LogStatus.PASS, result.getName()+" PASSED");
		rep.endTest(test);
		rep.flush();

	}

	@Override
	public void onTestFailure(ITestResult result) {

		Reporter.log("Capturing Screenshot on Failure");
		System.out.println("Test Fail :"+" "+result.getName());
		Object currentClass = result.getInstance();
		AndroidDriver webDriver = ((EnvironmentSetup) currentClass).driver;
		
		try {
			System.out.println("Taking Screenshot...");
			takeScreenShot(result.getName(),webDriver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*//Adding screen shot in ReportNG report
		Reporter.log("<a target=\"_blank\" href="+screenShotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+screenShotName+"><img src="+screenShotName+" height=200 width=200></img></a>");*/
	  
		//Adding ExtentReport
		test.log(LogStatus.FAIL, result.getName()+"FAILED with exception"+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(screenShotpath));
		rep.endTest(test);
		rep.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipping the test as the Run mode is NO");
		rep.endTest(test);
		rep.flush();
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
