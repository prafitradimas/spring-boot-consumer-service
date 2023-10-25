DROP TABLE IF EXISTS consumers;

CREATE TABLE IF NOT EXISTS consumers(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    city CHAR(100) NOT NULL,
    province CHAR(100) NOT NULL,
    register_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(8) NOT NULL DEFAULT 'active',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO consumers(name,address,city,province) VALUES('dimas', 'wirobrajan', 'yogyakarta', 'daerah istimewa yogyakarta');