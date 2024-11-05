pipeline {
    agent any
    
    stages {
        stage('GIT') {
            steps {
                // Clone the repository from Git
                git url: 'https://github.com/salmaa1/DEVOPS.git', branch: 'master', credentialsId: 'salmaJenkins'
            }
        }
        
        stage('Build with Maven') {
            steps {
                // Run Maven build command
                sh 'mvn clean install'

            }
        }

         stage("Quality") {
                    steps {
                        withCredentials([string(credentialsId: 'SonarQubeToken', variable: 'SONAR_TOKEN')]) {
                            sh 'mvn sonar:sonar -Dsonar.login=$SONAR_TOKEN'
                        }
                    }
                }

          stage('JUnit / Mockito') {
                     steps {
                         // Run tests using Maven
                         sh 'mvn test'
                     }
                 }
    }
    
    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
