package vn.vnext.control;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        users.add(new User("khiemnd5@gmail.com", "Khiem28121993", ""));
        users.add(new User("khiemnd5@gmail.com", "Khiem2812199", ""));

        loginTiki();
    }

    public void loginTiki() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try {
            driver.findElement(By.cssSelector("html.js.no-touchevents.-moz- body.tiki-login.firefox.ReactModal__Body--open div.ReactModalPortal div.ReactModal__Overlay.ReactModal__Overlay--after-open div.ReactModal__Content.ReactModal__Content--after-open div.popup-sms button.popup-sms__close")).click();
        } catch (Exception e) {

        }

        WebElement emailElm = driver.findElement(By.id("email"));
        WebElement pwElm = driver.findElement(By.id("password"));

        //case 1: test login fail
        emailElm.sendKeys("khiemnd5@gmail.com");
        pwElm.sendKeys("xxxxxx");

        driver.findElement(By.cssSelector("#login-form > form > div.form-group.last > button")).click();

        WebElement errLogin = driver.findElement(By.xpath("//*[@id=\"login-form\"]/form/div[1]"));

        System.out.println("Sai tên truy cập hoặc mật khẩu".equals(errLogin.getText()));




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
