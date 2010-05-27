** Download Dependencies and Generation of the Eclipse configuration files

  $mvn clean eclipse:clean eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true

 If you have problems with the dependencies contact me: jgutierrez[at]inbio[dot]ac[dot]cr

** Run the application on a jetty webserver
  $mvn -Dlog4j.configuration=file:src/main/resources/log4j.xml jetty:run
 
 -> its recommended to be run online because maven needs to download a couple of jars, 
 then you would be able to run the test without connection.    

** Configuration of the M3S

run the setup.sh [m3s-service] (check that the database scripts are included)
check the files config.properties and constants.properties [m3s-web or Tomcat webapp folder]
enjoy!


