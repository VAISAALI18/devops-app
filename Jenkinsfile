pipeline {
    agent any
    
    tools {
        maven 'Maven' // Ensure Maven is configured in Global Tool Configuration
    }
    
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
        
        stage('Build') {
            steps {
                echo 'Starting Maven Build'
                sh 'mvn clean compile'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube Static Code Analysis'
                withSonarQubeEnv('My Sonar Server') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        
        stage('Unit Tests') {
            steps {
                echo 'Running Unit Tests'
                sh 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Packaging Application'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo 'Archiving Build Artifacts'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'Pipeline succeeded! Code analyzed and built successfully.'
        }
        failure {
            echo 'Pipeline Failed - Check logs for details.'
        }
    }
}
