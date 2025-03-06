package com.moksh.securityscanner.scanner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class inputFieldCheck {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        List<String> Locators = new ArrayList<>();
String path ="/home/white7minato/WebSecurityScanner/src/main/java/com/moksh/securityscanner/scanner/possibleLocators.txt";
try(BufferedReader br = new BufferedReader(new FileReader(path))){
    String line;
    while((line = br.readLine()) != null){
        Locators.add(line);
        // System.out.println(line);
    }
}
catch(IOException e){
    e.printStackTrace();
}
System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

    WebDriver driver = new ChromeDriver();
try {
System.out.println("Enter the url path to check for input fields: ");
String url = sc.nextLine();
driver.get(url);
System.out.println("Title of the web page is: "+driver.getTitle());
} catch (Exception e) {
    e.printStackTrace();
}


for(String locator : Locators){
    String[] LocatorParts = locator.split("=");
    String locatorType = LocatorParts[0];
    String locatorValue = LocatorParts[1];

    WebElement inputField = null;
    try {
        if(locatorType.equalsIgnoreCase("id")){
            inputField = driver.findElement(By.id(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("name")){
            inputField = driver.findElement(By.name(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("className")){
            inputField = driver.findElement(By.className(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("tagName")){
            inputField = driver.findElement(By.tagName(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("linkText")){
            inputField = driver.findElement(By.linkText(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("partialLinkText")){
            inputField = driver.findElement(By.partialLinkText(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("cssSelector")){
            inputField = driver.findElement(By.cssSelector(locatorValue));
        }
        else if(locatorType.equalsIgnoreCase("xpath")){
            inputField = driver.findElement(By.xpath(locatorValue));
        }
        else{
            System.out.println("Invalid locator type");
        }
    } catch (Exception e) {
        System.out.println("Element not found with locator: "+locator);
    }
    
}
    
}
}
