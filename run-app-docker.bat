@echo off

:: Check for existing image
if not exist "docker image ls | findstr /i "app-image"" (
  echo Building image app-image...

  :: Check for JAR file
  if not exist ".\\target\\students-console-app.jar" (
    echo Jar file not found, building project...
    mvnw.cmd clean package
  )

  docker build -t app-image .

  :: Start the container after image creation
  echo Starting container...
  docker run -it --rm ^
    -e SPRING_CONFIG_LOCATION=/config/application.yml ^
    -v "%~dp0\app\application.yml:/config/application.yml:ro" ^
    app-image %*
) else (
  :: Image exists, start the container directly
  echo Starting container...
  docker run -it --rm ^
    -e SPRING_CONFIG_LOCATION=/config/application.yml ^
    -v "%~dp0\app\application.yml:/config/application.yml:ro" ^
    app-image %*
)

PAUSE