#!/usr/bin/groovy

def call() {

  def resource = libraryResource "devops/grababyte/manifest.yml"
  def manifest = readYaml (text: resource)

  return manifest

}
