--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SPEAKROOM
--------------------------------------------------------

  CREATE TABLE "SKOTT"."SPEAKROOM" 
   (	"ROOMID" NUMBER(10,0), 
	"SPEAKMEMBER1" VARCHAR2(20 BYTE), 
	"SPEAKMEMBER2" VARCHAR2(20 BYTE), 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.SPEAKROOM
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SPEAKROOM_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."SPEAKROOM_PK" ON "SKOTT"."SPEAKROOM" ("ROOMID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger SPEAKROOM_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."SPEAKROOM_TRG" 
BEFORE INSERT ON SPEAKROOM 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ROOMID IS NULL THEN
      SELECT SPEAKROOM_SEQ.NEXTVAL INTO :NEW.ROOMID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."SPEAKROOM_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table SPEAKROOM
--------------------------------------------------------

  ALTER TABLE "SKOTT"."SPEAKROOM" MODIFY ("ROOMID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."SPEAKROOM" ADD CONSTRAINT "SPEAKROOM_PK" PRIMARY KEY ("ROOMID")
  USING INDEX "SKOTT"."SPEAKROOM_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SPEAKROOM
--------------------------------------------------------

  ALTER TABLE "SKOTT"."SPEAKROOM" ADD CONSTRAINT "FK_SPEAKMEMBER1" FOREIGN KEY ("SPEAKMEMBER1")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
  ALTER TABLE "SKOTT"."SPEAKROOM" ADD CONSTRAINT "FK_SPEAKMEMBER2" FOREIGN KEY ("SPEAKMEMBER2")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
