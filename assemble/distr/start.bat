@echo off
set JAVA_ARGS=-Xms64m -Xmx512m

java -Xms64m -Xmx512m -Dlogging.config=./config/logback.xml -cp ./lib/* ru.cytogen.icbraindb.IcbraindbApplicationKt --spring.config.location=./config/application.yaml
