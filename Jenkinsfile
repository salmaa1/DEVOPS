pipeline {
    agent any
    
    stages {
        stage(' GIT Clone Repository') {
            steps {
                // Clone the repository from Git
                git url: 'https://github.com/salmaa1/DEVOPS.git', branch: 'master', credentialsId: 'salmaJenkins'
            }
        }
        
        stage('Build with Maven') {
            steps {
                // Run Maven build command
                sh 'mvn clean compile'
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
