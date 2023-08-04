--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table RELIGION
--------------------------------------------------------

  CREATE TABLE "SKOTT"."RELIGION" 
   (	"RELIGION_ID" NUMBER(10,0), 
	"RELIGION_NAME" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.RELIGION
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index RELIGION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."RELIGION_PK" ON "SKOTT"."RELIGION" ("RELIGION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger RELIGION_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."RELIGION_TRG" 
BEFORE INSERT ON RELIGION 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.RELIGION_ID IS NULL THEN
      SELECT RELIGION_SEQ.NEXTVAL INTO :NEW.RELIGION_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."RELIGION_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table RELIGION
--------------------------------------------------------

  ALTER TABLE "SKOTT"."RELIGION" MODIFY ("RELIGION_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."RELIGION" MODIFY ("RELIGION_NAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."RELIGION" ADD CONSTRAINT "RELIGION_PK" PRIMARY KEY ("RELIGION_ID")
  USING INDEX "SKOTT"."RELIGION_PK"  ENABLE;
