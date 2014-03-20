-- Converted by db_converter
START TRANSACTION;
SET standard_conforming_strings=off;
SET escape_string_warning=off;
SET CONSTRAINTS ALL DEFERRED;

CREATE TABLE "badge" (
    "id" bigint NOT NULL,
    "name" varchar(90) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "image" bytea ,
    "ord" integer DEFAULT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "ingr_instance" (
    "id" bigint NOT NULL,
    "brand" varchar(90) DEFAULT NULL,
    "name" varchar(200) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "ingr_instance_has_location" (
    "ingr_instance_id" bigint NOT NULL,
    "location_id" integer NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("ingr_instance_id","location_id")
);

CREATE TABLE "ingr_instance_ratings" (
    "id" bigint NOT NULL,
    "ingredient_has_ingr_instance_Ingredient_id" bigint NOT NULL,
    "ingredient_has_ingr_instance_IngredientInstance_id" bigint NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "ingredient" (
    "id" bigint NOT NULL,
    "recipe_id" bigint NOT NULL,
    "name" varchar(200) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "quantity" varchar(90) DEFAULT NULL,
    "ord" integer DEFAULT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "ingredient" VALUES (1,1,'tomato sauce','spicy tomato sauce is best','200 mL',2),(2,1,'chicken breast',NULL,'400 g',1);
CREATE TABLE "ingredient_has_ingr_instance" (
    "Ingredient_id" bigint NOT NULL,
    "IngredientInstance_id" bigint NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("Ingredient_id","IngredientInstance_id")
);

CREATE TABLE "location" (
    "id" integer NOT NULL,
    "name" varchar(90) DEFAULT NULL,
    "store" varchar(200) DEFAULT NULL,
    "store_url" varchar(510) DEFAULT NULL,
    "geo_location" varchar(90) DEFAULT NULL,
    "city" varchar(90) DEFAULT NULL,
    "country" varchar(90) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "recipe" (
    "id" bigint NOT NULL,
    "name" varchar(200) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "directions" varchar(510) DEFAULT NULL,
    "tags" varchar(510) DEFAULT NULL,
    "url" varchar(510) DEFAULT NULL,
    "image" oid ,
    "image_url" varchar(510) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "recipe" VALUES (1,'Perfect recipe','Awesome recipe for a recipe','Stir recipe until becomes recipe','perfect,inception',NULL,NULL,NULL,'2014-03-18 10:16:40',1);
CREATE TABLE "reviews" (
    "id" integer NOT NULL,
    "recipe_id" bigint NOT NULL,
    "description" varchar(510) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "taste_category" (
    "id" bigint NOT NULL,
    "type" varchar(2) DEFAULT NULL,
    "name" varchar(90) DEFAULT NULL,
    "ord" integer DEFAULT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "taste_score" (
    "id" bigint NOT NULL,
    "taste_category_id" bigint NOT NULL,
    "recipe_id" bigint NOT NULL,
    "score" integer DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "app_user" (
    "id" bigint NOT NULL,
    "username" varchar(32) NOT NULL,
    "password" varchar(64) NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("id"),
    UNIQUE ("username")
);

INSERT INTO "app_user" VALUES (1,'john','john','2014-03-18 10:16:40'),(2,'sara@fjk.com','hello','2014-03-18 11:53:31');
CREATE TABLE "app_user_has_badge" (
    "app_user_id" bigint NOT NULL,
    "badge_id" bigint NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY ("app_user_id","badge_id")
);

CREATE TABLE "app_user_info" (
    "id" bigint NOT NULL,
    "app_user_id" bigint NOT NULL,
    "email" varchar(510) DEFAULT NULL,
    "address" varchar(510) DEFAULT NULL,
    "city" varchar(90) DEFAULT NULL,
    "country" varchar(90) DEFAULT NULL,
    PRIMARY KEY ("id"),
    UNIQUE ("email")
);

INSERT INTO "app_user_info" VALUES (1,1,'john@gmail.com','Some Street 124','Rotterdam','Netherlands'),(2,2,NULL,NULL,NULL,NULL);
CREATE TABLE "wine" (
    "id" bigint NOT NULL,
    "recipe_id" bigint NOT NULL,
    "name" varchar(200) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id"),
    UNIQUE ("name")
);

CREATE TABLE "wine_has_wine_instance" (
    "wine_id" bigint NOT NULL,
    "wine_instance_id" bigint NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("wine_id","wine_instance_id")
);

CREATE TABLE "wine_instance" (
    "id" bigint NOT NULL,
    "brand" varchar(90) DEFAULT NULL,
    "name" varchar(200) DEFAULT NULL,
    "description" varchar(510) DEFAULT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "wine_instance_has_location" (
    "wine_instance_id" bigint NOT NULL,
    "location_id" integer NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("wine_instance_id","location_id")
);

CREATE TABLE "wine_instance_ratings" (
    "id" bigint NOT NULL,
    "wine_has_wine_instance_wine_id" bigint NOT NULL,
    "wine_has_wine_instance_wine_instance_id" bigint NOT NULL,
    "create_time" timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    "app_user_id" bigint NOT NULL,
    PRIMARY KEY ("id","wine_has_wine_instance_wine_id","wine_has_wine_instance_wine_instance_id")
);


-- Post-data save --
COMMIT;
START TRANSACTION;

-- Typecasts --

-- Foreign keys --
ALTER TABLE "ingr_instance" ADD CONSTRAINT "fk_ingr_instance_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance" ("app_user_id");
ALTER TABLE "ingr_instance_has_location" ADD CONSTRAINT "fk_ingr_instance_has_location_ingr_instance1" FOREIGN KEY ("ingr_instance_id") REFERENCES "ingr_instance" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance_has_location" ("ingr_instance_id");
ALTER TABLE "ingr_instance_has_location" ADD CONSTRAINT "fk_ingr_instance_has_location_location1" FOREIGN KEY ("location_id") REFERENCES "location" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance_has_location" ("location_id");
ALTER TABLE "ingr_instance_has_location" ADD CONSTRAINT "fk_ingr_instance_has_location_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance_has_location" ("app_user_id");
ALTER TABLE "ingr_instance_ratings" ADD CONSTRAINT "fk_ingr_instance_ratings_ingredient_has_ingr_instance1" FOREIGN KEY ("ingredient_has_ingr_instance_Ingredient_id", "ingredient_has_ingr_instance_IngredientInstance_id") REFERENCES "ingredient_has_ingr_instance" ("Ingredient_id", "IngredientInstance_id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance_ratings" ("ingredient_has_ingr_instance_Ingredient_id", "ingredient_has_ingr_instance_IngredientInstance_id");
ALTER TABLE "ingr_instance_ratings" ADD CONSTRAINT "fk_ingr_instance_ratings_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingr_instance_ratings" ("app_user_id");
ALTER TABLE "ingredient" ADD CONSTRAINT "fk_ingredient_recipe1" FOREIGN KEY ("recipe_id") REFERENCES "recipe" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingredient" ("recipe_id");
ALTER TABLE "ingredient_has_ingr_instance" ADD CONSTRAINT "fk_Ingredient_has_IngredientInstance_Ingredient1" FOREIGN KEY ("Ingredient_id") REFERENCES "ingredient" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingredient_has_ingr_instance" ("Ingredient_id");
ALTER TABLE "ingredient_has_ingr_instance" ADD CONSTRAINT "fk_Ingredient_has_IngredientInstance_IngredientInstance1" FOREIGN KEY ("IngredientInstance_id") REFERENCES "ingr_instance" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingredient_has_ingr_instance" ("IngredientInstance_id");
ALTER TABLE "ingredient_has_ingr_instance" ADD CONSTRAINT "fk_ingredient_has_ingr_instance_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "ingredient_has_ingr_instance" ("app_user_id");
ALTER TABLE "location" ADD CONSTRAINT "fk_location_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "location" ("app_user_id");
ALTER TABLE "recipe" ADD CONSTRAINT "fk_recipe_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "recipe" ("app_user_id");
ALTER TABLE "reviews" ADD CONSTRAINT "fk_reviews_recipe1" FOREIGN KEY ("recipe_id") REFERENCES "recipe" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "reviews" ("recipe_id");
ALTER TABLE "reviews" ADD CONSTRAINT "fk_reviews_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "reviews" ("app_user_id");
ALTER TABLE "taste_score" ADD CONSTRAINT "fk_taste_score_recipe1" FOREIGN KEY ("recipe_id") REFERENCES "recipe" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "taste_score" ("recipe_id");
ALTER TABLE "taste_score" ADD CONSTRAINT "fk_taste_score_taste_category1" FOREIGN KEY ("taste_category_id") REFERENCES "taste_category" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "taste_score" ("taste_category_id");
ALTER TABLE "taste_score" ADD CONSTRAINT "fk_taste_score_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "taste_score" ("app_user_id");
ALTER TABLE "app_user_has_badge" ADD CONSTRAINT "fk_app_user_has_badge_badge1" FOREIGN KEY ("badge_id") REFERENCES "badge" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "app_user_has_badge" ("badge_id");
ALTER TABLE "app_user_has_badge" ADD CONSTRAINT "fk_app_user_has_badge_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "app_user_has_badge" ("app_user_id");
ALTER TABLE "app_user_info" ADD CONSTRAINT "fk_app_user_info_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "app_user_info" ("app_user_id");
ALTER TABLE "wine" ADD CONSTRAINT "fk_wine_recipe1" FOREIGN KEY ("recipe_id") REFERENCES "recipe" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine" ("recipe_id");
ALTER TABLE "wine" ADD CONSTRAINT "fk_wine_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine" ("app_user_id");
ALTER TABLE "wine_has_wine_instance" ADD CONSTRAINT "fk_wine_has_wine_instance_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_has_wine_instance" ("app_user_id");
ALTER TABLE "wine_has_wine_instance" ADD CONSTRAINT "fk_wine_has_wine_instance_wine1" FOREIGN KEY ("wine_id") REFERENCES "wine" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_has_wine_instance" ("wine_id");
ALTER TABLE "wine_has_wine_instance" ADD CONSTRAINT "fk_wine_has_wine_instance_wine_instance1" FOREIGN KEY ("wine_instance_id") REFERENCES "wine_instance" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_has_wine_instance" ("wine_instance_id");
ALTER TABLE "wine_instance" ADD CONSTRAINT "fk_wine_instance_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance" ("app_user_id");
ALTER TABLE "wine_instance_has_location" ADD CONSTRAINT "fk_wine_instance_has_location_location1" FOREIGN KEY ("location_id") REFERENCES "location" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance_has_location" ("location_id");
ALTER TABLE "wine_instance_has_location" ADD CONSTRAINT "fk_wine_instance_has_location_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance_has_location" ("app_user_id");
ALTER TABLE "wine_instance_has_location" ADD CONSTRAINT "fk_wine_instance_has_location_wine_instance1" FOREIGN KEY ("wine_instance_id") REFERENCES "wine_instance" ("id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance_has_location" ("wine_instance_id");
ALTER TABLE "wine_instance_ratings" ADD CONSTRAINT "fk_wine_instance_ratings_app_user1" FOREIGN KEY ("app_user_id") REFERENCES "app_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance_ratings" ("app_user_id");
ALTER TABLE "wine_instance_ratings" ADD CONSTRAINT "fk_wine_instance_ratings_wine_has_wine_instance1" FOREIGN KEY ("wine_has_wine_instance_wine_id", "wine_has_wine_instance_wine_instance_id") REFERENCES "wine_has_wine_instance" ("wine_id", "wine_instance_id") ON DELETE CASCADE ON UPDATE CASCADE DEFERRABLE INITIALLY DEFERRED;
CREATE INDEX ON "wine_instance_ratings" ("wine_has_wine_instance_wine_id", "wine_has_wine_instance_wine_instance_id");

-- Sequences --
CREATE SEQUENCE badge_id_seq;
SELECT setval('badge_id_seq', max(id)) FROM badge;
ALTER TABLE "badge" ALTER COLUMN "id" SET DEFAULT nextval('badge_id_seq');
CREATE SEQUENCE ingr_instance_id_seq;
SELECT setval('ingr_instance_id_seq', max(id)) FROM ingr_instance;
ALTER TABLE "ingr_instance" ALTER COLUMN "id" SET DEFAULT nextval('ingr_instance_id_seq');
CREATE SEQUENCE ingr_instance_ratings_id_seq;
SELECT setval('ingr_instance_ratings_id_seq', max(id)) FROM ingr_instance_ratings;
ALTER TABLE "ingr_instance_ratings" ALTER COLUMN "id" SET DEFAULT nextval('ingr_instance_ratings_id_seq');
CREATE SEQUENCE ingredient_id_seq;
SELECT setval('ingredient_id_seq', max(id)) FROM ingredient;
ALTER TABLE "ingredient" ALTER COLUMN "id" SET DEFAULT nextval('ingredient_id_seq');
CREATE SEQUENCE location_id_seq;
SELECT setval('location_id_seq', max(id)) FROM location;
ALTER TABLE "location" ALTER COLUMN "id" SET DEFAULT nextval('location_id_seq');
CREATE SEQUENCE recipe_id_seq;
SELECT setval('recipe_id_seq', max(id)) FROM recipe;
ALTER TABLE "recipe" ALTER COLUMN "id" SET DEFAULT nextval('recipe_id_seq');
CREATE SEQUENCE reviews_id_seq;
SELECT setval('reviews_id_seq', max(id)) FROM reviews;
ALTER TABLE "reviews" ALTER COLUMN "id" SET DEFAULT nextval('reviews_id_seq');
CREATE SEQUENCE taste_category_id_seq;
SELECT setval('taste_category_id_seq', max(id)) FROM taste_category;
ALTER TABLE "taste_category" ALTER COLUMN "id" SET DEFAULT nextval('taste_category_id_seq');
CREATE SEQUENCE taste_score_id_seq;
SELECT setval('taste_score_id_seq', max(id)) FROM taste_score;
ALTER TABLE "taste_score" ALTER COLUMN "id" SET DEFAULT nextval('taste_score_id_seq');
CREATE SEQUENCE app_user_id_seq;
SELECT setval('app_user_id_seq', max(id)) FROM app_user;
ALTER TABLE "app_user" ALTER COLUMN "id" SET DEFAULT nextval('app_user_id_seq');
CREATE SEQUENCE app_user_info_id_seq;
SELECT setval('app_user_info_id_seq', max(id)) FROM app_user_info;
ALTER TABLE "app_user_info" ALTER COLUMN "id" SET DEFAULT nextval('app_user_info_id_seq');
CREATE SEQUENCE wine_id_seq;
SELECT setval('wine_id_seq', max(id)) FROM wine;
ALTER TABLE "wine" ALTER COLUMN "id" SET DEFAULT nextval('wine_id_seq');
CREATE SEQUENCE wine_instance_id_seq;
SELECT setval('wine_instance_id_seq', max(id)) FROM wine_instance;
ALTER TABLE "wine_instance" ALTER COLUMN "id" SET DEFAULT nextval('wine_instance_id_seq');
CREATE SEQUENCE wine_instance_ratings_id_seq;
SELECT setval('wine_instance_ratings_id_seq', max(id)) FROM wine_instance_ratings;
ALTER TABLE "wine_instance_ratings" ALTER COLUMN "id" SET DEFAULT nextval('wine_instance_ratings_id_seq');

-- Full Text keys --

COMMIT;
