pipeline {
    agent any

    environment {
        DOCKERHUB_REPO_FRONTEND = 'sh-vue'               
        DOCKERHUB_REPO_BACKEND = 'sh-spring'              
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'  
        DOCKERHUB_USER = 'tigerwk'                       
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/asdsasdfsd/SecondHand', branch: 'cicd'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend-vue') {
                    sh 'npm install'   
                    sh 'npm run build' 
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('SecondHand') {               
                    sh 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                script {
                    dir('frontend-vue') {
                        def frontendImage = docker.build("${DOCKERHUB_USER}/${DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    dir('SecondHand') {
                        def backendImage = docker.build("${DOCKERHUB_USER}/${DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    def dockerVersion = sh(script: 'docker --version', returnStdout: true).trim()
                    echo "Docker Version: ${dockerVersion}"
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        echo 'Logged in to Docker Hub successfully'
                    }
                }
            }
        }

        stage('Push Frontend Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        def frontendImage = docker.image("${DOCKERHUB_USER}/${DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID}")
                        frontendImage.push()
                    }
                }
            }
        }

        stage('Push Backend Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKERHUB_CREDENTIALS}") {
                        def backendImage = docker.image("${DOCKERHUB_USER}/${DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID}")
                        backendImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                sh "docker rmi ${DOCKERHUB_USER}/${DOCKERHUB_REPO_FRONTEND}:${env.BUILD_ID} || true"
                sh "docker rmi ${DOCKERHUB_USER}/${DOCKERHUB_REPO_BACKEND}:${env.BUILD_ID} || true"
            }
        }
    }
}
