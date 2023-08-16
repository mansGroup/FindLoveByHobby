--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-11-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table QUESTIONREPLY
--------------------------------------------------------

  CREATE TABLE "SKOTT"."QUESTIONREPLY" 
   (	"REPLYID" NUMBER, 
	"REPLYCONTENT" VARCHAR2(500 BYTE), 
	"QUESTIONID" NUMBER, 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp, 
	"MODIFIED_TIME" TIMESTAMP (6) DEFAULT systimestamp
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index QUESTIONREPLY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."QUESTIONREPLY_PK" ON "SKOTT"."QUESTIONREPLY" ("REPLYID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger QUESTIONREPLY_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."QUESTIONREPLY_TRG" 
BEFORE INSERT ON QUESTIONREPLY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."QUESTIONREPLY_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger QUESTIONREPLY_TRG1
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."QUESTIONREPLY_TRG1" 
BEFORE INSERT ON QUESTIONREPLY 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.REPLYID IS NULL THEN
      SELECT QUESTIONREPLY_SEQ.NEXTVAL INTO :NEW.REPLYID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."QUESTIONREPLY_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table QUESTIONREPLY
--------------------------------------------------------

  ALTER TABLE "SKOTT"."QUESTIONREPLY" MODIFY ("REPLYID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTIONREPLY" MODIFY ("REPLYCONTENT" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTIONREPLY" MODIFY ("QUESTIONID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTIONREPLY" ADD CONSTRAINT "QUESTIONREPLY_PK" PRIMARY KEY ("REPLYID")
  USING INDEX "SKOTT"."QUESTIONREPLY_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table QUESTIONREPLY
--------------------------------------------------------

  ALTER TABLE "SKOTT"."QUESTIONREPLY" ADD CONSTRAINT "FK_QUESTIONID" FOREIGN KEY ("QUESTIONID")
	  REFERENCES "SKOTT"."QUESTION" ("ID") ENABLE;
