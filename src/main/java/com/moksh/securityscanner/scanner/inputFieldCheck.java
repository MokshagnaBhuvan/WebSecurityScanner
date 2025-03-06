package com.moksh.securityscanner.scanner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class inputFieldCheck {
    public static void main(String args[]){
        List<String> Locators = new ArrayList<>();
String path ="/home/white7minato/websecurityscanner/WebSecurityScanner/src/main/java/com/moksh/securityscanner/scanner/possibleLocators.txt";
try(BufferedReader br = new BufferedReader(new FileReader(path))){
    String line;
    while((line = br.readLine()) != null){
        Locators.add(line);
        System.out.println(line);
    }
}
catch(IOException e){
    e.printStackTrace();
}
WebDriver driver = new ChromeDriver();

for(String locator : Locators){
    String[] LocatorParts = locator.split("=");
    String locatorType = LocatorParts[0];
    String locatorValue = LocatorParts[1];

    WebElement inputFeild = null;
    // if(locatorType.equalsIgnoreCase("))
}
    
}
}
