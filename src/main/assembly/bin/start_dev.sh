#!/bin/sh
curdir=`dirname $0`
cd $curdir
curdir=`pwd`

APP_NAME=`ls  ../lib/wealth-product-service-*.jar | grep -o "wealth.*jar" | tr -d '\r'`
echo $APP_NAME
MAIN_CLASS=com.gemantic.wealth.product.server.Server
APP_HOME=..
LIB_PATH=$APP_HOME/lib
DUBBO_REGISTRY_FILE=-Ddubbo.registry.file=$MAIN_CLASS
CLASSPATH=$APP_HOME:$LIB_PATH/$APP_NAME:$APP_HOME/resources

JAVA_ARGS="-server -Xmx256m -Xms256m -XX:NewSize=64m -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=78 -XX:+HeapDumpOnOutOfMemoryError -Xloggc:$APP_HOME/logs/gc.log"

PLATFORM=-Dplatform=default

cd $APP_HOME/bin

echo "$CLASSPATH"

if [ $# -gt 0 ];then
   PLATFORM=-Dplatform=$1
fi

echo " java  $PLATFORM $JAVA_ARGS $DUBBO_REGISTRY_FILE -classpath $CLASSPATH $MAIN_CLASS  1> /dev/null 2>&1 "
nohup java $PLATFORM $JAVA_ARGS $DUBBO_REGISTRY_FILE -classpath $CLASSPATH $MAIN_CLASS  1> /dev/null 2>&1 &