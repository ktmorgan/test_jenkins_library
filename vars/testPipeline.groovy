def call(body) {
  def dslParams = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = dslParams
  body()

  env.FOO = $STAGE_NAME ?: dslParams?.baz

  pipeline {
    agent any

    stages {
      stage('First') {
        steps {
          sh 'Stage Name: $FOO'
        }
      }
    }
  }
}