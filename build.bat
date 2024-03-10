@echo off

echo Building Cart project...
cd cart
gradlew.bat clean build
timeout /t 3 /nobreak  >nul  REM Wait for 5 seconds (adjust as needed)

cd ..

echo Building GateWay project...
cd gateway
gradlew.bat clean build
cd ..


echo Building Order project...
cd order
gradlew.bat clean build
cd ..


echo Building Products project...
cd products
gradlew.bat clean build
cd ..


echo Building Service Discovery project...
cd service-discovery-server
gradlew.bat clean build
cd ..

echo Building User project...
cd user
gradlew.bat clean build
cd ..


echo Build process completed for all projects.

docker-compose up --build
