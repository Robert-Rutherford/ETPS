www.etps.site

A Codeup capstone project created using Java, Spring Boot, Hibernate, JPA, Thymeleaf, jQuery, Moment JS, Apache POI, and SendGrid API.

Eligible Training Provider System Portal is a web application created to directly connect Texas Workforce Commission to eligible training providers to achieve a higher quality of communication and data submissions. ETPS provides straightforward displays for simpler navigation and essential functionality to effortlessly manage provider data. In the current system, providers are required to re-input information that rarely changes every time they need to make a new submission. They also have access to information to every provider associated with TWC which is not secure. 

###Providers can:
   - Download any of their previous submissions to autofill the form and cut down on time spent entering information that has not changed
   - Upload changed files and send them directly to TWC, which sends an automated message to inform TWC they have a new submission for review
   - Message the TWC representative at any time with questions or comments
   - Only have access to their own information 
    
    
###TWC can:
   - Download any submission from any provider, as well as having access to the master spreadsheet for all providers
   - Approve or deny submissions sent by providers, each of which will send a message to the corresponding provider
   - Message any provider with updates, deadlines, or comments
    
    
###Running the Project (IntelliJ)


- Clone the repository to your local machine
- Rename or make a copy of the example.properties file in src>main>resources and name it application.properties. Make sure to replace the username and password in the file with the username and password you want to use for the database user on your local machine. The database should be set up and created automatically by Spring.
- IntelliJ should automatically pickup that this is a Spring Boot application and allow you to start the backend, which runs at localhost:8080, from the configurations menu.

Creators:

- Miguel Garcia - https://github.com/miguelgarcia210
- Ethan Joiner - https://github.com/Ethan-Joiner
- Robert Rutherford - https://github.com/Robert-Rutherford
     

    
    
    