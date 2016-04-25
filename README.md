# spring-oauth2-demo

Demonstration project for OAuth2 using spring security oauth2 containing...

* Authorization server (using basic authentication), running at http://localhost:9090/uaa
* Resource server (with simple rest service), running at http://localhost:9092/resource/service
* SSO client (triggering SSO with authorization server and calling resource server), running at http://localhost:9094/client
