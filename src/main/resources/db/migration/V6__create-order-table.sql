CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    payment_method VARCHAR(50),
    delivery_method VARCHAR(50),
    status VARCHAR(50),
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES `lampp-it`.customers (id)
);