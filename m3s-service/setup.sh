#!/bin/bash
#Setup Script [test on Ubuntu 10.04]
# author: Jaime Gutiérrez - jgutierrez@inbio.ac.cr

DEFAULT_M3S_PATH="/mnt/m3sImages/INBio";
DEFAULT_IMPORTATION_BATCH_MEDIA_PATH="/root/m3sTempImages/INBio";

#FOLDERS: the tabs are only there to make easy the visualization of the path.
#DEFAULT_M3S_PATH
  #/mnt/m3sImages/INBio/IMPORT_FILES/
  IMPORT_FILES_FOLDER=$DEFAULT_M3S_PATH/"IMPORT_FILES";
  #/mnt/m3sImages/INBio/TEMP_MEDIA_DIR/
  TEMP_FILES_FOLDER=$DEFAULT_M3S_PATH/"TEMP_MEDIA_DIR";
  #/mnt/m3sImages/INBio/MEDIA/
  MEDIA_FOLDER=$DEFAULT_M3S_PATH/"MEDIA";
    #/mnt/m3sImages/INBio/MEDIA/ORIGINAL
    ORIGINAL_MEDIA_FOLDER=$MEDIA_FOLDER/"ORIGINAL";
    #/mnt/m3sImages/INBio/MEDIA/BIG
    BIG_MEDIA_FOLDER=$MEDIA_FOLDER/"BIG";
    #/mnt/m3sImages/INBio/MEDIA/THUMB
    THUMB_MEDIA_FOLDER=$MEDIA_FOLDER/"THUMB";

echo ""
echo "Setting up the application"
echo ""
echo "Creating folders:"
sudo mkdir $DEFAULT_M3S_PATH -v -p;
sudo mkdir $IMPORT_FILES_FOLDER -v -p;  
sudo mkdir $TEMP_FILES_FOLDER -v -p;
sudo mkdir $MEDIA_FOLDER -v -p;
sudo mkdir $ORIGINAL_MEDIA_FOLDER -v -p;
sudo mkdir $BIG_MEDIA_FOLDER -v -p;
sudo mkdir $THUMB_MEDIA_FOLDER -v -p;
# TODO: fix the permissions...
sudo chmod -R 777 $DEFAULT_M3S_PATH;

sudo mkdir $DEFAULT_IMPORTATION_BATCH_MEDIA_PATH -v -p;
# TODO: fix the permissions...
sudo chmod -R 777 $DEFAULT_IMPORTATION_BATCH_MEDIA_PATH;

echo ""
echo "Installing Image Magic"
sudo apt-get install imagemagick

echo ""
echo "Creating database [password for superuser will be prompt]"
#Uncomment this line to create a role for the application
psql --host localhost --port 5432 --username postgres --password < m3s_role[2010-05-27].sql

#data base schema creation
psql --host localhost --port 5432 --username postgres --password < m3s_schema[2010-05-27].sql

#populate the database using inbio data
#pg_dump --host 10.0.0.102 --port 5432 --username m3s --password --format plain --data-only --table core.text --table core.text_translation --table core.media_attribute --table core.media_attribute_type --table core.metadata_standard --table core.media_category --table core.media_type --table core.media_use --table core.privilege --table core.project --table core.security_users --table core.system_user --table core.user --table core.user_project_privilege --table core.use_policy  --table core.keyword_category --table core.keyword --file populate_inbio_data[2010-05-27].sql
psql --host localhost --port 5432 --username postgres --password --dbname m3s  < populate_inbio_data[2010-05-27].sql

#geo tagging update
psql --host localhost --port 5432 --username postgres --password --dbname m3s  < update_geotagging.sql


echo ""
echo "Done"

exit 0;
