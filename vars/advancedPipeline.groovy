#!/usr/bin/env groovy

def call(Map pipelineParams) {
pipeline { 
    agent any 
    stages { 
        stage('Cloning our Git') { 
            steps { 
                git url: pipelineParams.scmUrl
            }
        } 
        stage('Building our image') { 
            steps { 
                script { 
                    pipelineParams.dockerImage = docker.build pipelineParams.registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Deploy our image') { 
            steps { 
                script { 
                    docker.withRegistry( '', pipelineParams.registryCredential ) { 
                        pipelineParams.dockerImage.push() 
                    }
                } 
            }
        }        
        stage('Docker run') { 
            steps { 
                sh "docker run -d $pipelineParams.registry:$BUILD_NUMBER" 
            }
        } 
       
        stage('Cleaning up') { 
            steps { 
                sh "docker rmi -f $pipelineParams.registry:$BUILD_NUMBER" 
            }
        } 
    }
}
}
