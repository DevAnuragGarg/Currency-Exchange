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