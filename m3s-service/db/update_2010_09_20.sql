-- update 'security' and users management

-- delete table user
drop table core.user;

-- alter table project
ALTER TABLE core.project DROP CONSTRAINT fk6896fbca2a254e7d;
alter table core.project
  ADD CONSTRAINT project_name_fk FOREIGN KEY (project_manager_name)
      REFERENCES core.system_user ("username") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

-- alter table user_project_privileges
ALTER TABLE core.user_project_privilege DROP CONSTRAINT fk1c308997cd68a5b9;
alter table core.user_project_privilege
  ADD CONSTRAINT project_name_fk FOREIGN KEY (user_name)
      REFERENCES core.system_user ("username") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
--delete table security_users
drop table core.security_users;      