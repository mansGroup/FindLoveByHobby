--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACADEMIC
--------------------------------------------------------

  CREATE TABLE "SKOTT"."ACADEMIC" 
   (	"ACADEMIC_ID" NUMBER(10,0), 
	"ACADEMIC_NAME" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.ACADEMIC
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index ACADEMIC_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."ACADEMIC_PK" ON "SKOTT"."ACADEMIC" ("ACADEMIC_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger ACADEMIC_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."ACADEMIC_TRG" 
BEFORE INSERT ON ACADEMIC 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."ACADEMIC_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ACADEMIC_TRG1
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."ACADEMIC_TRG1" 
BEFORE INSERT ON ACADEMIC 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ACADEMIC_ID IS NULL THEN
      SELECT ACADEMIC_SEQ2.NEXTVAL INTO :NEW.ACADEMIC_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."ACADEMIC_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table ACADEMIC
--------------------------------------------------------

  ALTER TABLE "SKOTT"."ACADEMIC" MODIFY ("ACADEMIC_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."ACADEMIC" MODIFY ("ACADEMIC_NAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."ACADEMIC" ADD CONSTRAINT "ACADEMIC_PK" PRIMARY KEY ("ACADEMIC_ID")
  USING INDEX "SKOTT"."ACADEMIC_PK"  ENABLE;
