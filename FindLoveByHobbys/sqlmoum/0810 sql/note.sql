--------------------------------------------------------
--  파일이 생성됨 - 화요일-8월-08-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table NOTE
--------------------------------------------------------

  CREATE TABLE "SKOTT"."NOTE" 
   (	"SENDER" VARCHAR2(20 CHAR), 
	"RECIPIENT" VARCHAR2(20 CHAR), 
	"ID" NUMBER(10,0), 
	"TYPE" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.NOTE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Trigger NOTE_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."NOTE_TRG" 
BEFORE INSERT ON NOTE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT NOTE_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."NOTE_TRG" ENABLE;
