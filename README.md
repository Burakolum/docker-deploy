# How To Use Shared Libraries In A Jenkins Pipeline?

## Configure Shared Library in Jenkins

STEP 1- Click on manage jenkins on the left side menu. <br/>
STEP 2- Click on configure system. Scroll down until you find the Global Pipeline Libraries section.<br/>
STEP 3- Under the Library section, configure values as below.<br/>
<br/>
![library](https://user-images.githubusercontent.com/87417208/136165796-c4c59a9d-d0a9-4769-977a-23e749bd5afd.png)

## Referring Jenkins Shared Library from Pipeline
`@Library ("test-library@branch") _` <br/>
#### Example
```
@Library ("test-library@master") _

advancedPipeline(scmUrl: 'https://github.com/Burakolum/jenkins-Library.git',
                   registry: "burakolum/burak",
                   registryCredential: 'burakolum-dockerhub',
                   dockerImage: '' )
```                   

## Also, can add your custom steps

Each of your custom steps is a different .groovy file inside your vars/ directory.<br/>

Create a file for your custom step.For example,
`buildDocker.groovy`
```
#!/usr/bin/env groovy

def call(String registry){   
   sh 'sudo docker build -t $registry:$BUILD_NUMBER . '
   sh 'docker tag $registry:$BUILD_NUMBER docker.io/$registry:$BUILD_NUMBER'
}
```
You can also add parameters to your method - the example above has one parameter called `registry`.<br/>

![customstep](https://user-images.githubusercontent.com/87417208/136175092-5bde53a8-528f-4567-82d0-3185a1532e94.png) <br/>
To use the shared library in a pipeline, you add @Library('your-library-name') to the top of your pipeline definition

if you would like to see other custom steps you could look vars directory
