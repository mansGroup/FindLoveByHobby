--------------------------------------------------------
--  파일이 생성됨 - 수요일-8월-02-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table QUESTION
--------------------------------------------------------

  CREATE TABLE "SKOTT"."QUESTION" 
   (	"ID" NUMBER(10,0), 
	"USERID" VARCHAR2(40 CHAR), 
	"USERNICKNAME" VARCHAR2(20 CHAR), 
	"USEREMAIL" VARCHAR2(100 CHAR), 
	"QUESTIONOPTION" NUMBER(1,0), 
	"QUESTIONCONTENT" VARCHAR2(500 BYTE), 
	"CREATED_TIME" TIMESTAMP (6) DEFAULT systimestamp, 
	"MODIFIED_TIME" TIMESTAMP (6) DEFAULT systimestamp, 
	"STATUS" NUMBER(1,0) DEFAULT 0
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.QUESTION
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index SYS_C008701
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."SYS_C008701" ON "SKOTT"."QUESTION" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger QUESTION_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."QUESTION_TRG" 
BEFORE INSERT ON QUESTION 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."QUESTION_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table QUESTION
--------------------------------------------------------

  ALTER TABLE "SKOTT"."QUESTION" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "SKOTT"."QUESTION" MODIFY ("USERID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTION" MODIFY ("USERNICKNAME" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTION" MODIFY ("USEREMAIL" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."QUESTION" MODIFY ("QUESTIONOPTION" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table QUESTION
--------------------------------------------------------

  ALTER TABLE "SKOTT"."QUESTION" ADD FOREIGN KEY ("USERID")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
