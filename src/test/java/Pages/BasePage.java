package Pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BasePage {


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected WebDriver driver;


    public static void acceptAlert(WebDriver driver){
        Alert alt =  driver.switchTo().alert();
        System.out.println(alt.getText());
        alt.accept();
    }

    public static void acceptDismiss(WebDriver driver){
        Alert alt =  driver.switchTo().alert();
        System.out.println(alt.getText());
        alt.dismiss();
    }

    public static void acceptAlert(WebDriver driver,String text){
        Alert alt =  driver.switchTo().alert();
        System.out.println(alt.getText());
        alt.sendKeys(text);
        alt.accept();
    }


    public static void mouseHover(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public static void dragNDrop(WebDriver driver, WebElement element1, WebElement element2){
        Actions act = new Actions(driver);
        act.dragAndDrop(element1,element2).build().perform();
    }

    public static void click(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.click(element).build().perform();
    }

    public static void rightClick(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.contextClick(element).build().perform();
    }


    public static void takeScreenshot(WebDriver driver){
        try {
            TakesScreenshot scrShot = (TakesScreenshot) driver;
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("./ScreenShot"+getDate()+".png");
            FileUtils.copyFile(srcFile, destFile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
    public static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now).replace("/","_").replace(" ","_").replace(":","_");

    }

    public static void fullScreenShot(WebDriver driver){
        try {
            Screenshot myscreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

            ImageIO.write(myscreenshot.getImage(), "PNG", new File("./fullScreen" + getDate() + ".png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void selectByText(WebElement element, String text){
        Select sel = new Select(element);
        sel.selectByVisibleText(text);
    }

    public static void selectByValue(WebElement element, String text){
        Select sel = new Select(element);
        sel.selectByValue(text);
    }

    public static void selectByIndex(WebElement element, int index){
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }


    public static  String  getValue(String key){
        String path = System.getProperty("user.dir")+File.separator+"config.properties";
        String value = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(fis);
            value = prop.getProperty(key);
            //   System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }


    public static void assertTitle(WebElement element, String title){
        String actual = element.getText();
        Assert.assertEquals(actual,title);
    }

    public static void assertTitleisDisplayed(WebElement element){
       System.out.println(element.getText());
        Assert.assertTrue(element.isDisplayed(),"Message is present");
    }



}
