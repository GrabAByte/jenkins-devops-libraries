#!/usr/bin/groovy
def call() {

  def String dockerAgent
  def Integer buildNumber = env.BUILD_NUMBER.toInteger()

  if (buildNumber % 2 == 0) {
    dockerAgent = "even"
  } else {
    dockerAgent = "odd"
  }

  return dockerAgent
}
