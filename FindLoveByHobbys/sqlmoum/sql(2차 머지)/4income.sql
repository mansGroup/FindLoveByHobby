--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table INCOME
--------------------------------------------------------

  CREATE TABLE "SKOTT"."INCOME" 
   (	"INCOME_ID" NUMBER(10,0), 
	"INCOME" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.INCOME
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index INCOME_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."INCOME_PK" ON "SKOTT"."INCOME" ("INCOME_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger INCOME_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."INCOME_TRG" 
BEFORE INSERT ON INCOME 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."INCOME_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger INCOME_TRG1
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."INCOME_TRG1" 
BEFORE INSERT ON INCOME 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.INCOME_ID IS NULL THEN
      SELECT INCOME_SEQ1.NEXTVAL INTO :NEW.INCOME_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."INCOME_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table INCOME
--------------------------------------------------------

  ALTER TABLE "SKOTT"."INCOME" MODIFY ("INCOME_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."INCOME" MODIFY ("INCOME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."INCOME" ADD CONSTRAINT "INCOME_PK" PRIMARY KEY ("INCOME_ID")
  USING INDEX "SKOTT"."INCOME_PK"  ENABLE;
