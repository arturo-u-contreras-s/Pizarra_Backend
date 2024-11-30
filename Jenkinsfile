pipeline {
    agent any

    stages {
        stage('Test') {
            agent {
                docker {
                    image 'maven:latest'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'maven:latest'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }
}