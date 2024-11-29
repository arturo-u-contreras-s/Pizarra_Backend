pipeline {
    agent any

    stages {
        stage('Test') {
            agent {
                docker {
                    image 'maven:latest'
                }
            }
            steps {
                sh 'mvn test'
            }
        }
    }
}