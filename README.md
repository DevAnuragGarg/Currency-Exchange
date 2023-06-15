# Currency-Exchange Service
Currency exchange is Java based web application using latest version of spring boot, jpa, hibernate, mysql. Main aim of this web service is to convert provided amount of the world popular currencies into indian rupees. Majorly 5 currencies are being converted into INR.
1) ***USD***: American dollar => 1USD = ₹82 
2) ***EUR***: Euro=> 1 EUR = ₹89
3) ***AUD***: Australian dollar => 1 AUD = ₹55
4) ***GBP***: Pound => 1GBP = ₹103
5) ***KWD***: Kuwaiti Dinar => 1KWD = ₹267


# URLs
### Repository Link: 
**GitHub link of the source code is _[here](https://github.com/DevAnuragGarg/Currency-Exchange)_**.

### Docker images:
**Docker hub link of all the images created is _[here](https://hub.docker.com/repository/docker/devanurag/currency-exchange/general)_**.


### Endpoints
1) Test URL (If everything is working fine): ***GET*** http://34.170.27.49:8291/currency-exchange/indian-ruppee/for/20
   Response Body: 
   ```text
   Currency Exchange service is working fine
   ```
2) Exchange rate Db insertion API: ***POST*** http://34.170.27.49:8291/currency-exchange/indian-ruppee/for/20
   Request Body:
   ```json
   {"id":1000,"to":"INR","from":"USD","conversionMutliple":65.00,"inputValue":50,"outputValue":3250.00}
   ```
3) Requesting conversion values for amount input: http://34.170.27.49:8291/currency-exchange/indian-ruppee/for/20
   Response Body:
   ```json
   {"id":1000,"to":"INR","from":"USD","conversionMutliple":65.00,"inputValue":50,"outputValue":3250.00}
   ```

## DOCKER

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
   For reference adding link for maven commands: https://jenkov.com/tutorials/maven/maven-commands.html
   For first time building the project use mvn package command in the terminal.

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

## KUBERNETES 
We are running kubernetes on the GKE free version. Create the cluster on GKE and run the following commands in the GKE console.

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

For applying the storage class and persistent volume claim
```bash
kubectl apply -f mysqldb-storage.yaml
```

For checking the pvc
```bash
kubectl get pvc
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

Get all the deployments
```bash
kubectl get deploy
```

For deleting the deployment
```bash
kubectl delete deploy <deployment-name>
```

For deleting the service
```bash
kubectl delete service <service-name>
```

For deleting everything (pods, deployments, containers, services)
```bash
kubectl delete all --all
```

For deleting everything (pods, deployments, containers, services)
```bash
rm *
```

## License
[MIT](https://choosealicense.com/licenses/mit/)




Remove 
https://www.kindsonthegenius.com/deploy-springboot-with-mysql-to-kubernetes-minikube-step-by-step-tutorial/
https://github.com/piomin/sample-spring-microservices-kubernetes/tree/master/k8s
https://www.youtube.com/watch?v=qmDzcu5uY1I
https://www.youtube.com/watch?v=EQNO_kM96Mo
https://openliberty.io/guides/kubernetes-intro.html#deploying-the-microservices
https://dev.to/musolemasu/deploy-a-mysql-database-server-in-kubernetes-static-dpc
https://gitlab.com/nanuchi/youtube-tutorial-series/-/tree/master/demo-kubernetes-components

https://cloud.google.com/kubernetes-engine/docs/concepts/persistent-volumes#:~:text=GKE%20creates%20a%20default%20StorageClass,default%20StorageClass%20with%20your%20own.


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

