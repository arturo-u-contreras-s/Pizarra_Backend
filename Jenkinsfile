pipeline {
    agent any

    stages {
        stage('Test') {
            agent {
                docker {
                    image 'maven:3.8.1-jdk-11'
                }
            }
            steps {
                sh 'mvn test'
            }
        }
    }
}