pipeline {
    agent any

    tools {
        maven 'Maven'            // Jenkins → Tools → Maven name
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
                echo 'Running SonarQube Analysis'
                script {
                    def scannerHome = tool 'SonarScanner'   // Jenkins → Tools → SonarQube Scanner

                    withSonarQubeEnv('My Sonar Server') {
                        sh """
                          ${scannerHome}/bin/sonar-scanner \
                          -Dsonar.projectKey=devops-app \
                          -Dsonar.projectName=devops-app \
                          -Dsonar.sources=src/main/java \
                          -Dsonar.tests=src/test/java \
                          -Dsonar.java.binaries=target/classes
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Waiting for SonarQube Quality Gate result...'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
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
            echo ' Pipeline succeeded: Quality Gate PASSED and build completed.'
        }
        failure {
            echo ' Pipeline FAILED: Build error or Quality Gate FAILED.'
        }
    }
}
