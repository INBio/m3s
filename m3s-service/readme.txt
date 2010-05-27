** Download Dependencies and Generation of the Eclipse configuration files

  $mvn clean eclipse:clean eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true


* --> JTA
 Go to: http://java.sun.com/products/jta and download the class files 1.0.1B (jta-1_0_1B-classes.zip) 
  and if you wish the doc file too.

 Assuming the files where downloaded at: $HOME/.m2/manually-downloaded-jars/javax.transaction.jta/
 Then, install it using the command: (if $HOME=/home/jgutierrez/)  
  $mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Djavadoc=/home/jgutierrez/.m2/manually-downloaded-jars/javax.transaction.jta/jta-1_0_1B-doc.zip -Dfile=/home/jgutierrez/.m2/manually-downloaded-jars/javax.transaction.jta/jta-1_0_1B-classes.zip    

* --> Oracle
 Go to: http://www.oracle.com/technology/software/tech/java/sqlj_jdbc/htdocs/jdbc9201.html and 
 download the Oracle9i JDBC Driver

 Assuming the files where downloaded at: $HOME/.m2/manually-downloaded-jars/com.oracle.ojdbc14/
 Then, install it using the command: (if $HOME=/home/jgutierrez/)  
  $mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc14 -Dversion=9.0.2.0.0 -Dpackaging=jar -Dfile=/home/jgutierrez/.m2/manually-downloaded-jars/com.oracle.ojdbc14/ojdbc14-9.0.2.0.0.jar

 If yout have problems with the dependencies contact me: jgutierrez[at]inbio[dot]ac[dot]cr

** Install de artifact in the repository    
  $mvn -Dmaven.test.skip=true clean install source:jar --offline
 
 -> its recommended to be run online because maven needs to download a couple of jars, 
 then you would be able to run the test without connection.    

** About eclipse problems with tr.jar
 In some version of Eclipse on Ubuntu (using java-sun) the IDE is unable to locate the jar file. This
 could be fixed setting the concrete path of the java jre. (look for the installed jrw in eclipse).
