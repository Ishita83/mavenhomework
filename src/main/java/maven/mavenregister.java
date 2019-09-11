package maven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class
mavenregister {
    protected static WebDriver driver;
    public String generatingEmail(String startValue) {
        String email = startValue.concat(new Date().toString());
        return email;
    }
    public static String randomDate() {
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }
    @BeforeMethod()
    public void openbrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\Browserdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        //maximise the browser window screnn
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
        //open the website
        driver.get("https://demo.nopcommerce.com/");
    }
    @AfterMethod
    public void closebrowser() {
        driver.quit();
    }
    @Test(priority=0)
    public void UsershouldRegisterSuccessfully() {

        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //Enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Honey");
        //Enter last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Shah");
        //Enter email
        driver.findElement(By.name("Email")).sendKeys("xyz" + randomDate() + "@gmail.com");
        //Enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("keni24");
        //Re-enter password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("keni24");
        //Click Register
        driver.findElement(By.xpath("//input[@id='register-button']")).click();

        // To test the actual and expected results
        String Expectedresult = "Your registration completed";
        String Actualresult = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(Actualresult, Expectedresult);
    }
    @Test(priority=1)
    public void UserShouldReferAproducttoFriend() {
        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        //Enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Honey");
        //Enter last name
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Shah");
        //Enter email
        driver.findElement(By.name("Email")).sendKeys("xyz" + randomDate() + "@gmail.com");
        //Enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("keni24");
        //Re-enter password
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("keni24");
        //Click Register
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        // click on Conitnue
        driver.findElement(By.name("register-continue")).click();
        //
        //driver.findElement(By.xpath("//div/input[@type='submit']")).click();
        //
        driver.findElement(By.linkText("Apple MacBook Pro 13-inch")).click();
        //click on Email a Friend button
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //enter friend's email
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys("abc@gmail.com");
        //enter personal message
        driver.findElement(By.xpath("//textarea[@name='PersonalMessage']")).sendKeys("Hi test 123");
        driver.findElement(By.xpath("//input[@name='send-email']")).click();

        //To test the actual and expected results
        String ExpectedResult = "Your message has been sent.";
        String ActualResult = driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(ActualResult, ExpectedResult);
    }
    @Test(priority=2)
    public void userShouldBeAbleToNavigateCameraAndPhotoPage() {
            //Click on Electronics
            driver.findElement(By.linkText("Electronics")).click();
            //Navigate to Camera and Photo Page
            driver.findElement(By.xpath("//h2/a[@title=\"Show products in category Camera & photo\"]")).click();

            //To test the results
            String Expectedresult = "Camera & photo";
            String Actualresult = driver.findElement(By.linkText("Camera & photo")).getText();
            Assert.assertEquals(Actualresult, Expectedresult);
    }
    @Test(priority=4)
    public void userShouldBeAbleAddBooksToTheShoppingCart(){
                //click on Books
                driver.findElement(By.linkText("Books")).click();
                // Add Book Fahrenheit to shopping Cart
                driver.findElement(By.xpath("//input[contains(@onclick,'/37/1/1')]")).click();
                //Check product in shopping cart
                driver.findElement(By.linkText("Shopping cart")).click();
                //click on Continue Shopping
                driver.findElement(By.name("continueshopping")).click();
                // Add Book First Prize Pies to Shopping Cart
                driver.findElement(By.xpath("//input[contains(@onclick,'/38/1/1')]")).click();

                //To test the expected and actual results
                String Expectedresult = "Shopping cart";
                String Actualresult = driver.findElement(By.linkText("Shopping cart")).getText();
                Assert.assertEquals(Actualresult, Expectedresult);
            }
    @Test(priority=3)
    public void UserShouldAbleToSelectJewlleryFromAFixedRange(){
        //click on Jewelry
        driver.findElement(By.linkText("Jewelry")).click();
        //Click on $700-$3000 Range
        driver.findElement(By.xpath("//a[contains(@href,\"700-3000\")]")).click();
        //To test the expected and actual results
        String expectedrange="$700.00 - $3,000.00";
        String actualrange = driver.findElement(By.xpath("//span[@class='item']")).getText();
        Assert.assertEquals(actualrange,expectedrange);

        String startprice =driver.findElement(By.xpath("//span[@class=\"PriceRange\" and text() ='$700.00']")).getText();
        String endprice =driver.findElement(By.xpath("//span[@class=\"PriceRange\" and text() ='$3,000.00']")).getText();
        String actualprice =driver.findElement(By.xpath("//span[@class=\"price actual-price\" and text() ='$2,100.00']")).getText();

        System.out.println(startprice);
        System.out.println(endprice);
        System.out.println(actualprice);
        float sp = Float.parseFloat(startprice.substring(1));
        System.out.println(sp);
        float ep = Float.parseFloat(endprice.replace(",","").substring(1));
        System.out.println(ep);
        float ap = Float.parseFloat(actualprice.replace("," ,"").substring(1));
        System.out.println(ap);

        Assert.assertTrue(sp<=ap && ap<=ep);
    }
    }






