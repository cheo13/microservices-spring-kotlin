CREATE TABLE grades (
                        id SERIAL PRIMARY KEY,
                        subject VARCHAR(255) NOT NULL,
                        grade VARCHAR(20) NOT NULL,
                        semester VARCHAR(50) NOT NULL,
                        year INT NOT NULL,
                        createdAt TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);
