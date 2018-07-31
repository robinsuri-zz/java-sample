# Sample JAVA APP with Event Listener


1) run `ngrok http 8080` on terminal  
   This will give an `https url like -https://1bbea02d.ngrok.io`  
   linked to localhost:8080  
2) Go to `App Store` --> `Build Apps` ---> `Start Creating a new Flock App`  
Fill up the Basic information Form  
Fill up the Advanced Information Form  
`Event Listener URL` --- `https://1bbea02d.ngrok.io/sample/event` 
whatever the url ngrok produced suffixed with `/sample/event`  
`appId` and `appSecret` as on the App Config page needs to be put in `application.properties` file  
located at `java-sample/src/main/resources/application.properties`
3) Run the command `mvn clean package` under `java-sample` folder  
This will produce a file `sample.jar` under `target` folder  
Run the project using `java -jar target/sample.jar`  
This will start the server on `port 8080`  

Click on Install on the App Page  
you will see the message - `App Install event Occurred`  
Click on Uninstall you will see the message - `App Uninstall event Occurred`
