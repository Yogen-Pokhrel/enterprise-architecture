# Spring using Kubernetes and docker
Using Spring boot application inside the docker container orchestrated by the kubernetes.

  

## Installation
1. Install the applications
    * [Docker](https://docs.docker.com/engine/install/)
    * [Kubernetes](https://kubernetes.io/docs/tasks/tools/)
    * [Kind](https://kind.sigs.k8s.io/docs/user/quick-start/)
 2. Start _Docker_
3. Then run the *install.sh* file.
	```bash
	sh  install.sh
	```
4. Configure _Kubernetes_ dashboard
	```bash
	kubectl  -n  kubernetes-dashboard  port-forward  svc/kubernetes-dashboard-kong-proxy  8443:443
    ```
5. Start the _Dashboard_
    > **_NOTE:_** Run only after the above code runs.
    ```bash
	kubectl  -n  kubernetes-dashboard  create  token  kubernetes-dashboard-web
	```
5. Open [link](https://localhost:8443/) and paste the token. Now you are ready to go.

## Components:
- __Spring__ - Create a rest endpoint.
- __Docker__ - Containarize the application.
- __Deployment__ - Deploy the application as a pod inside the kubernetes.
- __Horizontal Pod Scaling__ - Scale the application according to the CPU usage.
- __Node Port__ - Give access to the application from the external world.
- __Kubernetes Dashboard__ - Get live updates about how the application are doing inside kubernetes.