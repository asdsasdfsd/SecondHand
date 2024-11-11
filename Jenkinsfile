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
                script {
                    try {
                        sh 'mkdir -p ./reports'
                        
                        dependencyCheck additionalArguments: '--scan ./SecondHand --format HTML', 
                                        odcInstallation: 'Dependency-Check', 
                                        outDir: './reports', 
                                        projectName: 'MyProject'
                    } catch (Exception e) {
                        echo "Error during vulnerability scan: ${e.message}"
                        currentBuild.result = 'FAILURE'
                        throw e  
                    }
                }
            }
        }

        stage('Publish Dependency-Check Report') {
            steps {
                script {
                    def reportPath = '/var/lib/jenkins/workspace/wke-pipeline/./dependency-check-report.html'
                    echo "Checking for report at: ${reportPath}"

                    if (fileExists(reportPath)) {
                        echo "Report found. Publishing..."
                        publishHTML(target: [
                            reportDir: '/var/lib/jenkins/workspace/wke-pipeline/.',
                            reportFiles: 'dependency-check-report.html', 
                            reportName: 'Vulnerability Report'
                        ])
                    } else {
                        echo "Dependency-Check report not found at ${reportPath}"
                        currentBuild.result = 'FAILURE' 
                    }
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

        stage('Scalability Testing') {
            steps {
                sh 'mkdir -p reports/scalability'
                
                withEnv(['PATH+JMETER=~/apache-jmeter-5.4.1/bin']) {
                    sh 'jmeter -n -t Test/scalability_test.jmx -l results.jtl -e -o reports/scalability'
                }
                
                publishHTML(target: [
                    reportDir: 'reports/scalability',
                    reportFiles: 'index.html',
                    reportName: 'Scalability Test Report'
                ])
            }
        }

        stage('Performance Testing') {
            steps {
                sh 'mkdir -p reports'
                
                sh 'k6 run --out json=reports/performance_test_result.json Test/performance_test.js'
                
                publishHTML(target: [
                    reportDir: 'reports',
                    reportFiles: 'performance_test_result.json',
                    reportName: 'Performance Test Report'
                ])
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
