#!/bin/sh

mysqldump --compatible=postgresql --default-character-set=utf8 -r database/crowdchef.mysql -u root crowdchef

python database/mysql-postgresql-converter/db_converter.py database/crowdchef.mysql database/crowdchef.psql
rm database/crowdchef.mysql

psql -U postgres -d crowdchef -f database/recreate_schema.psql
psql -U postgres -d crowdchef -f database/crowdchef.psql

psql -U postgres -d crowdchef -f database/populate_crowdchef.sql



