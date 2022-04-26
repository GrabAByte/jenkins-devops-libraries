#!/usr/bin/env groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)
  sh("""
      set +x
      docker login -u AWS -p \$(aws --region ${var.region} ecr get-login-password) ${var.registry}
      set -x
    """)

}
