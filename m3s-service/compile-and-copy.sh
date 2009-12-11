mvn -Dmaven.test.skip=true clean install source:jar -npu --offline
mvn install:install-file -Dfile=target/m3s-service-2.1-SNAPSHOT-sources.jar -DartifactId=m3s-service -DgroupId=m3s -Dpackaging=zip -Dversion=2.1-SNAPSHOT -npu -o 
#cp /home/jgutierrez/.m2/repository/m3s/m3s-service/2.1-SNAPSHOT/m3s-service-2.1-SNAPSHOT.jar /home/jgutierrez/workspace/m3s-v2.1/m3s-gwt/lib/
#mvn -Dtest=CLASSNAME




