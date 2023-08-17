--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USERINFO
--------------------------------------------------------

  CREATE TABLE "SKOTT"."USERINFO" 
   (	"ID" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(40 BYTE), 
	"EMAIL" VARCHAR2(50 CHAR), 
	"SEX" NUMBER, 
	"ROLE" NUMBER, 
	"PHONE" VARCHAR2(40 BYTE), 
	"NICKNAME" VARCHAR2(20 BYTE), 
	"ADDRESS" VARCHAR2(100 CHAR), 
	"BIRTHDAY" DATE, 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp, 
	"MODIFIED_TIME" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.USERINFO
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index USERINFO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."USERINFO_PK" ON "SKOTT"."USERINFO" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger USERINFO_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."USERINFO_TRG" 
BEFORE INSERT ON USERINFO 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."USERINFO_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table USERINFO
--------------------------------------------------------

  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("SEX" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" ADD CONSTRAINT "UI_ROLE_CK" CHECK (ROLE BETWEEN 0 AND 10) ENABLE;
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("PHONE" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("NICKNAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("ADDRESS" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" MODIFY ("BIRTHDAY" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USERINFO" ADD CONSTRAINT "USERINFO_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."USERINFO_PK"  ENABLE;
