#!/usr/bin/env groovy

def call(def registryCredential, def registry){
   withDockerRegistry(credentialsId: "${registryCredential}" ) {
      sh 'sudo docker push "${registry}":$BUILD_NUMBER'
   }
}

