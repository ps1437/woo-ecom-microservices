-- Drop tables if they exist

DROP TABLE IF EXISTS users;


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
