# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table companies (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  company_id                varchar(255) not null,
  constraint pk_companies primary key (id))
;

create table sessions (
  id                        bigint auto_increment not null,
  name                      varchar(255) not null,
  company_id                bigint,
  constraint pk_sessions primary key (id))
;

create table m_users (
  user_id                   integer auto_increment not null,
  company_id                bigint,
  session_id                bigint,
  user_name                 varchar(50) not null,
  constraint pk_m_users primary key (user_id))
;

alter table sessions add constraint fk_sessions_company_1 foreign key (company_id) references companies (id) on delete restrict on update restrict;
create index ix_sessions_company_1 on sessions (company_id);
alter table m_users add constraint fk_m_users_company_2 foreign key (company_id) references companies (id) on delete restrict on update restrict;
create index ix_m_users_company_2 on m_users (company_id);
alter table m_users add constraint fk_m_users_session_3 foreign key (session_id) references sessions (id) on delete restrict on update restrict;
create index ix_m_users_session_3 on m_users (session_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table companies;

drop table sessions;

drop table m_users;

SET FOREIGN_KEY_CHECKS=1;

