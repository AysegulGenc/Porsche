package com.group24.tests.porshe;

import com.group24.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class PorsheTest extends TestBase {
    @Test
    public void porscheTesting() throws InterruptedException {
        driver.get("https://www.porsche.com/usa/modelstart/");
        driver.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();
        String cost718CaymanSWholeText= driver.findElement(By.xpath("//div[@id='m982130']//div[@class='m-14-model-price']")).getText();
        String cost718CaymanS= cost718CaymanSWholeText.substring(cost718CaymanSWholeText.indexOf("$"),cost718CaymanSWholeText.indexOf("*")-3).replace(" ","");
        driver.findElement(By.xpath("//div[@id='m982130']")).click();
        Thread.sleep(5000);
        Set<String> windows = driver.getWindowHandles();
        for(String str: windows){
            driver.switchTo().window(str);
            if(!driver.getTitle().contains("-")){
                break;
            }
        }
        String cost718CaymanSInner= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Base price:']/../div[2]")).getText();
        Assert.assertEquals(cost718CaymanSInner,cost718CaymanS);
        String acPriceForEq= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Price for Equipment:']/../div[2]")).getText().
                substring(1);
        Assert.assertEquals(acPriceForEq,"0");
        String totalPrice= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Total Price:*']/..//div[2]")).getText().
                substring(1).replace(",","");
        String feesDelHand= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[contains(text(),'Delivery')]/..//div[2]")).getText().
                substring(1).replace(",","");
        double total= Double.parseDouble(totalPrice);
        double base= Double.parseDouble(cost718CaymanSInner.substring(1).replace(",", ""));
        double fees= Double.parseDouble(feesDelHand);
        double priceForEq= Double.parseDouble(acPriceForEq);
        Assert.assertEquals(total, base+fees+priceForEq);
        driver.findElement(By.id("s_exterieur_x_FJ5")).click();
        //locate and store again, cause elements value changed after click
        acPriceForEq= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Price for Equipment:']/../div[2]")).getText().
                substring(1).replace(",","");
        Assert.assertEquals(acPriceForEq, driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price").substring(1).replace(",",""));
        //locate and store again, cause elements value changed after click
        totalPrice= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Total Price:*']/..//div[2]")).getText().
                substring(1).replace(",","");
        feesDelHand= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[contains(text(),'Delivery')]/..//div[2]")).getText().
                substring(1).replace(",","");
        total= Double.parseDouble(totalPrice);
        fees= Double.parseDouble(feesDelHand);
        priceForEq= Double.parseDouble(acPriceForEq.replace(",",""));
        Assert.assertEquals(total, base+fees+priceForEq);
        //Thread.sleep(2000);
        // driver.findElement(By.xpath("//div[@id='scrollIndicator']")).click();
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("(//li[@id='s_exterieur_x_MXRD']//span)[2]")).click();
        String priceCarreraSportWheel= driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price");
        double costCarreraSportWheel= Double.parseDouble(priceCarreraSportWheel.substring(1).replace(",",""));
        //locate and store again, cause elements value changed after click
        acPriceForEq= driver.findElement(By.xpath("//section[@id='s_quicksum']//div[.='Price for Equipment:']/../div[2]")).getText().
                substring(1).replace(",","");
        priceForEq= Double.parseDouble(acPriceForEq);
        String costOfBlueColor= driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price").substring(1).replace(",","");
        double blueColorPrice= Double.parseDouble(costOfBlueColor);
        Assert.assertEquals(priceForEq,blueColorPrice+costCarreraSportWheel);
        System.out.println(priceForEq);
        System.out.println(blueColorPrice);
        System.out.println(costCarreraSportWheel);
        System.out.println(blueColorPrice+costCarreraSportWheel);

    }
}
