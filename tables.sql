create table game
(
    id             int          not null auto_increment primary key,
    name           varchar(100) not null,
    publisher_name varchar(100),
    genre          varchar(100)
);

insert into game
values (null, 'CS:GO', 'FPS', 'FPS');

insert into game
values (null, 'Dota 2', 'Valve', 'MOBA');

select *
from game;

# drop table game;

create table team
(
    id      int          not null auto_increment primary key,
    game_id int          not null,
    name    varchar(100) not null,
    foreign key (game_id) references game (id)
);

select *
from team;

# drop table team;

create table team_member_type
(
    id   int          not null auto_increment primary key,
    name varchar(100) not null unique
);

insert into team_member_type
values (null, 'PLAYER');
insert into team_member_type
values (null, 'STAFF');

select *
from team_member_type;

# drop table team_member_type;

create table team_member
(
    id         int          not null auto_increment,
    type       varchar(100) not null,
    team_id    int,
    first_name varchar(100),
    last_name  varchar(100),
    nick_name  varchar(100),
    dob        date,
    primary key (id, type),
    foreign key (type) references team_member_type (name),
    foreign key (team_id) references team (id)
);

select *
from team_member;

# drop table team_member;

create table team_member_category
(
    id            int          not null auto_increment primary key,
    member_id     int          not null,
    category_name varchar(100) not null,
    foreign key (member_id) references team_member (id)
);

select *
from team_member_category;

# drop table team_member_category;
