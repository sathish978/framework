package testScripts;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class TC_LGN_003 extends BaseTest{

    @Test
    public void TC_LGN_003(){
        LoginPage lp = new LoginPage(driver);
        lp.verifyInValidLogin("TC_LGN_003");


    }
}
