-- Insert data into Place with explicit IDs and metadata
INSERT INTO Place (id, alias, name, created_at, created_by, updated_at, updated_by) VALUES
(1, 'dst1', 'Paris', NOW(), 'admin', NOW(), 'admin'),
(2, 'dst2', 'Tokyo', NOW(), 'admin', NOW(), 'admin'),
(3, 'dst3', 'New York City', NOW(), 'admin', NOW(), 'admin'),
(4, 'dst4', 'London', NOW(), 'admin', NOW(), 'admin'),
(5, 'dst5', 'Rome', NOW(), 'admin', NOW(), 'admin'),
(6, 'dst6', 'Sydney', NOW(), 'admin', NOW(), 'admin'),
(7, 'dst7', 'Barcelona', NOW(), 'admin', NOW(), 'admin'),
(8, 'dst8', 'Cairo', NOW(), 'admin', NOW(), 'admin'),
(9, 'dst9', 'Rio de Janeiro', NOW(), 'admin', NOW(), 'admin'),
(10, 'dst10', 'Dubai', NOW(), 'admin', NOW(), 'admin');

-- Insert Clues with metadata
INSERT INTO Clues (id, clue, place_id, created_at, created_by, updated_at, updated_by) VALUES
(1, 'Home to a famous tower finished in 1889', 1, NOW(), 'admin', NOW(), 'admin'),
(2, 'Called the City of Light', 1, NOW(), 'admin', NOW(), 'admin'),
(3, 'Capital city with over 13 million residents', 2, NOW(), 'admin', NOW(), 'admin'),
(4, 'Known for high-tech, anime, and cherry blossoms', 2, NOW(), 'admin', NOW(), 'admin'),
(5, 'Known as the Big Apple', 3, NOW(), 'admin', NOW(), 'admin'),
(6, 'Home to Times Square', 3, NOW(), 'admin', NOW(), 'admin'),
(7, 'Home to Big Ben', 4, NOW(), 'admin', NOW(), 'admin'),
(8, 'Known for its double-decker buses', 4, NOW(), 'admin', NOW(), 'admin'),
(9, 'Home to the Colosseum', 5, NOW(), 'admin', NOW(), 'admin'),
(10, 'Known for its ancient history', 5, NOW(), 'admin', NOW(), 'admin'),
(11, 'Home to the Opera House', 6, NOW(), 'admin', NOW(), 'admin'),
(12, 'Known for its beautiful harbor', 6, NOW(), 'admin', NOW(), 'admin'),
(13, 'Known for its unique architecture by Gaudí', 7, NOW(), 'admin', NOW(), 'admin'),
(14, 'Home to La Sagrada Familia', 7, NOW(), 'admin', NOW(), 'admin'),
(15, 'Home to the Pyramids of Giza', 8, NOW(), 'admin', NOW(), 'admin'),
(16, 'Known for its ancient Egyptian history', 8, NOW(), 'admin', NOW(), 'admin'),
(17, 'Known for its Carnival', 9, NOW(), 'admin', NOW(), 'admin'),
(18, 'Home to Christ the Redeemer', 9, NOW(), 'admin', NOW(), 'admin'),
(19, 'Known for its modern architecture', 10, NOW(), 'admin', NOW(), 'admin'),
(20, 'Home to the Burj Khalifa', 10, NOW(), 'admin', NOW(), 'admin');

-- Insert Facts with metadata
INSERT INTO Facts (id, fact, place_id, created_at, created_by, updated_at, updated_by) VALUES
(1, 'The Louvre is the world''s largest art museum.', 1, NOW(), 'admin', NOW(), 'admin'),
(2, 'Famous for café culture and haute cuisine.', 1, NOW(), 'admin', NOW(), 'admin'),
(3, 'It''s the most populous metropolitan area in the world.', 2, NOW(), 'admin', NOW(), 'admin'),
(4, 'Tsukiji once was the world''s largest fish market.', 2, NOW(), 'admin', NOW(), 'admin'),
(5, 'The Statue of Liberty was a gift from France.', 3, NOW(), 'admin', NOW(), 'admin'),
(6, 'Central Park is larger than Monaco.', 3, NOW(), 'admin', NOW(), 'admin'),
(7, 'The London Underground is the world''s oldest underground railway network.', 4, NOW(), 'admin', NOW(), 'admin'),
(8, 'London has over 170 museums.', 4, NOW(), 'admin', NOW(), 'admin'),
(9, 'Rome has over 280 fountains and 900 churches.', 5, NOW(), 'admin', NOW(), 'admin'),
(10, 'The Trevi Fountain is a famous coin-tossing spot.', 5, NOW(), 'admin', NOW(), 'admin'),
(11, 'The Sydney Harbour Bridge is nicknamed ''The Coathanger''.', 6, NOW(), 'admin', NOW(), 'admin'),
(12, 'Sydney has over 100 beaches.', 6, NOW(), 'admin', NOW(), 'admin'),
(13, 'Barcelona has nine UNESCO World Heritage Sites.', 7, NOW(), 'admin', NOW(), 'admin'),
(14, 'The city has a famous football club.', 7, NOW(), 'admin', NOW(), 'admin'),
(15, 'The Nile River is the longest river in the world.', 8, NOW(), 'admin', NOW(), 'admin'),
(16, 'The Egyptian Museum holds the world''s largest collection of pharaonic antiquities.', 8, NOW(), 'admin', NOW(), 'admin'),
(17, 'The statue of Christ the Redeemer is one of the New Seven Wonders of the World.', 9, NOW(), 'admin', NOW(), 'admin'),
(18, 'Copacabana and Ipanema are famous beaches.', 9, NOW(), 'admin', NOW(), 'admin'),
(19, 'The Burj Khalifa is the tallest building in the world.', 10, NOW(), 'admin', NOW(), 'admin'),
(20, 'Dubai is a major shopping destination.', 10, NOW(), 'admin', NOW(), 'admin');
