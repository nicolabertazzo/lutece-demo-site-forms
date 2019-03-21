pipeline {
  agent any
  stages {
    stage('camp generate') {
      steps {
        sh 'docker run -v $(pwd):/camp/workspace fchauvel/camp:dev camp generate -d workspace --all'
      }
    }
    stage('camp realize') {
      steps {
        sh 'echo "camp realize"'
      }
    }
  }
}