-- Drop tables if they exist

DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Order_items;
-- Drop the table if it exists
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS users;


-- Table to store information about products
CREATE TABLE IF NOT EXISTS Products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    brand VARCHAR(100),
    category VARCHAR(100),
    image_url VARCHAR(255),
    stock_quantity INT,
    is_available BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE Orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(255) NOT NULL,
    order_date TIMESTAMP NOT NULL
);

CREATE TABLE Order_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);


-- Create table for shopping carts
CREATE TABLE carts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    total DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create table for cart items
CREATE TABLE cart_items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);



-- Create the users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    -- Additional fields as needed
);

-- Insert sample data
INSERT INTO users (username, password) VALUES
    ('john_doe', 'password123'),
    ('alice_smith', 'pass456'),
    ('bob_jackson', 'securePass');


-- Insert sample data into Products table
INSERT INTO Products (name, description, price, brand, category, image_url, stock_quantity, is_available)
VALUES
    ('Laptop', 'High-performance laptop with SSD', 999.99, 'TechCorp', 'Electronics', 'laptop_image.jpg', 50, true),
    ('Smartphone', '4G smartphone with dual camera', 399.99, 'MobileTech', 'Electronics', 'phone_image.jpg', 100, true),
    ('Running Shoes', 'Comfortable running shoes for athletes', 79.99, 'FootFit', 'Apparel', 'shoes_image.jpg', 200, true),
    ('Coffee Maker', 'Programmable coffee maker with grinder', 129.99, 'BrewMaster', 'Appliances', 'coffee_maker_image.jpg', 30, true),
    ('Backpack', 'Durable backpack with multiple compartments', 49.99, 'OutdoorGear', 'Accessories', 'backpack_image.jpg', 150, true);



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



-- Insert sample data into carts table
INSERT INTO carts (user_id, total) VALUES
    (1, 0.0),
    (2, 0.0);

-- Insert sample data into cart_items table
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
    (1, 1, 2), -- User 1 adds 2 units of product 1 to their cart
    (1, 3, 1); -- User 1 adds 1 unit of product 3 to their cart

