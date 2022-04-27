#!/usr/bin/groovy
def call() {

  def String jenkinsAgent
  def Integer buildNumber = env.BUILD_NUMBER.toInteger()

  if (buildNumber % 2 == 0) {
    jenkinsAgent = "even"
  } else {
    jenkinsAgent = "odd"
  }

  return jenkinsAgent
}
