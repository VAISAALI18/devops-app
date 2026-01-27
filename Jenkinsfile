// Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    
    options {
        skipDefaultCheckout()
    }
 
    stages {
        stage('Checkout Source') {
            steps {
                git url: 'git@github.com:VAISAALI18/devops-app.git',
                    credentialsId: 'github-ssh-key',
                    branch: 'main'
            }
        }
 
        stage('Build and Test') {
            steps {
                echo 'Starting Maven Build and Running Unit Tests'
                // Execute Maven build and tests
                sh 'mvn clean package'
            }
        }
 
        stage('Quality Gates and Archival') {
            steps {
                echo 'Publishing Test Results and Archiving Artifacts'
                // Publish Test Results (Requires JUnit Plugin)
                junit 'target/surefire-reports/*.xml'
                // Archive the build artifact
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline finished.'
        }
        failure {
            echo 'CI Pipeline Failed due to Build or Test Failure.'
        }
    }
}
