CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS editoras (
    IDT_COD INT AUTO_INCREMENT PRIMARY KEY,
    NOME_EDITORA VARCHAR(50) UNIQUE NOT NULL
);
