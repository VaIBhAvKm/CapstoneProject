pipeline {
    agent any

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                git branch: 'main', url: 'https://github.com/VaIBhAvKm/CapstoneProject.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Add your build commands here, e.g., Maven, Gradle, etc.
                sh './build.sh'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Add your test commands here
                sh './run-tests.sh'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add deployment commands here
                sh './deploy.sh'
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
            cleanWs()
        }
    }
}
