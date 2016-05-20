alter table SYS_AUTHORITY_RESOURCE
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH;

alter table SYS_AUTHORITY_RESOURCE
   drop constraint FK_SYS_AUTH_REFERENCE_SYS_RESO;

alter table SYS_RESOURCE
   drop constraint FK_SYS_RESO_REFERENCE_SYS_MODU;

alter table SYS_ROLE_AUTHORITY
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE;

alter table SYS_ROLE_AUTHORITY
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH;

alter table SYS_ROLE_MOUDLE
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_MODU;

alter table SYS_ROLE_MOUDLE
   drop constraint FK_S_ROLE_REFERENCE_SYS_ROLE;

alter table SYS_USER_ROLE
   drop constraint FK_SYS_USER_REFERENCE_SYS_USER;

alter table SYS_USER_ROLE
   drop constraint FK_SYS_USER_REFERENCE_SYS_ROLE;

drop table PERSISTENT_LOGINS cascade constraints;

drop table SYS_AUTHORITY cascade constraints;

drop table SYS_AUTHORITY_RESOURCE cascade constraints;

drop table SYS_MODULE cascade constraints;

drop table SYS_RESOURCE cascade constraints;

drop table SYS_ROLE cascade constraints;

drop table SYS_ROLE_AUTHORITY cascade constraints;

drop table SYS_ROLE_MOUDLE cascade constraints;

drop table SYS_USER cascade constraints;

drop table SYS_USER_ROLE cascade constraints;

/*==============================================================*/
/* Table: PERSISTENT_LOGINS                                     */
/*==============================================================*/
create table PERSISTENT_LOGINS  (
   USERNAME             VARCHAR2(64),
   SERIES               VARCHAR2(64)                    not null,
   TOKEN                VARCHAR2(64),
   LAST_USED            TIMESTAMP,
   constraint PK_PERSISTENT_LOGINS primary key (SERIES)
);

comment on table PERSISTENT_LOGINS is
'Spring Remember me 持久化';

/*==============================================================*/
/* Table: SYS_AUTHORITY                                       */
/*==============================================================*/
create table SYS_AUTHORITY  (
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   AUTHORITY_MARK       VARCHAR2(100),
   AUTHORITY_NAME       VARCHAR2(100)                   not null,
   AUTHORITY_DESC       VARCHAR2(200),
   MESSAGE              VARCHAR2(100),
   ENABLE               BOOLEAN,
   ISSYS                BOOLEAN,
   constraint PK_SYS_AUTHORITY primary key (AUTHORITY_ID)
);

/*==============================================================*/
/* Table: SYS_AUTHORITY_RESOURCE                             */
/*==============================================================*/
create table SYS_AUTHORITY_RESOURCE  (
   ID                   VARCHAR2(100)                   not null,
   RESOURCE_ID          VARCHAR2(100)                   not null,
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   constraint PK_SYS_AUTHORITY_RESOURCE primary key (ID)
);

/*==============================================================*/
/* Table: SYS_MODULE                                           */
/*==============================================================*/
create table SYS_MODULE  (
   MODULE_ID            VARCHAR2(100)                   not null,
   MODULE_NAME          VARCHAR2(100)                   not null,
   MODULE_DESC          VARCHAR2(200),
   MODULE_TYPE          VARCHAR2(100),
   PARENT               VARCHAR2(100),
   MODULE_URL           VARCHAR2(100),
   M_LEVEL              NUMBER,
   LEAF                 BOOLEAN,
   APPLICATION          VARCHAR2(100),
   CONTROLLER           VARCHAR2(100),
   ENABLE               BOOLEAN,
   PRIORITY             NUMBER,
   constraint PK_SYS_MODULE primary key (MODULE_ID)
);

comment on column SYS_MODULE.M_LEVEL is
'1';

/*==============================================================*/
/* Table: SYS_RESOURCE                                         */
/*==============================================================*/
create table SYS_RESOURCE  (
   RESOURCE_ID          VARCHAR2(100)                   not null,
   RESOURCE_TYPE        VARCHAR2(100),
   RESOURCE_NAME        VARCHAR2(100),
   RESOURCE_DESC        VARCHAR2(200),
   RESOURCE_PATH        VARCHAR2(200),
   PRIORITY             NUMBER                       default 0,
   ENABLE               BOOLEAN                      default true,
   MODULE_ID            VARCHAR2(100),
   constraint PK_SYS_RESOURCE primary key (RESOURCE_ID)
);

