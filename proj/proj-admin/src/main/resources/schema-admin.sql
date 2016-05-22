/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/22 12:11:33                           */
/*==============================================================*/


drop table if exists PERSISTENT_LOGINS;

drop table if exists SYS_AUTHORITY;

drop table if exists SYS_AUTHORITY_RESOURCE;

drop table if exists SYS_RESOURCE;

drop table if exists SYS_ROLE;

drop table if exists SYS_ROLE_AUTHORITY;

drop table if exists SYS_USER;

drop table if exists SYS_USER_ROLE;

/*==============================================================*/
/* Table: PERSISTENT_LOGINS                                     */
/*==============================================================*/
create table PERSISTENT_LOGINS
(
   USERNAME             varchar(64),
   SERIES               varchar(64) not null,
   TOKEN                varchar(64),
   LAST_USED            timestamp,
   primary key (SERIES)
);

/*==============================================================*/
/* Table: SYS_AUTHORITY                                         */
/*==============================================================*/
create table SYS_AUTHORITY
(
   AUTHORITY_ID         varchar(36) not null,
   AUTHORITY_NAME       varchar(40),
   AUTHORITY_DESC       varchar(100),
   ENABLED              BOOLEAN,
   ISSYS                BOOLEAN,
   MODULE               varchar(4) comment '所属的子系统',
   primary key (AUTHORITY_ID)
);

/*==============================================================*/
/* Table: SYS_AUTHORITY_RESOURCE                                */
/*==============================================================*/
create table SYS_AUTHORITY_RESOURCE
(
   ID                   varchar(36) not null,
   AUTHORITY_ID         varchar(36),
   RESOURCE_ID          varchar(36),
   ENABLED              BOOLEAN,
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_RESOURCE                                          */
/*==============================================================*/
create table SYS_RESOURCE
(
   RESOURCE_ID          varchar(36) not null,
   RESOURCE_NAME        varchar(100),
   RESOURCE_DESC        varchar(100),
   RESOURCE_TYPE        varchar(40),
   RESOURCE_STRING      varchar(200),
   PRIORITY             NUMBER comment '优先级',
   ENABLED              BOOLEAN,
   ISSYS                BOOLEAN,
   MODULE               varchar(4) comment '所属的子系统',
   primary key (RESOURCE_ID)
);

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ROLE_ID              varchar(36) not null,
   ROLE_NAME            varchar(40),
   ROLE_DESC            varchar(100),
   ENABLED              BOOLEAN,
   ISSYS                BOOLEAN,
   MODULE               varchar(4) comment '所属的子系统',
   primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: SYS_ROLE_AUTHORITY                                    */
/*==============================================================*/
create table SYS_ROLE_AUTHORITY
(
   ID                   varchar(36) not null,
   ROLE_ID              varchar(36),
   AUTHORITY_ID         varchar(36),
   ENABLED              BOOLEAN,
   primary key (ID)
);

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
create table SYS_USER
(
   USER_ID              varchar(36) not null,
   USERNAME             varchar(100) not null,
   NAME                 varchar(100),
   PASSWORD             varchar(100) not null,
   CREATED_TIME         timestamp default 'SYSDATE',
   LAST_LOGIN           timestamp,
   VALID_TIME           timestamp,
   LOGIN_IP             varchar(100),
   ORG_ID               varchar(100),
   ORG_NAME             varchar(100),
   ENABLED              BOOLEAN,
   ACCOUNT_NON_EXPIRED  BOOLEAN,
   ACCOUNT_NON_LOCKED   BOOLEAN,
   CREDENTIALS_NON_EXPIRED BOOLEAN,
   primary key (USER_ID)
);

/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
create table SYS_USER_ROLE
(
   ID                   varchar(36) not null,
   USER_ID              varchar(36),
   ROLE_ID              varchar(36),
   ENABLED              BOOLEAN,
   primary key (ID)
);

alter table SYS_AUTHORITY_RESOURCE add constraint FK_SYS_AUTHORITY_RE_AU foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID) on delete restrict on update restrict;

alter table SYS_AUTHORITY_RESOURCE add constraint FK_SYS_AUTHORITY_RE_RE foreign key (RESOURCE_ID)
      references SYS_RESOURCE (RESOURCE_ID) on delete restrict on update restrict;

alter table SYS_ROLE_AUTHORITY add constraint FK_SYS_ROLE_AUTHORITY_AU foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID) on delete restrict on update restrict;

alter table SYS_ROLE_AUTHORITY add constraint FK_SYS_ROLE_AUTHORITY_ROLES foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID) on delete restrict on update restrict;

alter table SYS_USER_ROLE add constraint FK_USER_ROLE_ROLE foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID) on delete restrict on update restrict;

alter table SYS_USER_ROLE add constraint FK_USER_ROLE_USER foreign key (USER_ID)
      references SYS_USER (USER_ID) on delete restrict on update restrict;

