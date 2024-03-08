-- Drop tables if they exist

DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Order_items;


CREATE TABLE Orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255) NOT NULL,
    order_date TIMESTAMP NOT NULL
);

CREATE TABLE Order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL
);


-- Insert sample orders
INSERT INTO orders (customer_name, order_date) VALUES
('Alice Johnson', '2024-03-03 10:00:00'),
('Bob Williams', '2024-03-03 11:30:00');

-- Insert order items for the first order
INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 1, 2), -- Laptop
(1, 3, 1), -- Running Shoes

-- Insert order items for the second order
(2, 2, 3), -- Smartphone
(2, 4, 1); -- Coffee Maker

