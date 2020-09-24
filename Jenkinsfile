pipeline {
    agent any

    stages {
        stage(‘pipeline开始拉取‘) {
        steps{
                  echo "开始拉取"
                 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '2938767e-a4c5-43e7-928c-cee7103b3121', url: 'https://github.com/tomcat-ll/lizi.git']]])
        }
        }
         //stage(‘编译安装，子工程‘) {
                 // sh "mvn -f service_common clean install "
               // }

         stage(‘pipeline编译打包‘) {
         steps{
                          echo "开始编译打包"
                          sh "mvn -f lizi clean package"
}
                       }
}
}