--------------------------------------------------------
--  파일이 생성됨 - 금요일-8월-04-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table AGE
--------------------------------------------------------

  CREATE TABLE "SKOTT"."AGE" 
   (	"AGE_ID" VARCHAR2(20 BYTE), 
	"AGE_NAME" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.AGE
SET DEFINE OFF;
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('1','20살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('2','21살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('3','22살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('4','23살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('5','24살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('6','25살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('7','26살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('8','27살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('9','28살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('10','29살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('11','30살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('12','31살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('13','32살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('14','33살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('15','34살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('16','35살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('17','36살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('18','37살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('19','38살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('20','39살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('21','40살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('22','41살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('23','42살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('24','43살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('25','44살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('26','45살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('27','46살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('28','47살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('29','48살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('30','49살');
Insert into SKOTT.AGE (AGE_ID,AGE_NAME) values ('31','50살');
--------------------------------------------------------
--  DDL for Index AGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."AGE_PK" ON "SKOTT"."AGE" ("AGE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger AGE_TRG
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."AGE_TRG" 
BEFORE INSERT ON AGE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SKOTT"."AGE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AGE_TRG1
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE TRIGGER "SKOTT"."AGE_TRG1" 
BEFORE INSERT ON AGE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SKOTT"."AGE_TRG1" ENABLE;
--------------------------------------------------------
--  Constraints for Table AGE
--------------------------------------------------------

  ALTER TABLE "SKOTT"."AGE" MODIFY ("AGE_ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."AGE" ADD CONSTRAINT "AGE_PK" PRIMARY KEY ("AGE_ID")
  USING INDEX "SKOTT"."AGE_PK"  ENABLE;
