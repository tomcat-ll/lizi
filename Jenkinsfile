pipeline {
    agent any
    stages {
        stage(‘pipeline开始拉取‘) {
        steps{
                  echo "开始拉取"
                  if (env.BRANCH_NAME == 'devlop') {
                  step{
                 checkout([$class: 'GitSCM', branches: [[name: '*/devlop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '2938767e-a4c5-43e7-928c-cee7103b3121', url: 'https://github.com/tomcat-ll/lizi.git']]])
                } } else {
                step{
                 checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '2938767e-a4c5-43e7-928c-cee7103b3121', url: 'https://github.com/tomcat-ll/lizi.git']]])
                } }
        }
        }
         //stage(‘编译安装，子工程‘) {
                 // sh "mvn -f service_common clean install "
               // }

         stage(‘pipeline编译打包‘) {
         steps{
                          echo "开始编译打包"
                          sh "mvn  clean package dockerfile:build"
                          echo "上传镜像"
                          sh "docker tag lizi:latest 192.168.5.101:85/library/lizi:latest "
                          echo "镜像推送harbor"
                          //def harbor_auth="6d69019c-b8e6-49a8-8563-1f81f9da8050"
                          withCredentials([usernamePassword(credentialsId: '6d69019c-b8e6-49a8-8563-1f81f9da8050', passwordVariable: 'password', usernameVariable: 'username')]) {
                              // some block
                              //登录harbor
                              sh " docker login -u ${username} -p ${password} 192.168.5.101:85  "
                              //镜像上传
                              sh "docker push 192.168.5.101:85/library/lizi:latest"
                              sh  "echo 镜像上传成功"
                          }
                          //部署

sshPublisher(publishers: [sshPublisherDesc(configName: '103_server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy.sh  192.168.5.101:85 library lizi latest 10000", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
sshPublisher(publishers: [sshPublisherDesc(configName: 'lilei-test', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy.sh  192.168.5.101:85 library lizi latest 10000", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])


}
                       }
}
}