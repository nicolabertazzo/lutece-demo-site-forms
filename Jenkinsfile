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
    stage('execute tests') {
      steps {
        withMaven(maven: 'MVN3', jdk: 'JDK8') {
          sh '''cd lutece-form-test
          mvn clean test -DcampOutPath="${WORKSPACE}/out"'''
        }
      }
    }
  }
  post {
    always {
      junit 'lutece-form-test/target/surefire-reports/*.xml'
    }
  }
}
