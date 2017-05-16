
DROP TABLE IF EXISTS user;
CREATE TABLE user (
        login TEXT PRIMARY KEY,
        password TEXT NOT NULL,
        code TEXT,
	gender TEXT
);

