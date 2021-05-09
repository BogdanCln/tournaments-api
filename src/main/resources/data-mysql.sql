insert into game
values (null, 'FPS', 'CS:GO', 'Valve');
--
insert into game
values (null, 'MOBA', 'Dota 2', 'Valve');
--
insert into team
values (1, 'Astralis', 1);
insert into team
values (2, 'G2', 1);
insert into team
values (3, 'Faze', 1);
insert into team
values (4, 'NaVi', 1);
--
insert into team_member
values (null, '1993-03-03', 'Peter', 'Rasmussen', 'dupreeh', 'PLAYER', 1);
insert into team_member
values (null, '1993-06-03', 'Pete', 'Asmussen', 'xyp9x', 'PLAYER', 1);
insert into team_member
values (null, '1993-05-03', 'Peer', 'Smussen', 'gla1ve', 'PLAYER', 1);
insert into team_member
values (null, '1995-03-03', 'Pter', 'Russen', 'Magisk', 'PLAYER', 1);
insert into team_member
values (null, '1995-03-03', 'Peter', 'Ran', 'Bubzkji', 'PLAYER', 1);
--
insert into tournament
values (null, '2020-06-06', 'Online', 'Tournament test 1', '2020-05-05', 'CANCELED', 1);
insert into tournament
values (null, '2021-06-06', 'Online', 'Tournament test 2', '2021-05-05', 'ONGOING', 1);
insert into tournament_team
values (2, 1);
insert into tournament_team
values (2, 2);
insert into tournament_team
values (2, 3);
insert into tournament_team
values (2, 4);
--
insert into bracket
values (null, 'Semi finals', 2);
insert into bracket
values (null, 'Grand final', 2);
--
insert into tournament_match
values (null, 0, false, 2, '2021-05-07 15:00:00', 'FINISHED', 1, 1, 2);
insert into tournament_match
values (null, 2, false, 1, '2021-05-07 19:00:00', 'FINISHED', 3, 1, 4);

--

--create TABLE users (
--username VARCHAR(50) NOT NULL,
--password VARCHAR(100) NOT NULL,
--enabled TINYINT NOT NULL DEFAULT 1,
--PRIMARY KEY (username)
--);
--create TABLE authorities (
--username VARCHAR(50) NOT NULL,
--authority VARCHAR(50) NOT NULL,
--FOREIGN KEY (username) REFERENCES users(username)
--);
--create UNIQUE INDEX ix_auth_username
--on authorities (username,authority);
--insert into users (username, password, enabled)
--values ('guest',
--'$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
--1);
--insert into authorities (username, authority)
--values ('guest', 'ROLE_GUEST');
--insert into users (username, password, enabled)
--values ('admin',
--'$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a',
--1);
--insert into authorities (username, authority)
--values ('admin', 'ROLE_ADMIN');
