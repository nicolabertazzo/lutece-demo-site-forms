pipeline {
  agent any
  stages {
    stage('camp generate') {
      steps {
        sh 'camp generate -d . --all'
      }
    }
    stage('camp realize') {
      steps {
        sh 'camp realize -d .'
      }
    }
    stage('camp execute') {
      steps {
        sh 'camp execute -c config.ini'
      }
    }
  }
}