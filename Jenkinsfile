pipeline {
    agent {
        docker {
            image 'maven:3.6.0-jdk-8'
            args '-v /root/.m2:/root/.m2'
        }
    }

    environment {
        DOCKER_REPOSITORY_HOST = 'wx.ankoninc.com.cn'
        ACTIVED_PROFILE = 'dev'
        NETWORK = 'servicenet'
	GIT_REPOSITORY = 'https://github.com/tomcat-ll/lizi.git'
    }
    stages {

        stage('merge code') {
	    steps {
                sh '''git checkout dev
                      git merge master'''
            }
	}

        stage('Maven install') {
            steps {
                sh 'mvn install'
            }
        }

        stage('Sonar') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }

	stage('push code to branch dev') {
	    steps {
		withCredentials([usernamePassword(credentialsId: 'config-user', usernameVariable: 'username', passwordVariable: 'password')]){
                    sh "git push https://$username:$password@${GIT_REPOSITORY}"
                }
            }
	}

        stage('Deploy cloud-app-service-server') {
            steps {
                script {
                    env.WORKSPACE_PATH = "./cloud-app-service-server"
                    def pom = readMavenPom file: "${WORKSPACE_PATH}/pom.xml"
                    env.PROJECT_NAME = pom.artifactId
                    env.JAR_FILE_PATH = "./target/${PROJECT_NAME}-${pom.version}.jar"
                    env.PORT = pom.properties.ankonAppPort
                    env.IMAGE = "${DOCKER_REPOSITORY_HOST}/${PROJECT_NAME}:${pom.version}-${ACTIVED_PROFILE}-${BUILD_TIMESTAMP}"
                }
                sh "docker build --build-arg JAR_FILE_PATH=${JAR_FILE_PATH} --build-arg PORT=${PORT} -t ${IMAGE} ${WORKSPACE_PATH}"
                sh "docker service rm ${PROJECT_NAME} || true"
                sh "docker push ${IMAGE}"
                sh "docker service create   --name ${PROJECT_NAME} --limit-memory 500M  --with-registry-auth --replicas 1   --publish published=${PORT},target=${PORT}   --network ${NETWORK}    ${IMAGE} --spring.profiles.active=${ACTIVED_PROFILE}"
            }
        }
    }
