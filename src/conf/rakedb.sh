#!/bin/bash

MYSQL=`which mysql`
VALUE='zombierescue2'

Q1="DROP DATABASE IF EXISTS ${VALUE};"
Q2="CREATE DATABASE IF NOT EXISTS ${VALUE};"
Q3="GRANT ALL ON ${VALUE}.* TO ${VALUE}@'localhost' IDENTIFIED BY '${VALUE}';"
Q4="GRANT ALL PRIVILEGES ON ${VALUE}.* TO '${VALUE}'@localhost;"
Q5="USE ${VALUE};"
SQL="${Q1}${Q2}${Q3}${Q4}${Q5}"

$MYSQL -u root -p -e "$SQL"
