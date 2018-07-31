1) mvn clean package
  java -jar  xyz.jar
2) run ngrok http 8080 on terminal
   This will give an https url like -https://1bbea02d.ngrok.io
   linked to localhost:8080
3) Go to App Store --> Build Apps ---> Start Creating a new Flock App
Fill up the Basic information Form
Fill up the Advanced Information Form
Event Listener URL --- https://1bbea02d.ngrok.io/sample/event
AppId and AppSecret as on the App Config page needs to be put in properties file
