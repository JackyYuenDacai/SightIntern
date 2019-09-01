drop database if exists caritas_db;
create database caritas_db;
use caritas_db;

create table users(
  id nvarchar(64) primary key,
  name nvarchar(128),
  role nvarchar(32) not null,
  privilege int not null,
  extra nvarchar(2048),
  pwd nvarchar(512),
  soc nvarchar(512),
  parent_id nvarchar(64)
);

create table users_icon(
  id nvarchar(64) primary key,
  icon_id nvarchar(128)
);

create table users_tag(
  id nvarchar(64) not null,
  tag_type nvarchar(64) not null,
  tag_id nvarchar(128) not null
);

create table soc_list(
  soc nvarchar(512) primary key,
  name nvarchar(1024)
);

create table record_master(
  token nvarchar(128) primary key,
  id nvarchar(128) ,
  type nvarchar(128),
  location nvarchar(128),
  record_in datetime,
  record_out datetime,
  data nvarchar(2048)

);

create table users_files(
  id nvarchar(64) primary key,
  pid nvarchar(64) ,
  file_type nvarchar(64) not null,
  file_id nvarchar(128) not null
);

create table users_token(
  id nvarchar(64) primary key,
  token nvarchar(128),
  expire datetime
);

create table forms(
  id nvarchar(64) primary key,
  soc nvarchar(512),
  user_id nvarchar(64),
  form_data nvarchar(8192)
);

insert into users values('test','Test user','Test',0,'Test user',md5('test'),'TEST','');
insert into soc_list values('CARITAS','Caritas School');
/*
child_status :
0: get in
1: get out
2: not sure
*/

/*
users
users_files
record_child
record_master
soc_list
users_tag
users_icon
*/
