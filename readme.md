##genmock: simulate SOAP Reqests/Responses to CA-GEN EESSI Service

#Build

On a Windows console from the project root directory, run the following command file. This will create
a docker image and push it to Nexus AMS image registry. You must have installed Docker Desktop in Windows. 

``` 
build-run-amsdev.bat build
```


#Deploy

From the project root dir, run the following in a Windows shell. This will deploy the GenMock server to the AMS K8S cluster in namespace `env-dev`. 

``` 
build-run-amsdev.bat deploy
```

#Test

* The genmock ReST server can be tested by opening this link in a browser: [https://env-dev.amsit.dev/__admin/](https://env-dev.amsit.dev/__admin/)
* You can also check the EESSI_SL liveness probe by opening this link in a browser: [https://env-dev.amsit.dev/EESSI_SL/isAlive](https://env-dev.amsit.dev/EESSI_SL/isAlive).
As a result in the HTML table, you should see ```CA:GEN Server connection | http://genmock.env-dev.svc.cluster.local:8089/LSEESSIV/EESSI_S_ANTWORT_TYP_VALUES  | OK```
* You may open the EESSI user interface link in a browser:  [https://env-dev.amsit.dev/eessi/index.html?SsoToken=Yce0WcVskR5f](https://env-dev.amsit.dev/eessi/index.html?SsoToken=Yce0WcVskR5f). 
The result should be the start page of EESSI with some items in the list "Neue Aktivitäten". (note that this might take a while). 
 