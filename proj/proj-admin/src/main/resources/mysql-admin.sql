/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/22 21:31:14                           */
/*==============================================================*/


drop table if exists PERSISTENT_LOGINS;

drop table if exists SYS_AUTHORITY;

drop table if exists SYS_AUTHORITY_RESOURCE;

drop table if exists SYS_MODULE;

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

alter table PERSISTENT_LOGINS comment 'Spring Remember me ณึพรปฏ';

/*==============================================================*/
/* Table: SYS_AUTHORITY                                         */
/*==============================================================*/
create table SYS_AUTHORITY
(
   AUTHORITY_ID         varchar(36) not null,
   AUTHORITY_MARK       varchar(20),
   AUTHORITY_NAME       varchar(100) not null,
   AUTHORITY_DESC       varchar(200),
   MESSAGE              varchar(200),
   ENABLED              BOOLEAN,
   SYS_SPEC             numeric(8,0),
   primary key (AUTHORITY_ID)
);

/*==============================================================*/
/* Table: SYS_AUTHORITY_RESOURCE                                */
/*==============================================================*/
create table SYS_AUTHORITY_RESOURCE
(
   RESOURCE_ID          varchar(36) not null,
   AUTHORITY_ID         varchar(36) not null,
   primary key (RESOURCE_ID, AUTHORITY_ID)
);

/*==============================================================*/
/* Table: SYS_MODULE                                            */
/*==============================================================*/
create table SYS_MODULE
(
   MODULE_ID            varchar(36) not null,
   MODULE_NAME          varchar(100) not null,
   MODULE_DESC          varchar(200),
   MODULE_TYPE          varchar(40),
   PARENT_ID            varchar(36),
   MODULE_URL           varchar(200),
   LEVEL                numeric(8,0) comment '1',
   LEAF                 BOOLEAN,
   ENABLED              BOOLEAN,
   PRIORITY             numeric(8,0),
   primary key (MODULE_ID)
);

/*==============================================================*/
/* Table: SYS_RESOURCE                                          */
/*==============================================================*/
create table SYS_RESOURCE
(
   RESOURCE_ID          varchar(36) not null,
   RESOURCE_TYPE        varchar(40) comment 'URL,METHOD',
   RESOURCE_NAME        varchar(100),
   RESOURCE_DESC        varchar(200),
   RESOURCE_PATH        varchar(200),
   PRIORITY             numeric(8,0) default 0,
   ENABLED              BOOLEAN default true,
   SYS_SPEC             numeric(8,0),
   PARENT_ID            varchar(36),
   MODULE_ID            varchar(36),
   primary key (RESOURCE_ID)
);

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
create table SYS_ROLE
(
   ROLE_ID              varchar(36) not null,
   ROLE_NAME            varchar(100),
   ROLE_DESC            varchar(200),
   ENABLED              BOOLEAN,
   SYS_SPEC             numeric(8,0),
   primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: SYS_ROLE_AUTHORITY                                    */
/*==============================================================*/
create table SYS_ROLE_AUTHORITY
(
   AUTHORITY_ID         varchar(36) not null,
   ROLE_ID              varchar(36) not null,
   primary key (AUTHORITY_ID, ROLE_ID)
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
   CREATED_TIME         timestamp,
   LAST_LOGIN           timestamp,
   VALID_TIME           timestamp,
   LOGIN_IP             varchar(100),
   ORG_ID               varchar(36),
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
   ROLE_ID              varchar(36) not null,
   USER_ID              varchar(36) not null,
   primary key (ROLE_ID, USER_ID)
);

alter table SYS_AUTHORITY_RESOURCE add constraint FK_Reference_6 foreign key (RESOURCE_ID)
      references SYS_RESOURCE (RESOURCE_ID) on delete restrict on update restrict;

alter table SYS_AUTHORITY_RESOURCE add constraint FK_Reference_7 foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID) on delete restrict on update restrict;

alter table SYS_RESOURCE add constraint FK_SYS_RESO_REFERENCE_SYS_MODU foreign key (MODULE_ID)
      references SYS_MODULE (MODULE_ID) on delete restrict on update restrict;

alter table SYS_ROLE_AUTHORITY add constraint FK_Reference_4 foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID) on delete restrict on update restrict;

alter table SYS_ROLE_AUTHORITY add constraint FK_Reference_5 foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID) on delete restrict on update restrict;

alter table SYS_USER_ROLE add constraint FK_Reference_2 foreign key (USER_ID)
      references SYS_USER (USER_ID) on delete restrict on update restrict;

alter table SYS_USER_ROLE add constraint FK_Reference_3 foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID) on delete restrict on update restrict;

