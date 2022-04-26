#!/usr/bin/env groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)
  sh("docker logout ${var.registry}")

}
