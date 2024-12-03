pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "pizarra-backend"
        DOCKER_TAG = "latest"
        DOCKER_REGISTRY = "aucseng"
    }

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

        stage('Build Docker Image') {
            agent {
                docker {
                    image 'docker:dind'
                    args '--privileged --user root'  // Use args to specify Docker options
                    reuseNode true
                }
            }
            steps {
                // Build the Docker image from the Dockerfile
                sh """
                dockerd --host=unix:///var/run/docker.sock &
                sleep 5
                docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG} .
                docker images
                """
            }
        }
    }
}