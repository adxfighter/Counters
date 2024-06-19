"C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" startvm "Tomcat" --type headless
TIMEOUT /T 30
java -jar -Dspring.profiles.active=%1 -Dserver.port=80 ./target/counters-2.0-SNAPSHOT.war
pause