#!/usr/bin/groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)

  dir("${var.directory}") {
    sh("ansible-galaxy install --force -r requirements.yml -p roles")
  }

}
