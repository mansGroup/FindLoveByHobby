--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table JOB
--------------------------------------------------------

  CREATE TABLE "SKOTT"."JOB" 
   (	"JOB_ID" NUMBER(10,0), 
	"JOB_NAME" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.JOB
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index JOB_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."JOB_PK" ON "SKOTT"."JOB" ("JOB_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger JOB_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."JOB_TRG" 
BEFORE INSERT ON JOB 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.JOB_ID IS NULL THEN
      SELECT JOB_SEQ.NEXTVAL INTO :NEW.JOB_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."JOB_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table JOB
--------------------------------------------------------

  ALTER TABLE "SKOTT"."JOB" MODIFY ("JOB_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."JOB" MODIFY ("JOB_NAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."JOB" ADD CONSTRAINT "JOB_PK" PRIMARY KEY ("JOB_ID")
  USING INDEX "SKOTT"."JOB_PK"  ENABLE;
