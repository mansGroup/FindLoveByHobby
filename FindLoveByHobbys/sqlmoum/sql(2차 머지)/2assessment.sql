--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ASSESSMENT
--------------------------------------------------------

  CREATE TABLE "SKOTT"."ASSESSMENT" 
   (	"ID" VARCHAR2(20 BYTE), 
	"SEXY" NUMBER DEFAULT 0, 
	"BEAUTIFUL" NUMBER DEFAULT 0, 
	"CUTE" NUMBER, 
	"HANDSOME" NUMBER, 
	"WONDERFUL" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.ASSESSMENT
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index ASSESSMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."ASSESSMENT_PK" ON "SKOTT"."ASSESSMENT" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table ASSESSMENT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."ASSESSMENT" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."ASSESSMENT" ADD CONSTRAINT "ASSESSMENT_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."ASSESSMENT_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ASSESSMENT
--------------------------------------------------------

  ALTER TABLE "SKOTT"."ASSESSMENT" ADD CONSTRAINT "FK_ID" FOREIGN KEY ("ID")
	  REFERENCES "SKOTT"."USERINFO" ("ID") ENABLE;
