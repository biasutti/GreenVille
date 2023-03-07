#!/usr/bin/env groovy
pipeline {
  agent {
    label 'local-node'
  }

  options {
    timeout(time: 1, unit: 'HOURS')
  }

  environment {
    MVN = 'mvn -B '
    PROFILE = ' '
  }

  /*parameters {
  }*/

  stages {
    stage("workspace") {
      steps {
        echo "BRANCH_NAME = ${env.BRANCH_NAME}"
        echo "GIT_BRANCH  = ${env.GIT_BRANCH}"
        echo "GIT_LOCAL_BRANCH  = ${env.GIT_LOCAL_BRANCH}"
        echo "show environment"
        sh "env"
      }
    }

    stage("Build") {
      steps {
        script {
          sh "${MVN} clean install"
        }
      }
    }
  }

  post {
    always {
      sh "${MVN} clean"
    }
  }
}
