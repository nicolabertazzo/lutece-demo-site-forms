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
        sh 'echo "camp realize"'
      }
    }
  }
}