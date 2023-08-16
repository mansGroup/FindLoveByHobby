--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
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
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.QUESTION
SET DEFINE OFF;
Insert into SKOTT.QUESTION (ID,USERID,USERNICKNAME,USEREMAIL,QUESTIONOPTION,QUESTIONCONTENT,CREATED_TIME,MODIFIED_TIME,STATUS) values (1,'희영','강남멋쟁이','ee',1,'rrrrssss',to_timestamp('23/08/03 12:27:50.855261000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/03 13:21:00.324276000','RR/MM/DD HH24:MI:SSXFF'),0);
Insert into SKOTT.QUESTION (ID,USERID,USERNICKNAME,USEREMAIL,QUESTIONOPTION,QUESTIONCONTENT,CREATED_TIME,MODIFIED_TIME,STATUS) values (2,'희영','강남멋쟁이','ee',1,'내 이름은 고난, 시련이죠 집에가 고싶다 시부레',to_timestamp('23/08/03 12:28:21.128804000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/04 17:01:49.380902000','RR/MM/DD HH24:MI:SSXFF'),0);
Insert into SKOTT.QUESTION (ID,USERID,USERNICKNAME,USEREMAIL,QUESTIONOPTION,QUESTIONCONTENT,CREATED_TIME,MODIFIED_TIME,STATUS) values (3,'희영','강남멋쟁이','ee',4,'문의사항을 몇 줄까지 작성하면 안보이게 될지 궁금합니다. 얼마나 서야 할까요222 ㅋㅋ',null,to_timestamp('23/08/04 17:01:09.213872000','RR/MM/DD HH24:MI:SSXFF'),0);
Insert into SKOTT.QUESTION (ID,USERID,USERNICKNAME,USEREMAIL,QUESTIONOPTION,QUESTIONCONTENT,CREATED_TIME,MODIFIED_TIME,STATUS) values (4,'희영','강남멋쟁이','ee',1,'dddd',to_timestamp('23/08/04 17:00:59.834036000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/04 17:00:59.834036000','RR/MM/DD HH24:MI:SSXFF'),0);
--------------------------------------------------------
--  DDL for Index SYS_C008701
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."SYS_C008701" ON "SKOTT"."QUESTION" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
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
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
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