--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SPEAKCHAT
--------------------------------------------------------

  CREATE TABLE "SKOTT"."SPEAKCHAT" 
   (	"ID" NUMBER(10,0), 
	"CHATFILE" VARCHAR2(2000 CHAR), 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp, 
	"REPORT" NUMBER(10,0), 
	"ROOMID" NUMBER(10,0)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.SPEAKCHAT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SPEAKCHAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."SPEAKCHAT_PK" ON "SKOTT"."SPEAKCHAT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger SPEAKCHAT_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."SPEAKCHAT_TRG" 
BEFORE INSERT ON SPEAKCHAT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID IS NULL THEN
      SELECT SPEAKCHAT_SEQ.NEXTVAL INTO :NEW.ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."SPEAKCHAT_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table SPEAKCHAT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."SPEAKCHAT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."SPEAKCHAT" ADD CONSTRAINT "SPEAKCHAT_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."SPEAKCHAT_PK"  ENABLE;
  ALTER TABLE "SKOTT"."SPEAKCHAT" ADD CONSTRAINT "SC_REPORT_CK" CHECK (REPORT BETWEEN 0 AND 1000) ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SPEAKCHAT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."SPEAKCHAT" ADD CONSTRAINT "FK_ROOMID" FOREIGN KEY ("ROOMID")
	  REFERENCES "SKOTT"."SPEAKROOM" ("ROOMID") ENABLE;
