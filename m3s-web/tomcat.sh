sudo /etc/init.d/tomcat5.5 stop
sudo rm /usr/share/tomcat5.5/webapps/m3sINBio* -rf
sudo rm /usr/share/tomcat5.5/logs/m3s.log
mvn clean install --offline
sudo cp target/m3sINBio.war /usr/share/tomcat5.5/webapps/
sudo /etc/init.d/tomcat5.5 start

