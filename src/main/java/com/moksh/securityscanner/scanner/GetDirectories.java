package com.moksh.securityscanner.scanner;
import java.util.HashSet;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetDirectories {
    private static WebDriver driver;
    public int counter = 0;
    private HashSet<String> directories = new HashSet<>();

    public boolean isValidUrl(String linkUrl, String baseUrl) {
        try {
            URL link = new URL(linkUrl);
            URL base = new URL(baseUrl);
            if (link.getHost().equals(base.getHost())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashSet<String> getDirectories(String baseUrl) {
        
        if (directories.contains(baseUrl)) {
            counter++;
            System.out.println("Already scanned this URL: " + baseUrl);
            return directories;
        }
        directories.add(baseUrl);
        try {
            
            driver.get(baseUrl);
            System.out.println("Title of the web page is: " + driver.getTitle());
            Thread.sleep(1000);
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (WebElement link : links) {
                String linkUrl = link.getAttribute("href");
                if (linkUrl != null && isValidUrl(linkUrl, baseUrl) && !directories.contains(linkUrl)) {
                    getDirectories(linkUrl);
                    counter++;
                    // Process the links if needed
                }
            }
        } catch (Exception e) {
            System.out.println("Error while scanning the URL: " + baseUrl);
        }
        System.out.println("Total number of links: "+counter);
        return directories;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the URL: ");
        String baseUrl = sc.nextLine();
        sc.close();
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        GetDirectories gd = new GetDirectories();
        gd.getDirectories(baseUrl);
    }
}
