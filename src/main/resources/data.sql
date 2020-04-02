insert into APPLICATION_USER(APPLICATION_USE_ROLE,IS_ACCOUNT_NON_EXPIRED,IS_ACCOUNT_NON_LOCKED,IS_CREDENTIALS_NON_EXPIRED,IS_ENABLED,PASSWORD,USERNAME) values
('USER',true,true,true,true,'$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC','user1'),
('USER',true,true,true,true,'$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC','user2'),
('ADMIN',true,true,true,true,'$2a$10$Br0/W3sngs5hdRgkGpwzt.ZyOPIHXacdbNH6TM7Ga28CF7W5MD7RC','admin');

insert into army(name) values
('Beast Herds'),
('Daemon Legions' ),
('Dread Elves'),
('Dwarven Holds' ),
('Empire of Sonnstahl' ),
('Highborn Elves' ),
('Infernal Dwarves' ),
('Kingdom Of Equitaine' ),
('Ogre Khans' ),
('Orcs and Goblins' ),
('Saurian Ancients' ),
('Sylvan Elves' ),
('Vampire Covenant' ),
('The Vermin Swarm' ),
('Warriors Of The Dark Gods' );


insert into unit_details(advance_rate,agility,armour,armour_penetration,attack_value,defensive_skill,discipline,health_points,march_rate,offensive_skill,resilience,strength) values
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5),
(5,2,4,5,5,5,5,5,5,5,5,5);



insert into unit(name,BASE_COST,army_id,unit_details_id,ADDITIONAL_MODELS_COST, MAX_SIZE_OF_UNIT, MIN_SIZE_OF_UNIT) values
('Vampire Count',50,13,1,10,50,1),
('Vampire Courtier',50,13,2,10,50,1),
('Necromancer',50,13,3,10,50,1),
('Barrow king',50,13,4,10,50,1),
('Fell Wraith',50,13,5,10,50,1),
('Banshee',50,13,6,10,50,1),
('Zombies',50,13,7,10,50,1),
('Skeletons',50,13,8,10,50,1),
('Ghouls',50,13,9,10,50,1),
('Bat Swarm',50,13,10,10,50,1),
('Dire Wolves',50,13,11,10,50,1),
('Great Bats',50,13,12,10,50,1),
('Ghasts',50,13,13,10,50,1),
('Barrow Guard',50,13,14,10,50,1),
('Barrow Knights',50,13,15,10,50,1),
('Cadavar Wagon',50,13,16,10,50,1),
('Dark Coach',50,13,17,10,50,1),
('Court of the Damned',50,13,18,10,50,1),
('Altar of Undeath',50,13,19,10,50,1),
('Phantom Hosts',50,13,20,10,50,1),
('Wraiths',50,13,21,10,50,1),
('Spectral Hunters',50,13,22,10,50,1),
('Vampire Knights',50,13,23,10,50,1),
('Vampire Spawn',50,13,24,10,50,1),
('Varkolak',50,13,25,10,50,1),
('Winged Reapers',50,13,26,10,50,1),
('Shrieking Horror',50,13,27,10,50,1);


insert into roster(name,army_id,APPLICATION_USER_ID) values
('Vampirki',13,1),
('Vampiry',13,1),
('Vamps',13,2);

insert into ordered_unit(NUMBER_OF_MODELS,ROSTER_ID,UNIT_ID) values
(1,1,1),
(1,1,2),
(20,1,7),
(20,1,8),
(20,1,9),
(1,2,1),
(1,2,2),
(20,2,7),
(20,2,8),
(20,2,9),
(1,3,1),
(1,3,2),
(20,3,7),
(20,3,8),
(20,3,9);



