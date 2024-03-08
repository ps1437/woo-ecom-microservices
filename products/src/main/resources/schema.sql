-- Drop tables if they exist

DROP TABLE IF EXISTS Products;

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


-- Insert sample data into Products table
INSERT INTO Products (name, description, price, brand, category, image_url, stock_quantity, is_available)
VALUES
    ('Laptop', 'High-performance laptop with SSD', 999.99, 'TechCorp', 'Electronics', 'laptop_image.jpg', 50, true),
    ('Smartphone', '4G smartphone with dual camera', 399.99, 'MobileTech', 'Electronics', 'phone_image.jpg', 100, true),
    ('Running Shoes', 'Comfortable running shoes for athletes', 79.99, 'FootFit', 'Apparel', 'shoes_image.jpg', 200, true),
    ('Coffee Maker', 'Programmable coffee maker with grinder', 129.99, 'BrewMaster', 'Appliances', 'coffee_maker_image.jpg', 30, true),
    ('Backpack', 'Durable backpack with multiple compartments', 49.99, 'OutdoorGear', 'Accessories', 'backpack_image.jpg', 150, true);

