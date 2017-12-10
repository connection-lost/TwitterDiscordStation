@echo off
mvn clean compile assembly:single
copy target\BangTwitterDiscord2*.jar .\
rename BangTwitterDiscord2*.jar BangTwitterDiscord.jar
pause