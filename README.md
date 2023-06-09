# Currency-Exchange Service
Currency exchange is Java based web application using latest version of spring boot, jpa, hibernate, mysql. Main aim of 
this web service is to convert provided amount in the API from the world popular currencies into indian rupee. 
Majorly 5 currencies are being converted into INR, in this project. We will insert the conversion value from these
currencies to INR into the database and when we hit the currency-exchange api with the amount, it will convert that amount
of currency into INR. Ex. for amount entered 1, all the corresponding currency values are converted into INR.
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
1) Test URL (If everything is working fine): ***GET*** http://34.170.27.49:8291/test
   Response Body: 
   ```text
   Currency Exchange service is working fine
   ```
2) Exchange rate DB insertion API: ***POST*** http://34.170.27.49:8291/currency-exchange
   Request Body:
   ```json
   [
     {
      "from": "USD",
      "to": "INR",
      "conversionMultiple": 82
    },
    {
      "from": "EUR",
      "to": "INR",
      "conversionMultiple": 89
    },
    {
      "from": "AUD",
      "to": "INR",
      "conversionMultiple": 267
    },
    {
      "from": "GBP",
      "to": "INR",
      "conversionMultiple": 103
    },
    {
      "from": "KWD",
      "to": "INR",
     "conversionMultiple": 267
    }
   ]
   ```
3) Requesting conversion values for amount input: ***GET*** http://34.170.27.49:8291/currency-exchange/indian-rupee/for/20
   Response Body:
   ```json
   {
    "id": 1,
    "to": "INR",
    "from": "USD-EUR-AUD-GBP-KWD",
    "conversionMultiple": 1.0,
    "inputValue": 20,
    "usdToInrValue": 1640.00,
    "eurToInrValue": 1780.00,
    "audToInrValue": 5340.00,
    "gbpToInrValue": 2060.00,
    "kwdToInrValue": 5340.00
   }
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
   Here devanurag is the docker hub username. Then run this command in maven build… of eclipse
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
kubectl apply -f mysqldb-configmap.yaml
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
kubectl get pod <pod-name> --watch
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

For removing all the files locally from GKE
```bash
rm *
```

## References
1) https://openliberty.io/guides/kubernetes-intro.html#tearing-down-the-environment
2) https://dev.to/musolemasu/deploy-a-mysql-database-server-in-kubernetes-static-dpc
3) https://www.kindsonthegenius.com/deploy-springboot-with-mysql-to-kubernetes-minikube-step-by-step-tutorial/
4) https://github.com/Java-Techie-jt/springboot-crud-k8s
5) https://loft.sh/blog/kubernetes-persistent-volumes-examples-and-best-practices/
6) https://medium.com/globant/kubernetes-deployment-deploying-mysql-databases-on-the-gke-8fa675d3d8a
7) https://www.youtube.com/watch?v=qmDzcu5uY1I
8) https://medium.com/@dayan888/sample-java-web-system-on-kubernetes-e52069390916


sql query to insert dummy data into the database
```bash 
   insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to) values(1001, 82, 'USD', 'INR');
   insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to) values(1002, 89, 'EUR', 'INR');
   insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to) values(1003, 55, 'AUD', 'INR');
   insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to) values(1004, 103, 'GBP', 'INR');
   insert into currency_exchange (conversion_id, conversion_value, conversion_from, conversion_to) values(1005, 267, 'KWD', 'INR');
```

## License
[MIT](https://choosealicense.com/licenses/mit/)