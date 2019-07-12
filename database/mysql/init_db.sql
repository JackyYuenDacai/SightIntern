drop if exists database caritas_db;
create database caritas_db;
use caritas_db;

create table users(
  unique id nvarchar(64) not null,
  name nvarchar(128),
  role nvarchar(32) not null,
  privilege INT not null,
  extra nvarchar(2048),
  pwd nvarchar(512),
  soc nvarchar(512),
  parent_id nvarchar(64),
  index [id_index] (id)
);

create table users_icon(
  unique id nvarchar(64) not null,
  icon_id nvarchar(128)
);

create table users_tag(
  id nvarchar(64) not null,
  tag_type nvarchar(64) not null,
  tag_id nvarchar(128) not null
);

create table soc_list(
  unique soc nvarchar(512) not null,
  unique name nvarchar(1024) not null,
);

create table record_master(
  id nvarchar(128) primary key,
  type nvarchar(128)
  location nvarchar(128),
);

create table record_child(
  id nvarchar(64) primary key,
  parent_id nvarchar(128),
  student_id nvarchar(64),
  record_time datetime default now(),
  data nvarchar(2048),
  status int,
  index [parent_id_index] (parent_id)
);
/*
child_status :
0: get in
1: get out
2: not sure
*/
