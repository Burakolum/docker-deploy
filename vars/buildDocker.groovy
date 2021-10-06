#!/usr/bin/env groovy

def call(String registry){   
   sh 'sudo docker build -t $registry:$BUILD_NUMBER . '
   sh 'docker tag $registry:$BUILD_NUMBER docker.io/$registry:$BUILD_NUMBER'
}
