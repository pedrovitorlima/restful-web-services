# JWT and API Versioning Example  
  
This project contains a few examples of API versioning, like URI and Parameter Versioning. Besides, the authorization and authentication was developed using Spring Security with JWT.  
  
## How To Run  
  
With maven installed, run the command bellow in the root folder (where the pom.xml is located):  
  
`mvn spring-boot:run`  
  
## JSON Web Tolken (JWT)  
  
This internet standards alow users to login in the application sending a JSON with username and password in the body and receive a tolken authentication witch thew can use further to make request for endpoints in the API. This tolken have a expiration time (1 day for this application, you can see more in JwtConfig.java).   
This application has an embedded database and some users is created during the runtime. Take a look on data.sql to view possible users and make your tests.  
  
### How to Authenticate  
  
Run the application and use an external tool like Postman to make a POST request with a content-type Application/Json with `username` and `password` properties to http://localhost:8080/auth/.   
You will see the tolken answered by response above, in the tab Headers.  
  
  ![Authentication using postman](https://github.com/pedrovitorlima/restful-web-services/blob/master/authentication-postman.png)
  
After request the /auth/ endpoint, the authentication will be processed by a filter called JWTLoginFilter.  
  
### How to Call Endpoints  
  
Use the tolken in previous step as value for `Authorization` header. Don't forget to start it with keyword `Bearer`.  
  
![Authorization using postman](https://github.com/pedrovitorlima/restful-web-services/blob/master/authorization-postman.png)  

If you are using Postman, you can use the Authorization tab. Select the type `Bearer Tolken` and paste the tolken without the keyword `Bearer`.  
  
When request an endpoint using the tolken, the validation will be processed by a filter called JWTTokenAuthenticationFilter.  
  
## API Versioning  
  
You can see the types of versioning taking a look on the class PersonVersioningController.java in this project. Don't forget to authenticate before and use the bearer tolken in requests.