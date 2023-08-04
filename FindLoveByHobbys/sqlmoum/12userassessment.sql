--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USER_ASSESSMENT
--------------------------------------------------------

  CREATE TABLE "SKOTT"."USER_ASSESSMENT" 
   (	"ID" NUMBER(*,0) DEFAULT 0, 
	"SENDER" VARCHAR2(40 BYTE), 
	"GETTER" VARCHAR2(40 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.USER_ASSESSMENT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index USER_ASSESSMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."USER_ASSESSMENT_PK" ON "SKOTT"."USER_ASSESSMENT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger USER_ASSESSMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."USER_ASSESSMENT_TRG" 
BEFORE INSERT ON USER_ASSESSMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT USER_ASSESSMENT_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."USER_ASSESSMENT_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table USER_ASSESSMENT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."USER_ASSESSMENT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."USER_ASSESSMENT" ADD CONSTRAINT "USER_ASSESSMENT_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."USER_ASSESSMENT_PK"  ENABLE;
