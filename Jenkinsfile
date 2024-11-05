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
                sh 'mvn clean compile -DskipTests'

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

        stage("Clean- Package"){
                 steps {
                 sh "mvn clean package"}
                 }

                 stage("Nexus") {
                 steps {
                 sh "mvn deploy:deploy-file -DgroupId=tn.esprit -DartifactId=tp-foyer -Dversion=5.0.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://192.168.50.4:8081/repository/maven-releases/ -Dfile=target/tp-foyer.war -DskipTests"
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
