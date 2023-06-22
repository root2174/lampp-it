use `lampp-it`;

CREATE TABLE IF NOT EXISTS company (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     open_hour INT NOT NULL,
     closed_hour INT NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     name VARCHAR(255) NOT NULL,
     cnpj VARCHAR(255) NOT NULL,
     state_registration VARCHAR(255) NOT NULL
);