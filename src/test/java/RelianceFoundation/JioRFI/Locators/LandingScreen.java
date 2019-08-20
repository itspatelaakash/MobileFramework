package RelianceFoundation.JioRFI.Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;


public class LandingScreen {
    public AndroidDriver driver;

    public static final By next = By.id("com.rfh.vaccination:id/landing_get_access_view");
    public static final By loginButton = By.id("dashboardId3");
    public static final By mobileNo = By.id("mobileNumber");
    public static final By password = By.id("password");
    public static final By login = By.id("loginId2");
    public static final By myAccount = By.id("dashboardId17");
    public static final By myRecords = By.id("dashboardId16");
    public static final By userProfile = By.id("currentUserProfileY1");
    public static final By vaccineInfo = By.id("dashboardId11");



    public LandingScreen(AndroidDriver driver) {
        this.driver= driver;
    }


    public WebElement getNext() {
        return driver.findElement(next);
    }

    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }

    public WebElement getMobileNo() {

        return driver.findElement(mobileNo);
    }

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    public WebElement getLogin() {
        return driver.findElement(login);
    }
    public WebElement getMyRecords() {
        return driver.findElement(myRecords);
    }
    public WebElement getUserProfile() {
        return driver.findElement(userProfile);
    }
    public WebElement getVaccineInfo() {
        return driver.findElement(vaccineInfo);
    }
    public WebElement getDTP(){
        return driver.findElementByXPath("//android.view.View[@text='DTP']");
    }

}
