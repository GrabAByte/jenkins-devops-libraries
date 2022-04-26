#!/usr/bin/groovy

def call(List collections = []) {

  allCollections = collections.join(' ')
  sh("ansible-galaxy collection install ${allCollections}")

}
