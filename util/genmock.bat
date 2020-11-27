@echo off	

if "%1"=="" (
    @echo on
	@echo "usage: genmock delete|proxy|requests|snapshot"
	@echo off
)
if "%1"=="delete" (
   @echo on
   curl -v -X"DELETE" https://env-dev.amsit.dev/__admin/requests
   @echo off
)

if "%1"=="proxy" (
   @echo on
   curl -v -X"POST" --data "@proxy-body.json" https://env-dev.amsit.dev/__admin/proxy
   @echo off
)


if "%1"=="requests" (
   @echo on
   curl -v -X"GET" https://env-dev.amsit.dev/__admin/requests
   @echo off
)

if "%1"=="snapshot" (
  @echo on
  curl -v -X"POST" --data "@snapshot-body.json" https://env-dev.amsit.dev/__admin/recordings/snapshot -o snapshot-result.json
  type snapshot-result.json
  @echo off
)


