#!/bin/bash

set -xe
ls -l

echo "$PROXYPORT"
echo "$PROXYSOURCE"
echo "$PROXYTARGET"

java -classpath "wiremock.jar:./libs/*" com.ibm.amsit.eessi.GenMock "$PROXYPORT" "$PROXYSOURCE" "$PROXYTARGET"