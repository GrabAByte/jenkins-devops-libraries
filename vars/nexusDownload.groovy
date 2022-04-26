#!/usr/bin/groovy

import devops.grababyte.mapVars

def call(def body = [:]) {

  Map var = new mapVars().paramsConverter(body)

  sh("""
    if [ ! -d ${var.destination} ]; then
      mkdir -p ${var.destination}
    fi
  """)

  if(var.anonymous) {
    var.components.each { item ->
      sh("""
         curl ${var.url}/${var.namespace}/${var.tag}/${item} \
           -o ${var.destination}/${item}
        """)
    }
  }
  else {
    withCredentials([usernamePassword(credentialsId: "${var.credentialsId}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
      var.components.each { item ->
        sh("""
           curl -v -u $USERNAME:$PASSWORD \
             ${var.url}/${var.namespace}/${var.tag}/${item} \
             -o ${var.destination}/${item}
          """)
      }
    }
  }
}
