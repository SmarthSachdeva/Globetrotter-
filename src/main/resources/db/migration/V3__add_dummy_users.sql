-- Insert 15 dummy users into the Users table with metadata columns
INSERT INTO users (id, username, email, password, score, created_at, updated_at, created_by) VALUES
(1, 'Spiderman', 'password123', 12, NOW(), NOW(), 'admin'),
(2, 'TonyStark', 'iamironman', 27, NOW(), NOW(), 'admin'),
(3, 'DoctorStrange', 'doctorstrange@globetrotter.com', 'slingring', 81, NOW(), NOW(), 'admin'),
(4, 'Deadpool', 'deadpool@globetrotter.com', 'chimichangas', 45, NOW(), NOW(), 'admin'),
(5, 'Wolverine', 'wolverine@globetrotter.com', 'healingfactor', 27, NOW(), NOW(), 'admin'),
(6, 'ThorOdinson', 'thorodinson@globetrotter.com', 'mjolnir', 60, NOW(), NOW(), 'admin'),
(7, 'HulkSmash', 'hulksmash@globetrotter.com', 'angerissues', 0, NOW(), NOW(), 'admin'),
(8, 'CaptainAmerica', 'captainamerica@globetrotter.com', 'shieldbro', 90, NOW(), NOW(), 'admin'),
(9, 'BlackWidow', 'blackwidow@globetrotter.com', 'redledger', 36, NOW(), NOW(), 'admin'),
(10, 'LokiLaufeyson', 'lokilaufeyson@globetrotter.com', 'gloriouspurpose', 0, NOW(), NOW(), 'admin'),
(11, 'StarLord', 'starlord@globetrotter.com', 'mixtapevol1', 72, NOW(), NOW(), 'admin'),
(12, 'Groot', 'groot@globetrotter.com', 'iamgroot', 18, NOW(), NOW(), 'admin'),
(13, 'RocketRaccoon', 'rocketraccoon@globetrotter.com', 'bigguns', 51, NOW(), NOW(), 'admin'),
(14, 'DoctorDoom', 'doctordoom@globetrotter.com', 'latveria', 0, NOW(), NOW(), 'admin'),
(15, 'SilverSurfer', 'silversurfer@globetrotter.com', 'cosmicpower', 12, NOW(), NOW(), 'admin');