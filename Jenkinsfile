pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                docker.image('maven:3.8.1-jdk-11').inside {
                    sh 'mvn test'
                }
            }
        }
    }
}