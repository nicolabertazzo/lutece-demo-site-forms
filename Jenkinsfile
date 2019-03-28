pipeline {

  parameters {
      booleanParam(name: 'config_changed', defaultValue: false )
  }
  agent any
  stages {
  stage('check changelog') {
      steps {
      script {
          env.config_changed = false;
          echo "config_changed value =  ${env.config_changed}"
          def changeLogSets = currentBuild.changeSets
          for (int i = 0; i < changeLogSets.size(); i++) {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
              def entry = entries[j]
              def files = new ArrayList(entry.affectedFiles)
              for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                if (file.path == "camp.yml" || file.path.startsWith("template")){
                  env.config_changed = true;
                }
              }
            }
          }
          echo "config_changed value =  ${env.config_changed}"
        }
      }  
    }

    stage('camp generate') {
      when { expression {env.config_changed == 'true'}}
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
      when { expression {env.config_changed == 'true'}}
      steps {
        sh 'camp realize -d .'
      }
    }
    stage ('pull request') {
      when { expression {env.config_changed == 'true'}}
      steps{
        sh 'git checkout -b amplifyconf-${GIT_BRANCH}-${BUILD_NUMBER}'
        sh 'git add out'
        sh 'git commit -m "added camp configurations"'
        // CREDENTIALID
        withCredentials([usernamePassword(credentialsId: 'github-user-password', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
          // REPOSITORY URL  
          sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@${GIT_URL}')
          // withEnv(['GITHUB_USER=${GIT_USERNAME}','GITHUB_PASSWORD=${GIT_PASSWORD}']) {
          // sh 'hub pull-request -m "Amplify pull request from build ${BUILD_NUMBER} on ${GIT_BRANCH}"'
          // }
        }
      }
    }

    stage('execute tests') {
      steps {
        when { expression {false==false}}
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

