pipeline {

  agent any
  stages {

    stage('camp generate') {
      when { changeset "camp.yml" }
      steps {
        sh 'camp generate -d . --all'
      }
    }
    stage('camp realize') {
      when { changeset "camp.yml" }
      steps {
        sh 'camp realize -d .'
      }
    }
    stage('execute tests') {
      when { changeset "camp.yml" }
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
