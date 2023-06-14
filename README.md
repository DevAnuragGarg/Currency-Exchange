# Currency-Exchange
Java based Spring boot application for exchanging the currency from one to another

# URLs
Request URL for currency exchange: http://localhost:8291/currency-exchange/from/USD/to/INR/value/50

Response:
{"id":1000,"to":"INR","from":"USD","conversionMutliple":65.00,"inputValue":50,"outputValue":3250.00}


## DOCKER

For creating the build run maven command:
***mvn clean package -Dmaven.test.skip=true***

Clears the target directory and builds the project and packages the resulting JAR file into the target directory - without running the unit tests during the build.

//if lifecycle phase not found exception is coming then go to this link and run commands
https://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
mvn package is the solution

All the maven commands
https://jenkov.com/tutorials/maven/maven-commands.html

Docker images can be created in two ways: 
1) Adding the docker plugin details in the pom.xml file and maven build the project.
   Add this line below maven plugin
   ```xml
   <configuration>
      <image>
        <name>devanurag/${project.artifactId}:${project.version}</name>
      </image>
      <pullPolicy>IF_NOT_PRESENT</pullPolicy>
   </configuration>
   ```
   Here devanurag is the docker hub username. Then run this command in maven build.
   ```bash
   spring-boot:build-image -DskipTests
   ```
   This will create the docker image with the name *devanurag/currency-exchange:<TAG>*

2) Another way using the command line by creating the docker image from the jar file as mentioned in Dockerfile
```bash
docker build -t currency-exchange:<TAG> .
```
-t is for tagging the image. currency-exchange is the name of the image. TAG is the tag you want to add to that image. 
If you don’t add any tag, it defaults to the tag named latest. . means, we are referring to the Dockerfile location as the docker build context.

For running the docker image generated
```bash
docker run -p 8291:8291 currency-exchange:<TAG>
```

Check if you are logged in, if not logged in, it will ask for username and password
```bash
docker login
```

Change the docker tag before pushing, add docker username (hub.docker.com) in front of the image. It will push the image to that username account. 
```bash
docker tag currency-exchange:<TAG> devanurag/currency-exchange:<TAG>
```

Push the docker image to docker hub
```bash
docker push devanurag/currency-exchange:<TAG>
```

Run the docker using docker compose file
```bash
docker-compose up
```

List all the running containers
```bash
docker container ls
OR
docker ps
```
Remove all unused containers, networks, images
```bash
docker system prune
```

// create database
docker run --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=currency_exchange_db --name mysqldb --publish 3306:3306 mysql

TABLE QUERIES
SELECT * FROM currency_exchange;

insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1001, 82, 'USD', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1002, 89, 'EUR', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1003, 55, 'AUD', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1004, 103, 'GBP', 'INR');
insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to)
values(1005, 267, 'KWD', 'INR');



https://www.bezkoder.com/docker-compose-spring-boot-mysql/

https://codelabs.developers.google.com/codelabs/cloud-springboot-kubernetes#0

https://medium.com/@dayan888/sample-java-web-system-on-kubernetes-e52069390916

https://www.endpointdev.com/blog/2022/01/kubernetes-101/

https://www.javaguides.net/2022/12/deploy-spring-boot-mysql-application-to-docker.html

https://github.com/RameshMF/springboot-docker-course/tree/main

https://github.com/Java-Techie-jt/springboot-crud-k8s

https://www.youtube.com/watch?v=pIPji3_rYPY

https://www.youtube.com/watch?v=-ekBqIvAGY4

https://www.youtube.com/watch?v=6hMHziv0T2Y


## KUBERNETES

For applying the secret
```bash
kubectl apply -f mysqldb-secret.yaml
```

To check the secret is successfully applied
```bash
kubectl get secret
```

For applying the configmap
```bash
kubectl apply -f currency-exchange-configmap.yaml
```

To check the configmap is successfully applied
```bash
kubectl get configmap
```

Now, applying the mysql database deployment
```bash
kubectl apply -f mysqldb-deployment.yaml
```

To check if the mysql server is running
```bash
kubectl exec -it <mysql-pod-name> --bash
bash-4.4# mysql currency_exchange_db -u root -p
```

If mysql server is running login using the credentials, you will be logged in
```bash
bash-4.4# mysql currency_exchange_db -u root -p
```

Let's get the port number and more information about mysql pod
```bash
kubectl describe pod <mysql-pod-name>
```

To check if the mysql service is running, on what type, IP address and Port:
```bash
kubectl get service
OR
kubectl describe service <mysql-service-name>
```

Now, let's deploy the currency exchange service
```bash
kubectl apply -f currency-exchange-deployment.yaml
```

Keep on checking with the pod, what's happening
```bash
kubectl get pod --watch
```

Check the logs for the pod
```bash
kubectl logs --tail=-1 <pod-name>
```

// delete everything (pods, deployments, containers, services)
```bash
kubectl delete all --all
```

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

https://www.kindsonthegenius.com/deploy-springboot-with-mysql-to-kubernetes-minikube-step-by-step-tutorial/
https://github.com/piomin/sample-spring-microservices-kubernetes/tree/master/k8s
https://www.youtube.com/watch?v=qmDzcu5uY1I
https://www.youtube.com/watch?v=EQNO_kM96Mo
https://openliberty.io/guides/kubernetes-intro.html#deploying-the-microservices
https://dev.to/musolemasu/deploy-a-mysql-database-server-in-kubernetes-static-dpc
https://gitlab.com/nanuchi/youtube-tutorial-series/-/tree/master/demo-kubernetes-components


Command to create the deployment:
kubectl create deployment current-exchange-api --image=devanurag/currency-exchange:TAG

Exposing the deployment to outer world on particular port:
kubectl expose deployment current-exchange-api --type=LoadBalancer --port=8291

Get the number of running nodes:
kubectl get pods

Get little more description of the pods
kubectl get pods -o wide


## License

[MIT](https://choosealicense.com/licenses/mit/)