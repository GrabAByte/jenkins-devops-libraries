#!/usr/bin/groovy
import devops.grababyte.mapVars

def call(body) {

  Map var = new mapVars().paramsConverter(body)

  concatMap = {
    it.collect { /-e $it.key=$it.value/ } join " "
  }

  def String ansibleOverrides = concatMap(var.overrides)

  sh(""" \
    export ANSIBLE_ROLES_PATH="${var.rolesPath}"

    ansible-playbook -v -f ${var.forks} \
      playbooks/${var.playbook}.yml \
      -i ${var.inventory} \
      \'${ansibleOverrides}\'
    """)

}
