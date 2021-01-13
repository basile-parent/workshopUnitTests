# Workshop Unit Tests

## Installation
First, clone this repo.

### Java import
First possibility open the whole project and import the java folder as module (F4 -> Modules -> "+" button -> Import Module -> choose the pom.xml file)

![Open folder on IntelliJ](doc/open-folder.jpg) 
![Import java folder as module on IntelliJ](doc/import-module.jpg) 

![Select pom.xml file on IntelliJ](doc/select-pom.jpg)

You can test installation is done by :
- running the tests :
    - `mvn clean test`
    - or right click on java/src/test/java folder -> Run 'All tests'
- launching server and make a call (via Postman or equivalent) to http://localhost:8080/ping 

### JS import
Either open the whole project or select the "js" folder with File -> New -> Project from existing source
![Create new project from existing sources on IntelliJ](doc/existing-sources.jpg)

You can test installation is done by :
- opening index.html file
- and by running the tests : `npm run test`

![Front preview](doc/preview-front.jpg)

## Exercice
