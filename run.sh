#Build the docker image
docker build -t cognito-challenge .

#Execute the docker image with the app
docker run --publish 8080:8080 --rm --name cognito-challenge cognito-challenge