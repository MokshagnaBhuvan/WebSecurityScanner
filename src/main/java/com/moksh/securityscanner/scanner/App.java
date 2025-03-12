package com.moksh.securityscanner.scanner;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the url path to check for input fields: ");
        String url = sc.nextLine();
        checkVuln check = new checkVuln();
        check.scanForVulnerabilities(url);
        sc.close();
    }
}
