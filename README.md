# OnTimeRecommend

## Tools, software used in system
- JAVA 8: Backend development language
- MySQL: Service Repository
- Spring Boot : Base framework (https://www.tutorialspoint.com/spring_boot/spring_boot_introduction.htm)
- Spring Cloud : Zuul and Eureka for microservice service registry, client discovery and gateway edge service (https://spring.io/projects/spring-cloud)
- Google Dialog Flow: NLP platform for chatbot (https://dialogflow.com/)
- User interface: HTML, angularJS
- Rabbit MQ : https://www.rabbitmq.com/getstarted.html

## OnTimeRecommend Application Architecture
![image](https://user-images.githubusercontent.com/58735511/147992045-55a3e93b-c3da-4789-bb74-436580421397.png)
* Service Registry pattern to implement microservices

  A distributed system typically comprises a large number of services which communicate with each other to perform certain operations. Service discovery is the process of one service dynamically discovering the network location (IP address and port) of another service to communicate with it.
  The microservice style of architecture is not only building individual services so much as it is making the interactions between services reliable and failure-tolerant. 
  A service registry is a phone book for your microservices. Each service registers itself with the service registry and tells the registry where it lives (host, port, node name) and perhaps other service-specific metadata - things that other services can use to make informed decisions about it. Service registry provides a centralized place to store up-to-date information on where a service is currently running. This is usually the list of IP and ports, but can also include additional information, such as whether a machine is a master or not.
  Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, intelligent routing, micro-proxy, control bus, distributed sessions, etc.). 

  * Why we need service registry 
  
  Imagine a scenario in which one REST service (Service A) is trying to invoke another REST service (Service B). In order to make a request, Service A needs to know the network location (IP address and port) of Service B. In a conventional SOA (Service Oriented Architecture) ecosystem, services’ network locations would hardly change, as they are deployed in on-premise data centers. Consequently, you can afford to maintain the network locations of services in configuration files, which will be updated infrequently. For example, Service A can maintain the IP address and port of Service B in a configuration file and use those values when making a request. Following figure illustrates this flow.
![image](https://user-images.githubusercontent.com/58735511/147992239-27b744b7-f28c-4338-9d05-21d96cb4058b.png)

  Microservice architecture is all about breaking down monoliths into fine grained services. This results in an increased number of services that forms a complex communication mesh. Therefore, it is difficult for one service to maintain the network locations of all the other services, that it has to communicate with, in a property file.

![image](https://user-images.githubusercontent.com/58735511/147992293-e6c220f9-f0f7-4fab-a919-aed4cc7514b8.png)

  These complications raised the need to have a more sophisticated mechanism for microservices to dynamically discover the network locations of other microservices for communication. The concept of service discovery was introduced as a result. Service discovery mechanism uses a central registry to maintain the network locations of all the microservices. If for some reason the IP address and the port number of a particular microservice changes, new values will be immediately re-registered in the registry.
  
![image](https://user-images.githubusercontent.com/58735511/147992354-9e176700-3023-45cd-8f49-3b23499c53a6.png)

*	Microservice Communication(Spring Cloud Eureka)

  Eureka is a REST based service which is primarily used for acquiring information about services that you would want to communicate with. This REST service is also known as Eureka Server. The Services that register in Eureka Server to obtain information about each other are called Eureka Clients. Following diagram illustrates how Eureka clients and server fit in together for our project.

![image](https://user-images.githubusercontent.com/58735511/147992433-1ec227f6-f615-4f57-b595-aca7097d1677.png)

1.	Register and deregister Services to service registry 
2.	End user make request for service to gateway edge server 
3.	Ask for service endpoints
4.	Return service end point with service url
5.	Call service

## Implemented  Projects
### Microservices (all microservices inside microservices folder on git)
* Service & client Registry using Eureka

  * eureka-service-registroy-admin : Eureka Server

* Zuul Gateway Service

  * zuul-edge-server-admin : Gateway Service

* OnTimeRecommend Admin Services

  * login-admin-service : Admin Login services
  * recommender-registry-services : Register and Manage Recommender modules data
  * rec-workflow-const-services :  Add and manage all process related to recommender workflow
  * rec-orch-process-services :  Configure and Manage recommender process for different gateway clients
  * rabbitmq-consumer-service : Queue process for different gateway clients
  * manage-client-services :  Add and Manage Gateway clients
  * execute-script-service : Execute python or bash scripts for given command and arguments

* Recommender ML Process Services

  *	topic-model-data-col-service  : Data collection for Topic Model
  *	topic-model-data-prc-service : Data Processing for Topic Model
  *	topic-model-filter-model-service : Data Filter for Topic Model
  *	topic-model-data-model-service : Model Topic Recommender

* Recommendation Service to Gateway clients

  *	recommendation-service : Provide recommendation to user queries
  *	jyupter-run-model-service : Run Jyupter Recommender based on user inputs
  *	publication-run-model-service : Run Publication  Recommender based on user inputs
  *	scholar-run-model-service: Run scholar Recommender based on user inputs
  *	cloud-temp-run-model-service : Run Cloud Template Recommender based on user inputs
  *	topic-model-run-model-service : Run Topic Model Recommender based on user inputs

* VIDURA Services

  *	user-profile-services : Identify user profile category

### Web-application(all web application inside WebApps folder on git)

1.	OnTimeRecommend Admin Interface : http://localhost:8080/OnTimeRecommend/
2.	CyNeuro – Web : http://localhost:9100/CyNeuro/
3.	OnTimeEvidence – Web : http://localhost:9300/Covid-19/#
4.	KBCommons – Web : http://localhost:9222/KBCommons-Web/#!/

## Development Environment



