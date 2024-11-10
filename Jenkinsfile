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
                git url: 'https://github.com/asdsasdfsd/SecondHand', branch: 'wke'
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend-vue') {
                    sh 'node -v'
                    sh 'npm -v'
                    sh 'npm update'
                    sh 'rm -rf node_modules'
                    sh 'npm cache clean --force'
                    sh 'npm install'   
                    sh 'npm run build --verbose' 
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

        stage('Vulnerability Scan') {
            steps {
                dependencyCheck additionalArguments: '--scan ./SecondHand', 
                                odcInstallation: 'Dependency-Check', // 确保插件安装路径名称匹配
                                outDir: './reports', 
                                projectName: 'MyProject'
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
                    try {
                        docker.withRegistry('https://registry-1.docker.io/v2/', "${DOCKERHUB_CREDENTIALS}") {
                            echo 'Logged in to Docker Hub successfully'
                        }
                    } catch (Exception e) {
                        echo "Error during login: ${e.message}"
                        currentBuild.result = 'FAILURE'
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

                // echo 'Archiving and publishing the reports...'
                // archiveArtifacts artifacts: 'reports/*', allowEmptyArchive: true
                
                // publishHTML(target: [
                //     reportDir: 'reports',
                //     reportFiles: 'dependency-check-report.html',
                //     reportName: 'Vulnerability Report'
                // ])
            }
        }
    }
}
