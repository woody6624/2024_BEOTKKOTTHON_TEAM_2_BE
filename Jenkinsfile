pipeline {
    agent any
    tools {
        nodejs "default"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        //후에 자바 스프링 관련 추가
        //window는 sh -> bat 변경 필요
        stage('Install Dependencies') {
            steps {
                dir('./muckkitlist_nest'){
                    script {
                        sh 'npm install'
                    }
                }
            }
        }

        stage('Build And Deploy') {
            steps {
                dir('./muckkitlist_nest'){
                    script {
                        sh 'npm run start:dev'
                    }
                }
            }
        }
    }
}