package RelianceFoundation.JioRFI.AutomationScripts;

import RelianceFoundation.JioRFI.Base;

import org.testng.annotations.Test;

public class xyz extends Base {

    @Test
    public void xyzTest(){
        System.out.println("Test");
        landingScreen.getNext().click();
    }
}
