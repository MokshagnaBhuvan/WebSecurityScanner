package com.moksh.securityscanner.scanner;

public class checkVuln {

    public void scanForVulnerabilities(String url){
        inputFieldCheck inputFieldCheck = new inputFieldCheck();
        System.out.println("Checking for vulnerabilities in the URL: "+url);
        inputFieldCheck.checkInputFields(url);
        
    }
}
