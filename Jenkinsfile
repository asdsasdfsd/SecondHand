pipeline {
    agent any

    environment {
        DOCKERHUB_REPO_FRONTEND = 'SH-Vue'   
        DOCKERHUB_REPO_BACKEND = 'SH-Spring'   
        DOCKERHUB_USER = 'tigerwk'  
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/asdsasdfsd/SecondHand', branch: 'cicd'
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                script {
                    dir('frontend-vue') {
                        def frontendImage = docker.build("${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    dir('SecondHand') {
                        def backendImage = docker.build("${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USER')]) {
                    script {
                        sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USER --password-stdin"
                    }
                }
            }
        }

        stage('Push Frontend Image to Docker Hub') {
            steps {
                script {
                    def frontendImage = docker.image("${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID}")
                    frontendImage.push()
                }
            }
        }

        stage('Push Backend Image to Docker Hub') {
            steps {
                script {
                    def backendImage = docker.image("${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID}")
                    backendImage.push()
                }
            }
        }
    }

    post {
        always {
            script {
                sh "docker rmi ${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID} || true"
                sh "docker rmi ${env.DOCKERHUB_USER}/${env.DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID} || true"
            }
        }
    }
}