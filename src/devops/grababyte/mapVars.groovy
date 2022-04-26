#!/usr/bin/groovy
package devops.grababyte

Map paramsConverter(body) {
  Map var = [:]

  if (body instanceof Closure) {
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = var
    body()
  }
  else if (body instanceof Map) {
    var = body
  }
  else {
    currentBuild.result = 'ABORTED'
    error("Aborting the build. Incorrect Data Type provided to the mapVars Function.")
  }

  return var
}
