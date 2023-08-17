--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table LIKES
--------------------------------------------------------

  CREATE TABLE "SKOTT"."LIKES" 
   (	"SENDER" VARCHAR2(20 BYTE), 
	"ID" NUMBER(10,0), 
	"RECIPIENT" VARCHAR2(20 BYTE), 
	"WHETHER" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.LIKES
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index LISK_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."LISK_PK" ON "SKOTT"."LIKES" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger LISK_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."LISK_TRG" 
BEFORE INSERT ON "LIKES" 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."LISK_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger LIKES_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."LIKES_TRG" 
BEFORE INSERT ON LIKES 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT LIKES_SEQ3.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."LIKES_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table LIKES
--------------------------------------------------------

  ALTER TABLE "SKOTT"."LIKES" MODIFY ("SENDER" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."LIKES" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."LIKES" ADD CONSTRAINT "LISK_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."LISK_PK"  ENABLE;
  ALTER TABLE "SKOTT"."LIKES" ADD CONSTRAINT "WT_WHETHER_CK" CHECK (WHETHER BETWEEN 0 AND 1) ENABLE;
