# This is not organised at all

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

delete
from game
where id = 2;

create table team
(
    id      int          not null auto_increment primary key,
    game_id int          not null,
    name    varchar(100) not null,
    foreign key (game_id) references game (id)
);

alter table team
    add constraint unique (game_id, name);


select *
from team;

delete
from team
where 1;

delete
from team_member_category
where 1;

delete
from game
where 1;
delete
from team
where 1;
ALTER TABLE game
    AUTO_INCREMENT = 1;
ALTER TABLE team
    AUTO_INCREMENT = 1;
insert into game
values (null, 'NONE', 'NONE', 'NONE');
insert into team
values (null, 1, 'NONE');

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
    id         int          not null auto_increment primary key,
    type       varchar(100) not null,
    team_id    int          not null default 1,
    first_name varchar(100),
    last_name  varchar(100),
    nick_name  varchar(100) not null,
    dob        date,
    foreign key (type) references team_member_type (name),
    foreign key (team_id) references team (id) ON DELETE CASCADE
        on update no action
);

insert into team_member
values (null, 'PLAYER', default, 'Test', 'Player', 'ttt', null);

select *
from tournament;

select *
from team;
delete
from team
where id = 14;

select *
from team_member;

select *
from tournament
where id = 34;

select *
from bracket;
delete
from bracket
where 1;
delete
from tournament_match
where 1;
drop table bracket;
drop table tournament_match;

select *
from bracket
where tournament_id = 34;

select *
from tournament_match
where bracket_id = 20;

delete
from tournament
where id = 34;

delete
from team
where id = 10;

delete
from team_member
where 1;

drop table team_member;
create table team_member_category
(
    id        int          not null auto_increment primary key,
    member_id int          not null,
    name      varchar(100) not null,
    foreign key (member_id) references team_member (id),
    unique (member_id, name)
);

drop table team_member_category;

select *
from team_member_category;

# Tournament stuff

drop table bracket;
drop table tournament;

create table tournament
(
    id         int          not null auto_increment primary key,
    game_id    int          not null,
    name       varchar(100) not null,
    status     varchar(100) not null,
    start_date date,
    end_date   date,
    location   varchar(100),
    foreign key (game_id) references game (id),
    unique (game_id, name)
);
alter table tournament
    add unique (game_id, name);

delete
from tournament
where 1;

select *
from tournament;

create table tournament_team
(
    id            int not null auto_increment primary key,
    tournament_id int not null,
    team_id       int not null,
    foreign key (tournament_id) references tournament (id) on delete cascade,
    unique (tournament_id, team_id)
);

delete
from tournament
where 1;
drop table tournament_team;

select t.id                     as tid,
       GROUP_CONCAT(tt.team_id) as teams
from tournament t
         left join tournament_team tt on t.id = tt.tournament_id
where t.id = 6
group by t.id;

SELECT t.id,
       t.game_id,
       t.name,
       t.status,
       t.start_date,
       t.end_date,
       t.location,
       ttt.teams
FROM tournament t
         join (select t.id                     as tid,
                      GROUP_CONCAT(tt.team_id) as teams
               from tournament t
                        left join tournament_team tt on t.id = tt.tournament_id
               group by t.id) ttt on t.id = ttt.tid
where t.id = 7
group by t.id
;

select *
from tournament;

insert into tournament_team
values (null, 7, 13);

delete
from tournament
where 1;

delete
from bracket
where 1;
delete
from tournament_match
where 1;

create table bracket
(
    id            int not null auto_increment primary key,
    tournament_id int not null,
    foreign key (tournament_id) references tournament (id) on delete cascade
);

select * from game;
update game
set genre = 'FPS'
where id = 2;

create table tournament_match
(
    id              int          not null auto_increment primary key,
    bracket_id      int          not null,
    bracket_phase   varchar(100) not null,
    red_team_id     int          not null,
    blue_team_id    int          not null,
    best_of         int          not null,
    red_team_score  int          not null,
    blue_team_score int          not null,
    status          varchar(100) not null,
    scheduled_date  date,
    default_win     bool         not null,
    foreign key (bracket_id) references bracket (id) on delete cascade,
    foreign key (red_team_id) references team (id),
    foreign key (blue_team_id) references team (id)
);

drop table tournament_match;
drop table tournament_match_game;

create table tournament_match_game
(
    id              int          not null auto_increment primary key,
    match_id        int          not null,
    red_team_score  int          not null,
    blue_team_score int          not null,
    status          varchar(100) not null,
    foreign key (match_id) references tournament_match (id)
);

select *
from bracket;
select *
from tournament_match;
