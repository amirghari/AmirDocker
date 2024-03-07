pipeline {
    agent any

    environment {
        PATH = "${env.PATH}:/usr/local/bin" // Update the PATH to include the directory of cmd.exe
        // Define Docker Hub credentials ID
        //DOCKERHUB_CREDENTIALS_ID = 'f68822ee-5118-4c1e-ad4f-51e45244cea7'
        // Define Docker Hub repository name
        DOCKERHUB_REPO = 'amirghari/amirducker'
        // Define Docker image tag
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/amirghari/AmirDocker.git'
            }
        }

        stage('Build') {
            steps {
                withMaven(maven: 'MAVEN_HOME') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                // Add steps to run tests
                withMaven(maven: 'MAVEN_HOME') {
                    sh 'mvn test'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                // Build Docker image
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                // Push Docker image to Docker Hub
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u eliasp532 -p ${dockerhubpwd}'
                        sh 'docker push eliasp532/inclassexercise7_3'
                    }
                }
            }
        }
    }

    post {
        success {
            // Publish JUnit test results
            junit '**/target/surefire-reports/TEST-*.xml'

            // Generate JaCoCo code coverage report
            jacoco(execPattern: '**/target/jacoco.exec')
        }
    }
}
