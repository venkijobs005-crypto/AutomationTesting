pipeline {
    agent any

    tools {
        jdk 'JDK11'
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/your/repo.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Selenium Grid Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'

                publishHTML(target: [
                    reportName: 'Cucumber HTML Report',
                    reportDir : 'target/cucumber-html-report',
                    reportFiles: 'index.html'
                ])
            }
        }
    }
}
