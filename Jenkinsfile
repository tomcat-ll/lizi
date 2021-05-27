pipeline {
    agent any
    environment{
    //项目名
     project_name='lizi'
    //仓库地址
     github='https://github.com/tomcat-ll/lizi.git'
    //jenkins远程服务器
     server_name='lilei-test'
    sever_port = '10000'}
    stages {
        stage(‘pipeline开始拉取‘) {
        steps{
         echo "清理workspace"
          sh("ls -al ${env.WORKSPACE}")
          deleteDir()  // clean up current work directory
          sh("ls -al ${env.WORKSPACE}")
                  echo "开始拉取"
                 checkout([$class: 'GitSCM', branches: [[name: '*/'+env.BRANCH_NAME]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'b1ef8492-1493-4a99-bc34-128736e69203', url: "${github}"]]])

        }
        }
         //stage(‘编译安装，子工程‘) {
                 // sh "mvn -f service_common clean install "
               // }
         stage(‘pipeline编译打包‘) {
         steps{
                          echo "开始编译打包1111"
                          sh "mvn clean package dockerfile:build"

                          //vue项目打包  npm run build
                          //前端构建docker build –t fontApp:v1.0
                          //k8s启动yml文件
                          echo "上传镜像"
                          sh "docker tag ${project_name}:latest 192.168.5.101:85/library/${project_name}:latest "
                          echo "镜像推送harbor"
                          //def harbor_auth="6d69019c-b8e6-49a8-8563-1f81f9da8050"
                          withCredentials([usernamePassword(credentialsId: '6d69019c-b8e6-49a8-8563-1f81f9da8050', passwordVariable: 'password', usernameVariable: 'username')]) {
                              // some block
                              //登录harbor
                              sh " docker login -u ${username} -p ${password} 192.168.5.101:85  "
                              //镜像上传
                              sh "docker push 192.168.5.101:85/library/${project_name}:latest"
                              sh  "echo 镜像上传成功"
                          }
                          //部署
                        }
}

        stage("远程拉取发布"){
        steps{
        script{
        if(env.BRANCH_NAME == 'master'){
            /*  sshPublisher(publishers: [sshPublisherDesc(configName: "${server_name}", transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy.sh ${sever_port} ${project_name}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
             sshPublisher(publishers: [sshPublisherDesc(configName: '102_server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy.sh ${sever_port} ${project_name}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
 */
            /*  withDockerContainer("192.168.5.101:85/library/lizi:latest"){
                                   sh "rancher  login https://192.168.5.104/v3 --token token-p8fcx:xfj49wkbg2p4lqtdl99kqr4b67gr45lg6jqgqb7cbskhvbzjx75rsf \
                                           --skip-verify --context c-jkdqp:p-zbvs4"
                                sh "rancher kubectl rollout restart deployment/lizi --namespace nginx"
                              } */
         sshPublisher(publishers: [sshPublisherDesc(configName: '103_server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy2.sh ${project_name} ${sever_port}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
        }else{
         sshPublisher(publishers: [sshPublisherDesc(configName: "${server_name}", transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: "/usr/local/jenkins/deploy2.sh ${project_name} ${sever_port} ${sever_port}", execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
        }

        }
        }
        }
        }
}