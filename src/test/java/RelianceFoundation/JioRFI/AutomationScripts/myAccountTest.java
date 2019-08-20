package RelianceFoundation.JioRFI.AutomationScripts;

import RelianceFoundation.JioRFI.Base;
import RelianceFoundation.JioRFI.Locators.LandingScreen;
import com.relevantcodes.extentreports.LogStatus;
import org.springframework.context.annotation.DependsOn;
import org.testng.SkipException;
import org.testng.annotations.Test;
import utilities.TestUtil;

public class myAccountTest extends Base {

    @Test
    public void accountTest() throws InterruptedException {

        if(!(TestUtil.isTestRunnable("accountTest", excel))){
            throw new SkipException("Skipping the test "+"appTest".toUpperCase()+ "as the Run mode is NO");
        }

        System.out.println("Test 2");
        landingScreen.getNext().click();
        Thread.sleep(5000);
        landingScreen.getLoginButton().click();
        landingScreen.getMobileNo().sendKeys("7021901104");
        landingScreen.getPassword().sendKeys("Healthhub@123");
        landingScreen.getLogin().click();
        test.log(LogStatus.INFO,"Printing Logs");
        landingScreen.getMyRecords().click();
        int i =50;
        while(i>0){
            landingScreen.getUserProfile().click();
            Thread.sleep(2000);
        }

    }

    @Test
    public void afterAccountTestB() throws InterruptedException {
        System.out.println("Depends on methods works");
        /*LandingScreen.getMyAccount().click();
        Thread.sleep(5000);*/
    }

    @Test(dependsOnMethods = {"accountTest"})
    public void afterAccountTest() throws InterruptedException {
        System.out.println("Depends on methods works");
        /*LandingScreen.getMyAccount().click();
        Thread.sleep(5000);*/
    }
}
