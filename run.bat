@echo off
if not exist bin mkdir bin
echo Compiling BankingApp...
javac -cp "lib/sqlite-jdbc.jar" -d bin *.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b %errorlevel%
)
echo Running BankingApp...
java -cp "bin;lib/sqlite-jdbc.jar" BankingApp
pause
