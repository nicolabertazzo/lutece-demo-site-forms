pipeline {
  agent any
  stages {
    stage('camp generate') {
      steps {
        sh 'camp generate -d . --all'
        script {
          def changeLogSets = currentBuild.changeSets
          for (int i = 0; i < changeLogSets.size(); i++) {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
              def entry = entries[j]
              echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
              def files = new ArrayList(entry.affectedFiles)
              for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                echo "  ${file.editType.name} ${file.path}"
              }
            }
          }
          changeLogSets
        }

      }
    }
    stage('camp realize') {
      steps {
        sh 'camp realize -d .'
      }
    }
    stage('execute tests') {
      steps {
        withMaven(maven: 'maven3') {
          sh '''cd lutece-form-test
mvn clean test -DcampOutPath="${WORKSPACE}/camp/out"'''
        }

      }
    }
  }
}