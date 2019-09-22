-- 用户表
create table if not  exists "user"
(
  id       bigserial    not null
    constraint user_pk
      primary key,
  username varchar(255) not null,
  password varchar(255) not null
);

create unique if not  exists index user_id_uindex
  on "user" (id);

-- 角色
create table if not  exists role
(
  id   bigserial    not null
    constraint role_pk
      primary key,
  name varchar(255) not null
);

create unique if not exists index role_id_uindex
  on role (id);


-- 角色
create table if not exists permission
(
  id          bigserial not null
    constraint permission_pk
      primary key,
  url         varchar(255),
  name        varchar(255),
  description varchar(255),
  pid         bigint
);

create unique if not exists index permission_id_uindex
  on permission (id);


  -- 用户角色表
create table if not  exists user_role
(
  user_id bigint not null
    constraint user_role_user_id_fk
      references "user",
  role_id bigint not null
    constraint user_role_role_id_fk
      references role
);

  -- 角色权限表
create table if not  exists role_permission
(
  role_id       bigint not null
    constraint role_permission_role_id_fk
      references role,
  permission_id bigint not null
    constraint role_permission_permission_id_fk
      references permission
);


INSERT INTO "user" (id, username, password) VALUES (1,'user','e10adc3949ba59abbe56e057f20f883e');
INSERT INTO "user" (id, username , password) VALUES (2,'admin','e10adc3949ba59abbe56e057f20f883e');
INSERT INTO role (id, name) VALUES (1,'USER');
INSERT INTO role (id, name) VALUES (2,'ADMIN');
INSERT INTO permission (id, url, name, pid) VALUES (1,'/user/common','common',0);
INSERT INTO permission (id, url, name, pid) VALUES (2,'/user/admin','admin',0);
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);







