#!/usr/bin/env groovy
pipeline {
  agent {
    dockerfile {
      dir 'docker'
      label 'local-node'
      additionalBuildArgs '--build-arg USER_ID=$(id -u) --build-arg USER_GID=$(id -g)'
      args '-h maven-build'
    }
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
