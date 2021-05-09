--delete from game;
--
select *
from game;
--
insert into game
values (null, 'FPS', 'CS:GO', 'Valve');
--
insert into game
values (null, 'MOBA', 'Dota 2', 'Valve');
--
insert into team
values (1, 'Astralis', 1);

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
insert into team
values (2, 'G2', 1);
insert into team
values (3, 'Faze', 1);
--
insert into tournament values (null, '2020-06-06', 'Online', 'Tournament test 1', '2020-05-05', 'CANCELED', 1);
insert into tournament values (null, '2021-06-06', 'Online', 'Tournament test 2', '2021-05-05', 'ONGOING', 1);
insert into tournament_team values (2, 1);
insert into tournament_team values (2, 2);
insert into tournament_team values (2, 3);
--
insert into bracket values(null, 'RO16', 1);
insert into bracket values(null, 'Quarter finals', 1);
insert into bracket values(null, 'Semi finals', 1);
insert into bracket values(null, 'Grand final', 1);