--------------------------------------------------------
--  파일이 생성됨 - 목요일-8월-10-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CHATCOUNT
--------------------------------------------------------

  CREATE TABLE "SKOTT"."CHATCOUNT" 
   (	"ROOMID" NUMBER(10,0), 
	"MALE_CHATCOUNT" NUMBER(10,0), 
	"MALE_CHECKCOUNT" NUMBER(10,0), 
	"FEMALE_CHATCOUNT" NUMBER(10,0), 
	"FEMALE_CHECKCOUNT" NUMBER(10,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.CHATCOUNT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index CHATCOUNT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."CHATCOUNT_PK" ON "SKOTT"."CHATCOUNT" ("ROOMID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table CHATCOUNT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."CHATCOUNT" MODIFY ("ROOMID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."CHATCOUNT" ADD CONSTRAINT "CHATCOUNT_PK" PRIMARY KEY ("ROOMID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
