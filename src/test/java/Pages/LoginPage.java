package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utlities.JavaUtils;

import java.nio.file.Path;
import java.util.HashMap;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//h1[text()='Admin area demo']")
    WebElement pageHeader;

    @FindBy(xpath = "//*[text()='Welcome, please sign in!']")
    WebElement welcomeMsg;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(xpath = "//label[text()='Email:']")
    WebElement emailTitle;

    @FindBy(id = "Password")
    WebElement passwordTextField;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginButton;

    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    WebElement dashboardTitle;

    @FindBy(xpath = "//*[contains(text(),'Login was unsuccessful. Please correct the errors and try again.')]")
    WebElement errorMsg;

//

    HashMap<String, String>lp;

    public void verifyLogin(String testcaseid){
        lp = JavaUtils.readExcelData("Login",testcaseid);
        emailField.clear();
        emailField.sendKeys(lp.get("USERNAME"));
        passwordTextField.clear();
        passwordTextField.sendKeys(lp.get("PASSWORD"));
        loginButton.click();
        assertTitle(dashboardTitle,"Dashboard");

    }

    public void verifyInValidLogin(String testcaseid){
        lp = JavaUtils.readExcelData("Login",testcaseid);
        emailField.clear();
        emailField.sendKeys(lp.get("USERNAME"));
        passwordTextField.clear();
        passwordTextField.sendKeys(lp.get("PASSWORD"));
        loginButton.click();
        assertTitleisDisplayed(errorMsg);
    }

}
