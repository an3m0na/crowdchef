#!/bin/sh

../../Tomcat/bin/catalina.sh stop
rm -r ../../Tomcat/webapps/CrowdChef*
cp crowdchef-webservice/target/CrowdChef.war ../../Tomcat/webapps
../../Tomcat/bin/catalina.sh run