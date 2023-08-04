--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table HOBBY
--------------------------------------------------------

  CREATE TABLE "SKOTT"."HOBBY" 
   (	"HOBBY_ID" NUMBER(10,0), 
	"CONTENT" VARCHAR2(20 CHAR), 
	"HOBBY_NAME" VARCHAR2(20 CHAR)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.HOBBY
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index HOBBY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."HOBBY_PK" ON "SKOTT"."HOBBY" ("HOBBY_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger HOBBY_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."HOBBY_TRG" 
BEFORE INSERT ON HOBBY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.HOBBY_ID IS NULL THEN
      SELECT HOBBY_SEQ.NEXTVAL INTO :NEW.HOBBY_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."HOBBY_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table HOBBY
--------------------------------------------------------

  ALTER TABLE "SKOTT"."HOBBY" MODIFY ("HOBBY_NAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."HOBBY" ADD CONSTRAINT "HOBBY_PK" PRIMARY KEY ("HOBBY_ID")
  USING INDEX "SKOTT"."HOBBY_PK"  ENABLE;
  ALTER TABLE "SKOTT"."HOBBY" MODIFY ("HOBBY_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."HOBBY" MODIFY ("CONTENT" NOT NULL ENABLE);
