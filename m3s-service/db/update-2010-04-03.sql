-- asume que la base de datos fue creada con el script llamado m3s.sql

-- El siguiente script actualizará las columnas que tienen que ver con la traducción de palabras

-- agregará la columna locale a la tabla text_translation
ALTER TABLE core.text_translation ADD COLUMN locale character varying(2);
-- actualiza el valor correspondiente al locale tomando como referencia el language_id y asumiendo
-- que 1=es, 2=en y 3=pt cualquier otro caso debe agregarse acá.
UPDATE core.text_translation SET locale = 'es' where language_id=1;
UPDATE core.text_translation SET locale = 'en' where language_id=2;
UPDATE core.text_translation SET locale = 'pt' where language_id=3;

-- si existiera un language adicional a los indicados arriba entonces  acá dará error ;)
ALTER TABLE core.text_translation ALTER locale set not null;

-- chao language en text_translation y chao en la BD.
ALTER TABLE core.text_translation DROP COLUMN language_id;
DROP TABLE core.language;

-- Se crea como llave secundaria el text_id y el locale de modo que sea imposible tener
-- en la BD dos textos para el mismo locale.
ALTER TABLE core.text_translation  ADD CONSTRAINT llave_secundaria UNIQUE(text_id, locale);

-- Se elimina la tabla text y todas sus llaves en otras tablas
DROP TABLE core.text CASCADE;

-- Se deben hacer las siguientes cosas y luego borrar este archivo:
--
-- Ejecutarlo en coffea: listo
-- Ejecutarlo en fornax[db 'real']:
-- Actualizar el db.sql:
