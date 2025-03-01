-- Create Place Table
CREATE TABLE place (
    id SERIAL PRIMARY KEY,
    alias VARCHAR(255),
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255)
);

-- Create Users Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    score INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255)
);

-- Create Clues Table
CREATE TABLE clues (
    id SERIAL PRIMARY KEY,
    clue VARCHAR(255) NOT NULL,
    place_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    CONSTRAINT fk_clues_place FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE
);

-- Create Facts Table
CREATE TABLE facts (
    id SERIAL PRIMARY KEY,
    fact VARCHAR(255) NOT NULL,
    place_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    CONSTRAINT fk_facts_place FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE
);

-- Create UserGuesses Table
CREATE TABLE user_guesses (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    place_id INTEGER NOT NULL, -- The correct place
    guessed_place_id INTEGER NOT NULL, -- The user's guess
    is_correct BOOLEAN NOT NULL,
    score INTEGER DEFAULT 0,
    guessed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(255),
    CONSTRAINT fk_userguesses_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_userguesses_place FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE,
    CONSTRAINT fk_userguesses_guessed_place FOREIGN KEY (guessed_place_id) REFERENCES place(id) ON DELETE CASCADE
);
