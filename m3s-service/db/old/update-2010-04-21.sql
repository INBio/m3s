-- asume que la base de datos está al día.

-- El siguiente script actualizará los media attributes para agregar dos al estándar migración:
-- id  nombre
-- 36  high resolution volume
-- 37  high resolution file name 




insert into core.media_attribute_value (media_attribute_id, media_id, value_varchar, created_by, creation_date) 
  select 36, media_id,high_resolution_volume, 'admin', '2010-04-21' 
  from core.media 
  where media.high_resolution_volume is not null;
  
insert into core.media_attribute_value (media_attribute_id, media_id, value_varchar, created_by, creation_date) 
  select 37, media_id,high_resolution_file_name, 'admin', '2010-04-21' 
  from core.media 
  where media.high_resolution_file_name is not null;  
  
--insertar esto en media_attribute_type lo hice manual...

-- Ejecutarlo en coffea: listo
-- Ejecutarlo en Cimex[db 'real']: listo
-- Actualizar el db.sql: 