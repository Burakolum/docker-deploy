#!/usr/bin/env groovy

def call(String repoUrl){
  git url: "${repoUrl}"
}
