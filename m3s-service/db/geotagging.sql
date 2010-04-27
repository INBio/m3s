
insert into core.metadata_standard (metadata_standard_id, name, creation_Date, 
    created_by, last_modification_date, last_modification_by)
  values (6, 'GPS Information', '2010-04-27', 'admin', '2010-04-27', 'admin');

insert into core.media_attribute_type (media_type_id, media_attribute_id, metadata_standard_id, creation_Date, 
    created_by, last_modification_date, last_modification_by, standard_attribute_id) 
  values (1, 38, 6, '2010-04-27', 'admin', '2010-04-27', 'admin', 38);  

insert into core.media_attribute_type (media_type_id, media_attribute_id, metadata_standard_id, creation_Date, 
    created_by, last_modification_date, last_modification_by, standard_attribute_id) 
  values (1, 39, 6, '2010-04-27', 'admin', '2010-04-27', 'admin', 39);


--insert en media_attribute***  
  
-- Ejecutarlo en coffea: listo
-- Ejecutarlo en Cimex[db 'real']: listo
-- Actualizar el db.sql: 