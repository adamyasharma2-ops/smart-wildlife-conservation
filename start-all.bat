@echo off
REM NatureSheild - Start All Microservices
REM Run this script from the wildlife-conservation directory

echo ========================================================
echo   NatureSheild Protected Habitat Command Network
echo   Starting all 4 microservices...
echo ========================================================

SET BASE=%~dp0

echo.
echo [1/4] Launching Wildlife Core Service (port 8081)...
start "NatureSheild - Wildlife Service [8081]" cmd /k "cd /d %BASE%wildlife-service && ..\mvnw.cmd spring-boot:run"

timeout /t 4 /nobreak > nul

echo [2/4] Launching Protected Area Service (port 8082)...
start "NatureSheild - Protected Area Service [8082]" cmd /k "cd /d %BASE%protected-area-service && ..\mvnw.cmd spring-boot:run"

timeout /t 4 /nobreak > nul

echo [3/4] Launching Patrol Operations Service (port 8083)...
start "NatureSheild - Patrol Service [8083]" cmd /k "cd /d %BASE%patrol-service && ..\mvnw.cmd spring-boot:run"

timeout /t 4 /nobreak > nul

echo [4/4] Launching Threat & Incident Service (port 8084)...
start "NatureSheild - Incident Service [8084]" cmd /k "cd /d %BASE%incident-service && ..\mvnw.cmd spring-boot:run"

echo.
echo ========================================================
echo All 4 microservices launched in separate consoles.
echo.
echo NatureSheild Portal Entry Points:
echo   Login Gateway:          http://localhost:8081/login
echo   Consolidated Dashboard: http://localhost:8081/dashboard
echo   Protected Areas:        http://localhost:8082/areas
echo   Patrol Operations:      http://localhost:8083/patrols
echo   Incidents & Threats:    http://localhost:8084/incidents
echo   Official Monthly Report: http://localhost:8084/incidents/report
echo ========================================================
pause
