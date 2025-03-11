package com.moksh.securityscanner.scanner;
import java.util.HashSet;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetDirectories {
    private static WebDriver driver;
    public ArrayList <WebElement> visited_links = new ArrayList<WebElement>();
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
            // return directories;
        }
        directories.add(baseUrl);
        try {
            
            driver.get(baseUrl);
            System.out.println("Title of the web page is: " + driver.getTitle());
            Thread.sleep(1000);
            
            List<WebElement> links = driver.findElements(By.tagName("a"));
            List<WebElement> buttonLinks = driver.findElements(By.tagName("button"));
            for (WebElement button : buttonLinks){
                String buttonUrl = button.getAttribute("href");
                if(buttonUrl!=null && buttonUrl.contains("Window.location.href")){
                    int start_index =  buttonUrl.indexOf("'") +1;
                    int end_index = buttonUrl.lastIndexOf("'");
                    buttonUrl = buttonUrl.substring(start_index, end_index);

                    if (!buttonUrl.equals(null) && isValidUrl(buttonUrl, baseUrl) && !directories.contains(buttonUrl)) {
                        getDirectories(buttonUrl);
                        counter++;
                        visited_links.add(button);

                    }
                }
            }
            for (WebElement link : links) {
                String linkUrl = link.getAttribute("href");
                if (linkUrl != null && isValidUrl(linkUrl, baseUrl) && !directories.contains(linkUrl)) {
                    getDirectories(linkUrl);
                    visited_links.add(link);
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
        driver.quit();
        gd.visited_links.forEach(link -> System.out.println(link));
    }
}
