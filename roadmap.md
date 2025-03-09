WebSecurityScanner/
│── src/
│   ├── main/java/com/moksh/securityscanner/
│   │   ├── scanner/
│   │   │   ├── Scanner.java    # Main Scanner Logic
│   │   │   ├── HttpRequester.java # HTTP Handling
│   │   │   ├── SqlInjectionTester.java # SQLi Detection
│   │   │   ├── XssTester.java  # XSS Detection
│   │   │   ├── RedirectTester.java # Open Redirect Detection
│   │   ├── utils/
│   │   │   ├── Logger.java  # Custom Logging
│   │   │   ├── ReportGenerator.java  # Report in CSV/JSON
│   ├── test/java/com/moksh/securityscanner/ # Unit Tests
│── pom.xml (for Maven dependencies)
│── build.gradle (for Gradle dependencies)
│── README.md (Project Documentation)
│── .github/workflows/ci.yml (CI/CD Pipeline)
│── Dockerfile (for containerization)
│── .gitignore (Ignore target files)
