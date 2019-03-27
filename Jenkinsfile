pipeline {

  agent any
  stages {

    stage('camp generate') {
      when { changeset "camp.yml" }
      steps {
        script{
          if (fileExists('out')) {
            sh 'git rm -r out'
          } 
        }
        sh 'camp generate -d . --all'
      }
    }
    stage('camp realize') {
      when { changeset "camp.yml" }
      steps {
        sh 'camp realize -d .'
        sh 'git add out'
        sh 'git commit -m "updated camp configurations"'
        // CREDENTIALID
        withCredentials([usernamePassword(credentialsId: 'github-user-password', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
          // REPOSITORY URL  
          sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@${GIT_URL}')
        }
        script{
          currentBuild.result = 'SUCCESS'
          return
        }
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
   environment {
    GIT_URL = sh (script: 'git config remote.origin.url', returnStdout: true).trim().replaceAll('https://','')
  }
}

