-- Insert data into Place with explicit IDs and metadata
INSERT INTO Place (id, alias, name, created_at, created_by, updated_at, updated_by) VALUES
(11, 'dst11', 'Paris', NOW(), 'admin', NOW(), 'admin'),
(12, 'dst12', 'Tokyo', NOW(), 'admin', NOW(), 'admin'),
(13, 'dst13', 'New York City', NOW(), 'admin', NOW(), 'admin'),
(14, 'dst14', 'London', NOW(), 'admin', NOW(), 'admin'),
(15, 'dst15', 'Rome', NOW(), 'admin', NOW(), 'admin'),
(16, 'dst16', 'Sydney', NOW(), 'admin', NOW(), 'admin'),
(17, 'dst17', 'Barcelona', NOW(), 'admin', NOW(), 'admin'),
(18, 'dst18', 'Cairo', NOW(), 'admin', NOW(), 'admin'),
(19, 'dst19', 'Rio de Janeiro', NOW(), 'admin', NOW(), 'admin'),
(20, 'dst20', 'Dubai', NOW(), 'admin', NOW(), 'admin');

-- Insert Clues with metadata
INSERT INTO Clues (id, clue, place_id, created_at, created_by, updated_at, updated_by) VALUES
(21, 'Home to a famous tower finished in 1889', 11, NOW(), 'admin', NOW(), 'admin'),
(22, 'Called the City of Light', 11, NOW(), 'admin', NOW(), 'admin'),
(23, 'Capital city with over 13 million residents', 12, NOW(), 'admin', NOW(), 'admin'),
(24, 'Known for high-tech, anime, and cherry blossoms', 12, NOW(), 'admin', NOW(), 'admin'),
(25, 'Known as the Big Apple', 13, NOW(), 'admin', NOW(), 'admin'),
(26, 'Home to Times Square', 13, NOW(), 'admin', NOW(), 'admin'),
(27, 'Home to Big Ben', 14, NOW(), 'admin', NOW(), 'admin'),
(28, 'Known for its double-decker buses', 14, NOW(), 'admin', NOW(), 'admin'),
(29, 'Home to the Colosseum', 15, NOW(), 'admin', NOW(), 'admin'),
(30, 'Known for its ancient history', 15, NOW(), 'admin', NOW(), 'admin'),
(31, 'Home to the Opera House', 16, NOW(), 'admin', NOW(), 'admin'),
(32, 'Known for its beautiful harbor', 16, NOW(), 'admin', NOW(), 'admin'),
(33, 'Known for its unique architecture by Gaudí', 17, NOW(), 'admin', NOW(), 'admin'),
(34, 'Home to La Sagrada Familia', 17, NOW(), 'admin', NOW(), 'admin'),
(35, 'Home to the Pyramids of Giza', 18, NOW(), 'admin', NOW(), 'admin'),
(36, 'Known for its ancient Egyptian history', 18, NOW(), 'admin', NOW(), 'admin'),
(37, 'Known for its Carnival', 19, NOW(), 'admin', NOW(), 'admin'),
(38, 'Home to Christ the Redeemer', 19, NOW(), 'admin', NOW(), 'admin'),
(39, 'Known for its modern architecture', 20, NOW(), 'admin', NOW(), 'admin'),
(40, 'Home to the Burj Khalifa', 20, NOW(), 'admin', NOW(), 'admin');

-- Insert Facts with metadata
INSERT INTO Facts (id, fact, place_id, created_at, created_by, updated_at, updated_by) VALUES
(21, 'The Louvre is the world''s largest art museum.', 11, NOW(), 'admin', NOW(), 'admin'),
(22, 'Famous for café culture and haute cuisine.', 11, NOW(), 'admin', NOW(), 'admin'),
(23, 'It''s the most populous metropolitan area in the world.', 12, NOW(), 'admin', NOW(), 'admin'),
(24, 'Tsukiji once was the world''s largest fish market.', 12, NOW(), 'admin', NOW(), 'admin'),
(25, 'The Statue of Liberty was a gift from France.', 13, NOW(), 'admin', NOW(), 'admin'),
(26, 'Central Park is larger than Monaco.', 13, NOW(), 'admin', NOW(), 'admin'),
(27, 'The London Underground is the world''s oldest underground railway network.', 14, NOW(), 'admin', NOW(), 'admin'),
(28, 'London has over 170 museums.', 14, NOW(), 'admin', NOW(), 'admin'),
(29, 'Rome has over 280 fountains and 900 churches.', 15, NOW(), 'admin', NOW(), 'admin'),
(30, 'The Trevi Fountain is a famous coin-tossing spot.', 15, NOW(), 'admin', NOW(), 'admin'),
(31, 'The Sydney Harbour Bridge is nicknamed ''The Coathanger''.', 16, NOW(), 'admin', NOW(), 'admin'),
(32, 'Sydney has over 100 beaches.', 16, NOW(), 'admin', NOW(), 'admin'),
(33, 'Barcelona has nine UNESCO World Heritage Sites.', 17, NOW(), 'admin', NOW(), 'admin'),
(34, 'The city has a famous football club.', 17, NOW(), 'admin', NOW(), 'admin'),
(35, 'The Nile River is the longest river in the world.', 18, NOW(), 'admin', NOW(), 'admin'),
(36, 'The Egyptian Museum holds the world''s largest collection of pharaonic antiquities.', 18, NOW(), 'admin', NOW(), 'admin'),
(37, 'The statue of Christ the Redeemer is one of the New Seven Wonders of the World.', 19, NOW(), 'admin', NOW(), 'admin'),
(38, 'Copacabana and Ipanema are famous beaches.', 19, NOW(), 'admin', NOW(), 'admin'),
(39, 'The Burj Khalifa is the tallest building in the world.', 20, NOW(), 'admin', NOW(), 'admin'),
(40, 'Dubai is a major shopping destination.', 20, NOW(), 'admin', NOW(), 'admin');
