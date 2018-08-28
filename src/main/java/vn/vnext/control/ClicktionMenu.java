package vn.vnext.control;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class ClicktionMenu extends Init{
    WebDriver driver;

    String [][]menuTesting = {{"Software Testing Tutorial: Free Course", "Free Selenium Tutorials", "UFT/QTP Tutorial for Beginners: Learn in 7 Days", "JMeter Tutorial for Beginners: Learn in 7 Days", "JIRA Tutorial: A Complete Guide for Beginners"},
            {"TEST Management Tutorials: Complete Training Course", "Free Mobile App Testing Tutorial", "SoapUI Tutorials for Beginners", "ETL Testing or Data Warehouse Testing Tutorial", "Bugzilla Tutorial for Beginners: Defect Tracking Tool"}};
    
    public ClicktionMenu(String url) {
        this.driver = getDriver();
        this.driver.get(url);
        clickMenu();
    }
    
    public void clickMenu() {
        
        // close popup
        WebDriverWait wait = new WebDriverWait(driver, 5);

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.println(menuTesting[j][i]);
//            }
//        }
        
        // click step by step pass to menu
        try {
            for (int i = 1; i <= 5; i++) {
                for (int j = 1; j <= 2; j++) {
                    driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/div/div/div/div/div[1]/div[2]/ul/li[3]/a/span")).click();
                    driver.findElement(By.xpath("/html/body/div[2]/section[2]/div/div/div/div/div/div/div[1]/div[5]/div[1]/div/div[" + i + "]/ul/li[" + j + "]/a/span")).click();
                    wait.until(titleContains(menuTesting[j-1][i-1]));
                }
            }
        } catch (Exception e) {

        } finally {
            driver.quit();
        }

        
    }
}
