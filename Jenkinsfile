pipeline {
    agent any
    stages {
        stage('Test'){
            steps{
                 bat 'mvn test'
             }
            post {
                failure {
                    script {
                        currentBuild.result = 'FAILED'
                    }
                }
            }
        }
 
        
        stage('Clean Up') {
            steps {
                bat 'rmdir /s /q target'
            }
        }
    }
}
