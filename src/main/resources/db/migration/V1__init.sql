create table application_user
(
    id                         integer AUTO_INCREMENT,
    application_use_role       varchar(255),
    is_account_non_expired     boolean not null,
    is_account_non_locked      boolean not null,
    is_credentials_non_expired boolean not null,
    is_enabled                 boolean not null,
    password                   varchar(255),
    username                   varchar(255),
    email                      varchar(255),
    primary key (id)
);

create table token
(
    id                  integer AUTO_INCREMENT,
    value               varchar(255),
    application_user_id integer,
    primary key (id)
);


create table army
(
    id   integer AUTO_INCREMENT,
    name varchar(255),
    primary key (id)
);

create table group_of_options
(
    id   integer AUTO_INCREMENT,
    name varchar(255),
    primary key (id)
);

create table ooption
(
    id          integer AUTO_INCREMENT,
    description varchar(255) not null,
    name        varchar(255) not null,
    primary key (id)
);

create table ordered_unit
(
    id               integer AUTO_INCREMENT,
    number_of_models integer not null,
    roster_id        integer,
    unit_id          integer,
    primary key (id)
);

create table ooption_groups_of_options
(
    options_id           integer not null,
    groups_of_options_id integer not null
);

create table roster
(
    id                  integer AUTO_INCREMENT,
    name                varchar(15),
    application_user_id integer,
    army_id             integer not null,
    primary key (id)
);

create table tournament
(
    id      integer AUTO_INCREMENT,
    name    varchar(255) not null,
    host_id integer      not null,
    time    datetime     not null,
    primary key (id)
);

create table unit
(
    id                     integer AUTO_INCREMENT,
    additional_models_cost integer not null check (additional_models_cost <= 1000 AND additional_models_cost >= 3),
    base_cost              integer not null check (base_cost <= 1000 AND base_cost >= 3),
    max_size_of_unit       integer not null check (max_size_of_unit <= 100 AND max_size_of_unit >= 1),
    min_size_of_unit       integer not null check (min_size_of_unit <= 100 AND min_size_of_unit >= 1),
    name                   varchar(255),
    army_id                integer,
    unit_details_id        integer,
    primary key (id)
);

create table group_of_options_units
(
    units_id             integer,
    groups_of_options_id integer

);

create table ordered_unit_choosen_options
(
    ordered_units_id   integer,
    choosen_options_id integer

);

create table tournament_participants
(
    tournaments_id  integer,
    participants_id integer

);


create table unit_details
(
    id                 integer AUTO_INCREMENT,
    advance_rate       integer not null check (advance_rate >= 1 AND advance_rate <= 10),
    agility            integer not null check (agility >= 0 AND agility <= 10),
    armour             integer not null check (armour <= 6 AND armour >= 0),
    armour_penetration integer not null check (armour_penetration >= 0 AND armour_penetration <= 10),
    attack_value       integer not null check (attack_value >= 1 AND attack_value <= 10),
    defensive_skill    integer not null check (defensive_skill >= 1 AND defensive_skill <= 10),
    discipline         integer not null check (discipline >= 1 AND discipline <= 10),
    health_points      integer not null check (health_points >= 1 AND health_points <= 10),
    march_rate         integer not null check (march_rate >= 1 AND march_rate <= 10),
    offensive_skill    integer not null check (offensive_skill >= 1 AND offensive_skill <= 10),
    resilience         integer not null check (resilience >= 1 AND resilience <= 10),
    strength           integer not null check (strength >= 1 AND strength <= 10),
    primary key (id)
);


alter table ooption_groups_of_options
    add
        foreign key (groups_of_options_id)
            references group_of_options (id);

alter table ooption_groups_of_options
    add
        foreign key (options_id)
            references ooption (id);

alter table ordered_unit
    add
        foreign key (roster_id)
            references roster (id);

alter table ordered_unit
    add
        foreign key (unit_id)
            references unit (id);

alter table roster
    add
        foreign key (application_user_id)
            references application_user (id);

alter table roster
    add
        foreign key (army_id)
            references army (id);

alter table tournament
    add
        foreign key (host_id)
            references application_user (id);

alter table unit
    add
        foreign key (army_id)
            references army (id);


alter table unit
    add
        foreign key (unit_details_id)
            references unit_details (id);

alter table group_of_options_units
    add
        foreign key (groups_of_options_id)
            references group_of_options (id);

alter table group_of_options_units
    add
        foreign key (units_id)
            references unit (id);

alter table ordered_unit_choosen_options
    add
        foreign key (ordered_units_id)
            references ordered_unit (id);

alter table ordered_unit_choosen_options
    add
        foreign key (choosen_options_id)
            references ooption (id);


alter table tournament_participants
    add
        foreign key (participants_id)
            references application_user (id);

alter table tournament_participants
    add
        foreign key (tournaments_id)
            references tournament (id);

alter table token
    add
        foreign key (application_user_id)
            references application_user (id);