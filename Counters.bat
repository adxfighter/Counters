"C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" startvm "Tomcat" --type headless
TIMEOUT /T 50
java -jar -Dspring.profiles.active=db2 -Dserver.port=80 ./target/counters-2.0-SNAPSHOT.war
pause