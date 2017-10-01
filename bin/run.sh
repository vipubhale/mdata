#!/bin/bash
cmd=$0
bin_home=$(dirname ${cmd})
if [[ ${bin_home} == '.' ]]; then
  bin_home=$(pwd)
fi
app_home=$(dirname ${bin_home})
echo "Running the command from :: "${app_home}
java -Xmx256m -Dspring.profiles.active=prod \
  -jar ${app_home}/mdata-0.0.8-beta.jar &