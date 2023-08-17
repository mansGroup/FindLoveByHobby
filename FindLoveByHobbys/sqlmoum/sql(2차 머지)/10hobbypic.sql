--------------------------------------------------------
--  파일이 생성됨 - 금요일-7월-28-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table HOBBYPICTURE
--------------------------------------------------------

  CREATE TABLE "SKOTT"."HOBBYPICTURE" 
   (	"ID" VARCHAR2(20 BYTE), 
	"HOBBY_PIC1" VARCHAR2(20 BYTE), 
	"HOBBY_PIC2" VARCHAR2(20 BYTE), 
	"HOBBY_PIC3" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
REM INSERTING into SKOTT.HOBBYPICTURE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index HOBBYPICTURE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SKOTT"."HOBBYPICTURE_PK" ON "SKOTT"."HOBBYPICTURE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table HOBBYPICTURE
--------------------------------------------------------

  ALTER TABLE "SKOTT"."HOBBYPICTURE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "SKOTT"."HOBBYPICTURE" ADD CONSTRAINT "HOBBYPICTURE_PK" PRIMARY KEY ("ID")
  USING INDEX "SKOTT"."HOBBYPICTURE_PK"  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table HOBBYPICTURE
--------------------------------------------------------

  ALTER TABLE "SKOTT"."HOBBYPICTURE" ADD CONSTRAINT "FK3_ID" FOREIGN KEY ("ID")
	  REFERENCES "SKOTT"."PROFILE" ("USER_ID") ENABLE;
