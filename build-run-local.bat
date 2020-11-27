call mvn clean package
docker build . -t genmock:latest
docker stop genmock
docker run -e PROXYTARGET=http://ams9esb01ls/ -p 8089:8089 --name genmock --rm genmock:latest