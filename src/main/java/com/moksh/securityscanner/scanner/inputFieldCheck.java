package com.moksh.securityscanner.scanner; // spell-checker: disable-line

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// import java.time.Duration; // Unused import
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class inputFieldCheck {
    public void checkInputFields(String url) {
        GetDirectories availableDirectories = new GetDirectories();
        List<String> locators = new ArrayList<>();
        String path = "/home/white7minato/WebSecurityScanner/src/main/java/com/moksh/securityscanner/scanner/possibleLocators.txt"; // spell-checker: disable-line
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                locators.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver"); // spell-checker: disable-line
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get(url);
            System.out.println("Title of the base web page is: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> directories = availableDirectories.getDirectories(url);
        
        if (directories.isEmpty()) {
            System.out.println("No directories found for the given URL: " + url);
        }
        
        for (String directory : directories) {
            try {
                driver.get(directory);
                System.out.println("Checking directory: " + directory);
            } catch (Exception e) {
                System.out.println("Failed to navigate to directory: " + directory);
                continue;
            }

            for (String locator : locators) {
                String[] locatorParts = locator.split("=");
                if (locatorParts.length != 2) {
                    System.out.println("Invalid locator format: " + locator);
                    continue;
                }
                
                String locatorType = locatorParts[0];
                String locatorValue = locatorParts[1];

                try {
                    WebDriverWait wait = new WebDriverWait(driver, 2); 

    
    WebElement inputField = null;
    switch (locatorType.toLowerCase()) {
        case "id":
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
            break;
        case "name":
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
            break;
        case "classname":
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
            break;
        case "tagname": // spell-checker: disable-line
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
            break;
        case "linktext": // spell-checker: disable-line
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
            break;
        case "partiallinktext": // spell-checker: disable-line
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
            break;
        case "cssselector": // spell-checker: disable-line
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
            break;
        case "xpath":
            inputField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
            break;
        default:
            System.out.println("Invalid locator type: " + locatorType);
    }
                    if (inputField != null) {
                        System.out.println("Input field found in directory: " + directory + " with locator: " + locator);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Element not found in directory: " + directory + " with locator: " + locator);
                }
            }
        }
        
        driver.quit();
    }
}
