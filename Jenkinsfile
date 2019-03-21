pipeline {
  agent any
  stages {
    stage('camp generate') {
      steps {
        sh 'docker run -v $(pwd):/camp/workspace fchauvel/camp:dev camp generate -d workspace/camp --all'
      }
    }
    stage('camp realize') {
      steps {
        sh 'echo "camp realize"'
      }
    }
  }
}