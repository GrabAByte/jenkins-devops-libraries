#!/usr/bin/env/groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)

  dir("${var.directory}") {
    $class: 'GitSCM',
    branches: [[name: "origin/${var.branch}"]],
    userRemoteConfigs: [[
      credentialsId: "${var.credentialsId}",
      url: "${var.repository}"
    ]]
  }
}
