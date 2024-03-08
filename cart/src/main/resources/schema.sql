-- Drop tables if they exist

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS cart_items;

-- Create table for shopping carts
CREATE TABLE cart (
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
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data into carts table
INSERT INTO cart (user_id, total) VALUES
    (1, 0.0),
    (2, 0.0);

-- Insert sample data into cart_items table
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
    (1, 1, 2), -- User 1 adds 2 units of product 1 to their cart
    (1, 3, 1); -- User 1 adds 1 unit of product 3 to their cart

