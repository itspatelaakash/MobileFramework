package RelianceFoundation.JioRFI.AutomationScripts;

import RelianceFoundation.JioRFI.Base;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.SkipException;
import org.testng.annotations.Test;


import RelianceFoundation.JioRFI.Locators.LandingScreen;
import utilities.TestUtil;

import java.util.Iterator;
import java.util.Set;

public class landingTest extends Base {

	@Test(dataProviderClass=TestUtil.class,dataProvider="TestDP")
	public void appTest(String mobile,String password) throws InterruptedException {

		if(!(TestUtil.isTestRunnable("appTest", excel))){
			throw new SkipException("Skipping the test "+"appTest".toUpperCase()+ "as the Run mode is NO");
		}

		System.out.println("Test");
		landingScreen.getNext().click();

		Thread.sleep(5000);
		landingScreen.getLoginButton().click();
		landingScreen.getMobileNo().sendKeys(mobile);
		landingScreen.getPassword().sendKeys(password);
		landingScreen.getLogin().click();
		Thread.sleep(3000);
		landingScreen.getMyRecords().click();
		int i =50;
		while(i>0){
			landingScreen.getUserProfile().click();
			Thread.sleep(2000);
		}
		test.log(LogStatus.INFO,"Printing Logs");
	}

}
