insert into application_user(APPLICATION_USE_ROLE, IS_ACCOUNT_NON_EXPIRED, IS_ACCOUNT_NON_LOCKED,
                             IS_CREDENTIALS_NON_EXPIRED, IS_ENABLED, PASSWORD, USERNAME)
values ('USER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user1'),
       ('USER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user2'),
       ('USER', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'user3'),
       ('ADMIN', true, true, true, true, '$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC', 'admin');

insert into army(name)
values ('Beast Herds'),
       ('Daemon Legions'),
       ('Dread Elves'),
       ('Dwarven Holds'),
       ('Empire of Sonnstahl'),
       ('Highborn Elves'),
       ('Infernal Dwarves'),
       ('Kingdom Of Equitaine'),
       ('Ogre Khans'),
       ('Orcs and Goblins'),
       ('Saurian Ancients'),
       ('Sylvan Elves'),
       ('Vampire Covenant'),
       ('The Vermin Swarm'),
       ('Warriors Of The Dark Gods'),
       ('Undying Dynasties');


insert into unit_details(advance_rate, agility, armour, armour_penetration, attack_value, defensive_skill, discipline,
                         health_points, march_rate, offensive_skill, resilience, strength)
values (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5),
       (5, 2, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5);



insert into unit(name, BASE_COST, army_id, unit_details_id, ADDITIONAL_MODELS_COST, MAX_SIZE_OF_UNIT, MIN_SIZE_OF_UNIT)
values ('Vampire Count', 50, 13, 1, 10, 50, 1),
       ('Vampire Courtier', 50, 13, 2, 10, 50, 1),
       ('Necromancer', 50, 13, 3, 10, 50, 1),
       ('Barrow king', 50, 13, 4, 10, 50, 1),
       ('Fell Wraith', 50, 13, 5, 10, 50, 1),
       ('Banshee', 50, 13, 6, 10, 50, 1),
       ('Zombies', 50, 13, 7, 10, 50, 1),
       ('Skeletons', 50, 13, 8, 10, 50, 1),
       ('Ghouls', 50, 13, 9, 10, 50, 1),
       ('Bat Swarm', 50, 13, 10, 10, 50, 1),
       ('Dire Wolves', 50, 13, 11, 10, 50, 1),
       ('Great Bats', 50, 13, 12, 10, 50, 1),
       ('Ghasts', 50, 13, 13, 10, 50, 1),
       ('Barrow Guard', 50, 13, 14, 10, 50, 1),
       ('Barrow Knights', 50, 13, 15, 10, 50, 1),
       ('Cadavar Wagon', 50, 13, 16, 10, 50, 1),
       ('Dark Coach', 50, 13, 17, 10, 50, 1),
       ('Court of the Damned', 50, 13, 18, 10, 50, 1),
       ('Altar of Undeath', 50, 13, 19, 10, 50, 1),
       ('Phantom Hosts', 50, 13, 20, 10, 50, 1),
       ('Wraiths', 50, 13, 21, 10, 50, 1),
       ('Spectral Hunters', 50, 13, 22, 10, 50, 1),
       ('Vampire Knights', 50, 13, 23, 10, 50, 1),
       ('Vampire Spawn', 50, 13, 24, 10, 50, 1),
       ('Varkolak', 50, 13, 25, 10, 50, 1),
       ('Winged Reapers', 50, 13, 26, 10, 50, 1),
       ('Shrieking Horror', 50, 13, 27, 10, 50, 1);


insert into roster(name, army_id, APPLICATION_USER_ID)
values ('Vampirki', 13, 1),
       ('Vampiry', 13, 1),
       ('Vamps', 13, 2);

insert into ordered_unit(NUMBER_OF_MODELS, ROSTER_ID, UNIT_ID)
values (1, 1, 1),
       (1, 1, 2),
       (20, 1, 7),
       (20, 1, 8),
       (20, 1, 9),
       (1, 2, 1),
       (1, 2, 2),
       (20, 2, 7),
       (20, 2, 8),
       (20, 2, 9),
       (1, 3, 1),
       (1, 3, 2),
       (20, 3, 7),
       (20, 3, 8),
       (20, 3, 9);



insert into group_of_options(name)
values ('weapons'),
       ('magic'),
       ('blood ties');


insert into ooption(name, description)
values ('axe', '+1s'),
       ('sword', '+2s'),
       ('knife', '+3s'),
       ('apprentice', '1 lvl'),
       ('adept', '2 lvl'),
       ('master', '3 lvl'),
       ('Brotherhood of the Dragon Bloodline', '+2 Offensive Skill'),
       ('Von Karnstein Bloodline', 'reroll failed Vampiric rolls'),
       ('Lamia Bloodline', 'Lighting reflexes and -1 Attack Value'),
       ('Strigoi Bloodline', 'Fortitude (4+), Hatred, and +1 Health Point'),
       ('Nosferatu Bloodline', '-1 Attack Value, -2 Offensive Skill');



insert into ooption_groups_of_options(groups_of_options_id, options_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7),
       (3, 8),
       (3, 9),
       (3, 10),
       (3, 11);


insert into group_of_options_units(units_id, groups_of_options_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 2),
       (3, 3),
       (4, 1),
       (4, 2),
       (4, 3),
       (5, 1),
       (5, 2),
       (5, 3),
       (6, 1),
       (6, 2),
       (6, 3),
       (7, 1),
       (7, 2),
       (7, 3),
       (8, 1),
       (8, 2),
       (8, 3),
       (9, 1),
       (9, 2),
       (9, 3),
       (10, 1),
       (10, 2),
       (10, 3),
       (11, 1),
       (11, 2),
       (11, 3),
       (12, 1),
       (12, 2),
       (12, 3),
       (13, 1),
       (13, 2),
       (13, 3),
       (14, 1),
       (14, 2),
       (14, 3),
       (15, 1),
       (15, 2),
       (15, 3),
       (16, 1),
       (16, 2),
       (16, 3),
       (17, 1),
       (17, 2),
       (17, 3),
       (18, 1),
       (18, 2),
       (18, 3),
       (19, 1),
       (19, 2),
       (19, 3),
       (20, 1),
       (20, 2),
       (20, 3),
       (21, 1),
       (21, 2),
       (21, 3),
       (22, 1),
       (22, 2),
       (22, 3),
       (23, 1),
       (23, 2),
       (23, 3),
       (24, 1),
       (24, 2),
       (24, 3),
       (25, 1),
       (25, 2),
       (25, 3),
       (26, 1),
       (26, 2),
       (26, 3),
       (27, 1),
       (27, 2),
       (27, 3);

insert into ordered_unit_choosen_options(ordered_units_id, choosen_options_id)
values (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1);

insert into tournament(name, host_id, time)
values ('1 turniej', 1, current_time());
insert into tournament(name, host_id, time)
values ('2 turniej', 1, current_time());
insert into tournament(name, host_id, time)
values ('3 turniej', 2, current_time());
insert into tournament(name, host_id, time)
values ('4 turniej', 2, current_time());

insert into tournament_participants(tournaments_id, participants_id)
values (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 1),
       (3, 3),
       (4, 1),
       (4, 3);
