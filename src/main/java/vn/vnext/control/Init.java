package vn.vnext.control;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Init {
    WebDriver driver;
    
    public Init() {
        String os = System.getProperty("os.name");

        if (os.equals("Linux")) {
            System.setProperty("webdriver.gecko.driver","geckodriver");
        } else if(os.equals("Windows 10")){
            System.setProperty("webdriver.gecko.driver","geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver","geckodriver_mac");//Mac OS X
        }

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.private.browsing.autostart", true);

        FirefoxOptions option=new FirefoxOptions();
        option.setProfile(firefoxProfile);

        this.driver = new FirefoxDriver(option);
    }

    public WebDriver getDriver() {
        return driver;
    }
    
}
