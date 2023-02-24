# Jenkins: jenkins-devops-libraries by GrabAByte

A central repository containing Jenkins libraries to be used for CI CD.

## Directory Layout

* `.gitlab-ci.yml` contains the CI pipeline to lint and tag the Jenkins Libraries.
* `metadata.json` contains the project name and version to tag
* `resources/devops/grababyte/*` contains supplementary files to be used in conjunction with the libraries.
* `src/devops/grababyte/*.groovy` contains classes than can be used by methods within the repository.
* `vars/*.groovy` contains the methods that be used throughout the pipelines
* `test/**/Jenkinsfile*` contains the invoking Jenkinsfiles for testing functionality

## Requirements

You will require a Jenkins Server and Agent to be provisioned for your projects.
You will need to set up this repository in your Jenkins Server's Global Library configuration. Please consult Jenkins official documentation for the steps to do this.

## Dependencies

Currently there are no additional dependencies to run these libraries.

## Lint

If you wish to manually invoke the linting, firstly install and then run the following command on linux -

```
npm install -g npm-groovy-lint
npm-groovy-lint .
```

# Manifest

It is important to note that general metadata for your code repositories and devops tooling is found at resources/devops/grababyte/manifest.yml, without this file all pipelines will break.

# Libraries
```
 * src/devops/grababyte/mapVars.groovy - Takes a map into every method
```

# Methods
## vars/ansibleCollections.groovy
```
Function: This will install any dependent ansible collections you may have

Usage:

ansibleCollections([
  'collection1', description: The list of collections to install, can be as many items as you require
  'collection2'
])
```
## vars/ansibleRequirements.groovy
```
Function: This will install the requirements specified in the requirements.yml

Usage:

ansibleRequirements(
  directory: ''  description: The path with which to run the ansible-galaxy comamnd. The recommended is the playbooks path.
)
```
## vars/ansibleRunPlaybook.groovy
```
Function: A generic method to run any ansible playbook from the playbooks repository with the required overrides

Usage:

ansibleRunPlaybook(
  inventory: '', description: The location of the ansible inventory.
  playbook: '', description: The name of the playbook to run, please do not include the file extension.
  overrides: [
    foo: 'bar' description: Any number of key value pairs specific to your ansible role which you may need to override.
  ]
)
```
## vars/assignAgent.groovy
```
Function: This will check whether the Jenkins build number id odd or even, and assign to the odd or even Jenkins Agent based on this value

Usage:

assignAgent()
```
## vars/codeCheckout.groovy
```
Function: Check out any code repository your credentialId has access to

Usage:

codeCheckout(
  directory: '', description: The directory to check the code out to
  repository: '', description: The repository to clone
  branch: '', description: The branch to clone
  credentialsId: '' description: The credentialsId which can clone from the repository
)
```
## vars/dockerCleanup.groovy
```
Function: This will remove a docker image from the build agent

Usage:

dockerCleanup(
  image: '' description: The name of the docker image to remove from the build agent
)
```
## vars/dockerLogin.groovy
```
Function: Login into an AWS ECR. Note: This method had been created because AWS requires a token to be generated for authentication.

Usage:

dockerLogin(
  region: '', description: The AWS region in which the ECR resides
  registry: '' description: The name of the ECR with which to login
)
```
## vars/dockerLogout.groovy
```
Function: Logout of an AWS ECR

Usage:

dockerLogout(
  registry: '' description: The name of the ECR with which to logout
)
```
## vars/dockerVolume.groovy
```
Function: Ensures a docker volume directory is present if it is to be mounted in the pipelines

Usage:

dockerVolume(
  directory: '' description: The directory to create
)
```
## vars/loadManifest.groovy
```
Function: Gathers the manifest in resources/devops/grababyte/ and creates a map

Usage:

loadManifest()
```
## vars/nexusDownload.groovy
```
Function: Download one or more components from a Nexus namespace

Usage:

nexusDownload(
  anonymous: '', (Boolean) description: Is this artifact to be downloaded as an anonymous user
  credentialsId: '', description: The global usernamePassword binding credentials to download the components from Nexus
  destination: '', description: The directory to download the components to
  url: '', description: The base URL for download components from Nexus
  namespace: '', description: Appended to the url for the namespace of the components to download
  tag: '', description: The further version tag on the namespace to download components
  components: [
    'component1', description: A list of components to download from within the namespace (can be one component)
    'component2'
  ]
)
```
## vars/nexusUpload.groovy
```
Function: Upload one or more components from a Nexus namespace

Usage:

nexusUpload(
  credentialsID: '', description: The global usernamePassword binding credentials to download the components from Nexus
  url: '', description: The Nexus base URL
  sourceFilePath: '', description: The path to the local file to upload to Nexus
  sourceFileName: '', description: The name of the local file to upload to Nexus
  namespace: '' description: The Nexus namespace with which to upload the local file
)
```
## vars/sonarScan.groovy (DEPRECATED)
```
Function: Run the Sonar Scan for the code repository you have cloned in the Pipeline
```

# Installation

The libraries can be called in the following way, but need to be set up as extended libraries in the Jenkins global configuration

`@Library('jenkins-devops-libraries') _`


# Tags
To Pull in a specific version of the pipelines please reference them as follows at the top of your Jenkinsfile

`@Library('jenkins-devops-libraries@1.0.0') _`

The "@1.0.0" will clone in that library version for use.

## Author Information
These libraries were created initially in 2022 by grababyte, please keep supporting the development community with Open Source Software.
