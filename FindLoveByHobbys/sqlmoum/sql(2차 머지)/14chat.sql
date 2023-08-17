--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CHAT
--------------------------------------------------------

  CREATE TABLE "SKOTT"."CHAT" 
   (	"CONTENTID" NUMBER(10,0), 
	"CHATID" NUMBER(10,0), 
	"CONVERSATION" VARCHAR2(2000 BYTE), 
	"TEXTTYPE" NUMBER DEFAULT 0, 
	"CREATED_TIME" TIMESTAMP (6)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.CHAT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index CHAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."CHAT_PK" ON "SKOTT"."CHAT" ("CHATID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger CHAT_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."CHAT_TRG" 
BEFORE INSERT ON CHAT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.CHATID IS NULL THEN
      SELECT CHAT_SEQ.NEXTVAL INTO :NEW.CHATID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."CHAT_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table CHAT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."CHAT" MODIFY ("CHATID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."CHAT" MODIFY ("CONVERSATION" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."CHAT" ADD CONSTRAINT "CHAT_PK" PRIMARY KEY ("CHATID")
  USING INDEX "SKOTT"."CHAT_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CHAT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."CHAT" ADD CONSTRAINT "FK_CONTENTID" FOREIGN KEY ("CONTENTID")
	  REFERENCES "SKOTT"."CHATROOM" ("CONTENTID") ENABLE;
