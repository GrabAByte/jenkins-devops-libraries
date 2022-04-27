#!/usr/bin/env groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)
  sh("docker rmi -f \$(docker images | grep ${var.image} | awk '{ print \$3 }' | uniq)")

}
