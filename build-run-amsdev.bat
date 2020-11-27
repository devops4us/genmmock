@echo off
if "%1"=="build" (
	@echo on
	call mvn clean package
	docker build . -t ams9jen01ls.amsit.dev:80/genmock:latest

	docker login ams9jen01ls.amsit.dev:80 -u admin -p Quook2dn
	ECHO if necessary, add "insecure-registries": ["ams9jen01ls.amsit.dev:80"] to Docker config
	docker push ams9jen01ls.amsit.dev:80/genmock:latest
)

if "%1"=="deploy" (
	@echo on
	kubectl apply -f ci-cd-pipeline\tekton-kubernetes\deploy-genmock.yaml -n env-dev
	kubectl expose deployment genmock-app --name=genmock -n env-dev
)