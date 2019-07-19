DELIMITER ';'

/*
MYSQL SCRIPT
PROCEDURE CONFIG DATA
*/

DELIMITER '/$'

create procedure set_user_icon(pid nvarchar(128), icon_id nvarchar(256))
begin
  delete from users_icon where users_icon.id = pid;
  insert into users_icon values(pid,icon_id);
end
/$


create procedure set_user_tag(pid nvarchar(128),type nvarchar(64),id nvarchar(128))
begin
  delete from users_tag where users_tag.tag_type = type and users_tag.tag_id = id;
  insert into users_tag values(pid,type,id);
end
/$


DELIMITER ';';
