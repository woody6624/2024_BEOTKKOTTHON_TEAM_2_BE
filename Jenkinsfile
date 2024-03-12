pipeline {
    agent any
    
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
                        sh '/usr/local/bin/npm install'
                    }
                }
            }
        }

        stage('Build And Deploy') {
            steps {
                dir('./muckkitlist_nest'){
                    script {
                        sh '/usr/local/bin/npm run start:dev'
                    }
                }
            }
        }
    }
}