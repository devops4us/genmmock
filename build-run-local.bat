

@echo off
if "%1%"=="proxy" (
	call mvn clean package
	docker build . -t genmock:latest
	docker stop genmock
	docker run -e COMMAND=PROXY -e PROXYSOURCE="/.*" -e PROXYTARGET=http://ams9esb01ls/ -e PROXYPORT=8089 -p 8089:8089 --name genmock --rm genmock:latest
)
if "%1%"=="mock" (
	call mvn clean package
	docker build . -t genmock:latest
	docker stop genmock
	docker run -e COMMAND=MOCK -e PROXYPORT=8089 -p 8089:8089 --name genmock --rm genmock:latest
)
if "%1%"=="" (
	echo "usage: build-run-local proxy|mock"
)