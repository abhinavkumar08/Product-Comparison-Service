# Product-Comparison-Service
A simple Springboot project to compare Products.

Steps to run the Project : 

1. Extract the zip folder to your local machine.

2.Import the project as Maven Project to Eclipse or Intellij, Make sure the IDE is using JAVA 11.
 
3. Build the project using maven package or maven install.

4. Check the target folder, product-comparison-service.jar should be generated.

5. Open cmd or bash terminal and navigate to project location. Make sure you have docker installed on your system.

6. Run the below command to create a docker image of the application.
    docker build -t product-comparison-service .
    
7. Run "docker images" to check whether the image is created successfully.

8. Navigate to the project folder location and run docker-compose up. This will start the appliction on port 8083 and 3 mongo cluster on ports 20717, 20718 and 20719.

9. After the server is started, checkout the below URL to see the API docs :

http://localhost:8080/swagger-ui.html#/

10. Test by firing an api requets.
