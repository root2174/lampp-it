use `lampp-it`;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    company_id BIGINT NOT NULL,
    CONSTRAINT fk_product_company_id FOREIGN KEY (company_id) REFERENCES company (id)
);