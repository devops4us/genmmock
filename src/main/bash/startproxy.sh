#!/bin/bash

set -xe
ls -l

echo "$COMMAND"
echo "$PROXYPORT"
echo "$PROXYSOURCE"
echo "$PROXYTARGET"

java -classpath "wiremock.jar:./libs/*" com.ibm.amsit.eessi.GenMock "$COMMAND" "$PROXYPORT" "$PROXYSOURCE" "$PROXYTARGET"