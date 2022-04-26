#!/usr/bin/groovy
import devops.grababyte.mapVars

def call(def body = [:]) {

  Map var = new mapVars().paramsConverter(body)

  withCredentials([usernamePassword(credentialsId: "${var.credentialsId}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

    sh("""
       curl -v -u $USERNAME:$PASSWORD \
         --upload-file ${var.sourceFilePath}/${var.sourceFileName} \
         ${var.url}/${var.namespace}/\$(date +%d-%m-%Y)-${env.BUILD_NUMBER}/${var.sourceFileName}
    """)

  }
}
