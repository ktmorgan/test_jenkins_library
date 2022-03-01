def call(body) {
  def dslParams = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = dslParams
  body()

  env.FOO = currentBuild.fullProjectName ?: dslParams?.baz

  pipeline {
    agent any

    stages {
      stage('First') {
        steps {
          sh 'Project Name: $FOO'
        }
      }
    }
  }
}