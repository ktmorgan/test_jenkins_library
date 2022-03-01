def call(body) {
  def dslParams = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = dslParams
  body()

  env.FOO = env.FOO ?: dslParams?.foo

  pipeline {
    agent any

    stages {
      stage('First') {
        steps {
          sh 'printenv'
        }
      }
    }
  }
}