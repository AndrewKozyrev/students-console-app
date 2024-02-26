@echo off

if not exist "docker image ls | findstr /i "app-image"" (
  echo Building image app-image...

  if not exist ".\\target\\students-console-app.jar" (
    echo Jar file not found, building project...
    mvnw.cmd clean package
  )

  docker build -t app-image .
)

echo Starting container...
docker run -it --rm ^
  -e SPRING_CONFIG_LOCATION=/config/application.yml ^
  -v "%~dp0\app\application.yml:/config/application.yml:ro" ^
  app-image %*

PAUSE