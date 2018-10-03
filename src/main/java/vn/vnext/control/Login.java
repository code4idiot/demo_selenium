package vn.vnext.control;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.vnext.enity.User;

import javax.xml.bind.Element;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends Init{
    WebDriver driver;
    private List<User> users;

    public Login(String url) {
        this.driver = getDriver();
        this.driver.get(url);
        users = new ArrayList<User>();
//        users.add(new User("khiemnd5@gmail.com", "Khiem28121993", ""));
//        users.add(new User("khiemnd5@gmail.com", "Khiem2812199", ""));

        loginStackOverflow();
    }

    public void loginStackOverflow() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Actions actions = new Actions(driver);

        WebElement emailElm = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement pwElm = driver.findElement(By.xpath("//*[@id=\"password\"]"));

        //case 1: test login fail: password empty
        emailElm.sendKeys("khiemnd5@gmail.com");
        pwElm.sendKeys("");

        // submit case 1
        WebElement element = driver.findElement(By.xpath("//*[@id=\"submit-button\"]"));
        actions.moveToElement(element).click().build().perform();
        
        // check pass or fail case 1
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[3]/div/div")));
        WebElement errLogin = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div"));
        String result = "Password cannot be empty.".equals(errLogin.getText()) ? "Pass" : "Fail";
        users.add(new User("khiemnd5@gmail.com", "", result));

        //case 2: test login fail : password is wrong
        emailElm.clear();
        emailElm.sendKeys("khiemnd5@gmail.com");
        pwElm.sendKeys("xxxxxx");

        // submit case 2
        actions.moveToElement(element).click().build().perform();

        // check pass or fail case 2
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[3]/div/div[2]")));
        WebElement errorCase2 = driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/div[2]"));

        String resultCase2 = "The email or password is incorrect.".equals(errorCase2.getText()) ? "Pass" : "Fail";
        users.add(new User("khiemnd5@gmail.com", "xxxxxx", resultCase2));
        
        exportResultToExcel();
    }


    public void exportResultToExcel() {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = Login.class.getResourceAsStream("/TCLogin.xls");
            os = new FileOutputStream("TCLoginOutput.xls");

            Context context = new Context();
            context.putVar("users", users);
            JxlsHelper.getInstance().processTemplate(is, os, context);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
