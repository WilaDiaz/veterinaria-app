pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=veterinaria-app -Dsonar.projectName="Veterinaria App"'
                }
            }
        }
    }
}
