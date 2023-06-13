# Currency-Exchange
Java based Spring boot application for exchanging the currency from one to another

# URLs
Request URL for currency exchange: http://localhost:8291/currency-exchange/from/USD/to/INR/value/50

Response:
{"id":1000,"to":"INR","from":"USD","conversionMutliple":65.00,"inputValue":50,"outputValue":3250.00}



// creating the build
Run maven command:
mvn clean package -Dmaven.test.skip=true
Clears the target directory and builds the project and packages the resulting JAR file into the target directory - without running the unit tests during the build.

//if lifecycle phase not found exception is coming then go to this link and run commands
https://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
mvn package is the solution

All the maven commands
https://jenkov.com/tutorials/maven/maven-commands.html

// for creating the docker image
For creating the docker image there are two days:
1) Adding the docker plugin details in the pom.xml file and maven build the project.

Add this line below maven plugin
<configuration>
<image>
<name>devAnurag/${project.artifactId}:${project.version}
</image>
<pullPolicy>IF_NOT_PRESENT</pullPolicy>
</configuration>

Then run this command in maven build..
spring-boot:build-image -DskipTests

2) Create docker file and run command
   docker build -t currency-exchange:tag .
   -t is for tagging the image.
   nginx is the name of the image.
   1.0 is the tag name. If you don’t add any tag, it defaults to the tag named latest.
   . means, we are referring to the Dockerfile location as the docker build context.

// for running the docker image
docker run -p 8291:8291 docker-image-name

// check whether you are logged in using your docker credentials
docker login
if not logged in it will ask for username and password otherwise it will show successfully logged in

// docker tagggin before pushing, add username infront
docker tag currency-exchange:0.0.1 devanurag/currency-exchange:0.0.1

// a new image will be created and then push the same file
docker push devanurag/currency-exchange:0.0.1

// to use the docker compose
use docker-compose up

// create database
docker run --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=currency_exchange_db --name mysqldb --publish 3306:3306 mysql

TABLE QUERIES
SELECT * FROM currency_exchange;

insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1001, 65, 'USD', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1002, 75, 'EUR', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1003, 25, 'AUD', 'INR');




https://codelabs.developers.google.com/codelabs/cloud-springboot-kubernetes#0

https://medium.com/@dayan888/sample-java-web-system-on-kubernetes-e52069390916

https://www.endpointdev.com/blog/2022/01/kubernetes-101/

https://www.javaguides.net/2022/12/deploy-spring-boot-mysql-application-to-docker.html

https://github.com/RameshMF/springboot-docker-course/tree/main

https://github.com/Java-Techie-jt/springboot-crud-k8s






For applying and checking the secret
kubectl apply -f mysqldb-secret.yaml
kubectl get secret

For applying the deployment
kubectl apply -f mysqldb-deployment.yaml

// to check if the service is attached to right pod:
kubectl get service
kubectl describe service <service-name (DB ClusterIP)>
There you can check the IP address of the pod

// run the config map
kubectl apply -f currency-exchange-configmap.yaml

// go into that mysql pod
kubectl exec -it <pod-name> --bash

// check the config map
kubectl get configmaps

// run the currency-exchange deployment
kubectl apply -f currency-exchange-deployment.yaml

// check the IP address of the mysql pod
kubectl get pod -o wide

// get all the values
kubectl get all

// check what is happening with the pod
kubectl get pod --watch

// if you want to know if something is wrong or not
kubectl describe pod <pod-name>

kubectl decribe deploy

// delete everything
kubectl delete all --all

// run in mysql interactive mode
kubectl run -it --rm --image=mysql:8.0 --restart=Never mysql-client -- mysql -h mysql -password="password"

// remove all the yamls
rm *.yaml

// for checking the persistent volume
kubectl get pv

// for claim
kubectl get pvc

// to edit the file, use 
vi <yaml file>

https://github.com/piomin/sample-spring-microservices-kubernetes/tree/master/k8s
https://www.youtube.com/watch?v=qmDzcu5uY1I
https://www.youtube.com/watch?v=EQNO_kM96Mo
https://openliberty.io/guides/kubernetes-intro.html#deploying-the-microservices
https://dev.to/musolemasu/deploy-a-mysql-database-server-in-kubernetes-static-dpc
https://gitlab.com/nanuchi/youtube-tutorial-series/-/tree/master/demo-kubernetes-components


# initial dependency
FROM openjdk:17

# maintainer of the project
LABEL maintainer="Anurag Garg <developer.anuraggarg@gmail.com>"

# exposing the port for the service
EXPOSE 8291

#add the jar file, need to replace add with copy
ADD target/currency-exchange-0.0.2-SNAPSHOT.jar currency-exchange-0.0.2-SNAPSHOT.jar

# run this jar
ENTRYPOINT ["java", "-jar", "currency-exchange-0.0.2-SNAPSHOT.jar"]

Command to create the deployment:
kubectl create deployment current-exchange-api --image=devanurag/currency-exchange:TAG

Exposing the deployment to outer world on particular port:
kubectl expose deployment current-exchange-api --type=LoadBalancer --port=8291

Get the number of running nodes:
kubectl get pods

Get little more description of the pods
kubectl get pods -o wide

Get the number of replicaset:
kubectl get replicasets

Get the number of services:
kubectl get services

Get the number of deployments:
kubectl get deployments

Increasing the number of pods running at one time
kubectl scale deployment current-exchange-api --replicas=4

Delete the pod
kubectl delete pod <pod-id>

Deploying the new image to the deployment
kubectl set image deployment <deployment-id> <container-id>=<image-name>

Get the container-id for the deployment using replicaset
kubectl get rs -o wide