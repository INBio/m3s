mvn -Dmaven.test.skip=true clean install source:jar -npu --offline
mvn install:install-file -Dfile=target/m3s-service-2.1-SNAPSHOT-sources.jar -DartifactId=m3s-service -DgroupId=m3s -Dpackaging=zip -Dversion=2.1-SNAPSHOT -npu -o 

#mvn test -Dtest=CLASSNAME --offline
#mvn test -Dtest=TaxonomyManagerTest --offline




