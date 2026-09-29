import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestExercise1 {
    static WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.smartclient.com/smartgwt/showcase/#featured_tile_filtering");
    }

    @Test
    public void searchTest() {
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"isc_2S\"]"));
        searchBox.sendKeys("a");
        assertEquals(searchBox.getAttribute("value"), "a");
    }

    @Test
    public void sliderTest() throws InterruptedException {
        WebElement maxLifeSpanSliderHandle = driver.findElement(By.xpath("//*[@id=\"isc_2G\"]"));

        sleep(1000);

        int offsetLength = driver.findElement(By.xpath("//*[@id=\"isc_2P\"]")).getSize().getWidth() / 3;

        new Actions(driver).moveToElement(maxLifeSpanSliderHandle).clickAndHold().moveByOffset(-offsetLength, 0).release().perform();

        String lifeSpanValue = driver.findElement(By.xpath("//*[@id=\"isc_2K\"]/table/tbody/tr/td")).getText();
        assertEquals(lifeSpanValue, "40");
    }

    @Test
    public void sortTest() {
        driver.findElement(By.xpath("//*[@id=\"isc_3G\"]")).click();
        driver
                .findElement(By.xpath("//*[@class=\"pickListMenu\"]"))
                .findElement(By.xpath(".//*[text()='Life Span']"))
                .click();
        String selectedSortValue = driver.findElement(By.xpath("//*[@id=\"isc_3G\"]")).getText();
        assertEquals(selectedSortValue, "Life Span");

        driver.findElement(By.xpath("//*[@id=\"isc_3Q\"]/span")).click();
    }

    @Test(priority = 1)
    public void testNumResults() {
        int numResults = driver.findElements(By.className("simpleTile")).size();
        assertTrue(numResults > 12);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}