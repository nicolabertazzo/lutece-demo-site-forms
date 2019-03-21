pipeline {
  agent any
  stages {
    stage('camp generate') {
      steps {
        sh 'docker run hello-world'
      }
    }
    stage('camp realize') {
      steps {
        sh 'echo "camp realize"'
      }
    }
  }
}