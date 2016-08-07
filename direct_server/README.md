# About
Simple server which stores Customer(Post customer-json on checkAccess/) and CustomerData(Post customerData-json on checkData/) entities by 
post requests. Jsons are validated so, you can't post inapropriate data. 
Then, you can retrieve data by Posting user-json on login/ . Only if you are a admin.
Server has simple user-firendly page with login form which is located on GET /login route.

Json format:
```{"login": "user", "password": "userpass"}```

# Intsallation requirements
* Java 8
* Gradle
* Git

# Installation
```bash
# Download app
git clone https://github.com/SigmaOne/orbs.io.git
cd orbs.io

# Run tomcat, run db, deploy war
gradle bootRun 
```
