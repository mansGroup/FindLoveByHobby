--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table PICTURE
--------------------------------------------------------

  CREATE TABLE "SKOTT"."PICTURE" 
   (	"ID" VARCHAR2(20 BYTE), 
	"PIC1" VARCHAR2(20 BYTE), 
	"PIC2" VARCHAR2(20 BYTE), 
	"PIC3" VARCHAR2(20 BYTE), 
	"PIC4" VARCHAR2(20 BYTE), 
	"PIC5" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.PICTURE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index PICTURE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."PICTURE_PK" ON "SKOTT"."PICTURE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table PICTURE
--------------------------------------------------------

  ALTER TABLE "SKOTT"."PICTURE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."PICTURE" ADD CONSTRAINT "PICTURE_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."PICTURE_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PICTURE
--------------------------------------------------------

  ALTER TABLE "SKOTT"."PICTURE" ADD CONSTRAINT "FK2_ID" FOREIGN KEY ("ID")
	  REFERENCES "SKOTT"."PROFILE" ("USER_ID") ENABLE;
