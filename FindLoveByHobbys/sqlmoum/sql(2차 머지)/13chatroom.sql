--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CHATROOM
--------------------------------------------------------

  CREATE TABLE "SKOTT"."CHATROOM" 
   (	"CONTENTID" NUMBER(10,0), 
	"MALEID" VARCHAR2(20 BYTE), 
	"FEMALEID" VARCHAR2(20 BYTE), 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.CHATROOM
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index CHATROOM_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."CHATROOM_PK" ON "SKOTT"."CHATROOM" ("CONTENTID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger CHATROOM_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."CHATROOM_TRG" 
BEFORE INSERT ON CHATROOM 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."CHATROOM_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CHATROOM_TRG1
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."CHATROOM_TRG1" 
BEFORE INSERT ON CHATROOM 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.CONTENTID IS NULL THEN
      SELECT CHATROOM_SEQ1.NEXTVAL INTO :NEW.CONTENTID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."CHATROOM_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table CHATROOM
--------------------------------------------------------

  ALTER TABLE "SKOTT"."CHATROOM" MODIFY ("CONTENTID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."CHATROOM" ADD CONSTRAINT "CHATROOM_PK" PRIMARY KEY ("CONTENTID")
  USING INDEX "SKOTT"."CHATROOM_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CHATROOM
--------------------------------------------------------

  ALTER TABLE "SKOTT"."CHATROOM" ADD CONSTRAINT "FK_MALEID" FOREIGN KEY ("MALEID")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
  ALTER TABLE "SKOTT"."CHATROOM" ADD CONSTRAINT "FK_FEMALEID" FOREIGN KEY ("FEMALEID")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
