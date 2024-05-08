#! /bin/sh

FILE_NAME=icbraindb_server
JAVA_ARGS='-Xms64m -Xmx512m'

nohup java $JAVA_ARGS -Dlogging.config=./config/logback.xml -cp 'lib/*' ru.cytogen.icbraindb.IcbraindbApplicationKt --spring.config.location=./config/application.yaml > $FILE_NAME.out 2>&1 &
echo $! > $FILE_NAME.pid
