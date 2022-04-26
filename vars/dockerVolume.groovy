#!/usr/bin/groovy

import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)

  sh("""
      if [ ! -d ${var.directory} ]; then
        mkdir -p ${var.directory} && chown -R ${var.user}:${var.user} ${var.directory}"
      fi
    """)

}
