-- Script para limpar completamente o banco de dados
-- Execute este script no MySQL/MariaDB antes de reiniciar o Quarkus

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS cupulagame_db;
CREATE DATABASE cupulagame_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS=1;