comment on column SYS_RESOURCE.RESOURCE_TYPE is
'URL,METHOD';

/*==============================================================*/
/* Table: SYS_ROLE                                             */
/*==============================================================*/
create table SYS_ROLE  (
   ROLE_ID              VARCHAR2(100)                   not null,
   ROLE_NAME            VARCHAR2(100),
   ROLE_DESC            VARCHAR2(200),
   ENABLE               BOOLEAN,
   constraint PK_SYS_ROLE primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: SYS_ROLE_AUTHORITY                                 */
/*==============================================================*/
create table SYS_ROLE_AUTHORITY  (
   ID                   VARCHAR2(100)                   not null,
   AUTHORITY_ID         VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_ROLE_AUTHORITY primary key (ID)
);

/*==============================================================*/
/* Table: SYS_ROLE_MOUDLE                                     */
/*==============================================================*/
create table SYS_ROLE_MOUDLE  (
   ID                   VARCHAR2(100)                   not null,
   MODULE_ID            VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_ROLE_MOUDLE primary key (ID)
);

comment on table SYS_ROLE_MOUDLE is
'控制角色对模块的访问权，主要用于生成菜单';

/*==============================================================*/
/* Table: SYS_USER                                             */
/*==============================================================*/
create table SYS_USER  (
   USER_ID              VARCHAR2(100)                   not null,
   USERNAME             VARCHAR2(100)                   not null,
   NAME                 VARCHAR2(100),
   PASSWORD             VARCHAR2(100)                   not null,
   CREATED_TIME         TIMESTAMP                      default SYSDATE,
   LAST_LOGIN           TIMESTAMP,
   VALID_TIME           TIMESTAMP,
   LOGIN_IP             VARCHAR2(100),
   ORG_ID               VARCHAR2(100),
   ORG_NAME             VARCHAR2(100),
   ENABLED              BOOLEAN,
   ACCOUNT_NON_EXPIRED  BOOLEAN,
   ACCOUNT_NON_LOCKED   BOOLEAN,
   CREDENTIALS_NON_EXPIRED BOOLEAN,
   constraint PK_SYS_USER primary key (USER_ID)
);

/*==============================================================*/
/* Table: SYS_USER_ROLE                                       */
/*==============================================================*/
create table SYS_USER_ROLE  (
   ID                   VARCHAR2(100)                   not null,
   ROLE_ID              VARCHAR2(100)                   not null,
   USER_ID              VARCHAR2(100)                   not null,
   constraint PK_SYS_USER_ROLE primary key (ID)
);

alter table SYS_AUTHORITY_RESOURCE
   add constraint FK_SYS_AUTH_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID);

alter table SYS_AUTHORITY_RESOURCE
   add constraint FK_SYS_AUTH_REFERENCE_SYS_RESO foreign key (RESOURCE_ID)
      references SYS_RESOURCE (RESOURCE_ID);

alter table SYS_RESOURCE
   add constraint FK_SYS_RESO_REFERENCE_SYS_MODU foreign key (MODULE_ID)
      references SYS_MODULE (MODULE_ID);

alter table SYS_ROLE_AUTHORITY
   add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID);

alter table SYS_ROLE_AUTHORITY
   add constraint FK_SYS_ROLE_REFERENCE_SYS_AUTH foreign key (AUTHORITY_ID)
      references SYS_AUTHORITY (AUTHORITY_ID);

alter table SYS_ROLE_MOUDLE
   add constraint FK_SYS_ROLE_REFERENCE_SYS_MODU foreign key (MODULE_ID)
      references SYS_MODULE (MODULE_ID);

alter table SYS_ROLE_MOUDLE
   add constraint FK_S_ROLE_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID);

alter table SYS_USER_ROLE
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key (USER_ID)
      references SYS_USER (USER_ID);

alter table SYS_USER_ROLE
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (ROLE_ID)
      references SYS_ROLE (ROLE_ID);
