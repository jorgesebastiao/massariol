insert into permissions(id,
creationDate,
lastModification,
permission)
values(1,
'2020-07-26 00:03:38',
'2020-07-26 00:03:38',
0);
insert into permissions(id,creationDate,lastModification,permission)
values(2,'2020-07-26 00:03:38','2020-07-26 00:03:38',1);

insert into users(id,
creationDate,
lastModification,
active,
email,
name,
password,
type)
values(1,
'2019-11-26 00:36:53',
'2019-11-26 00:36:53',
1,
'admin@massarioltreinamentos.com.br',
'Massariol Treinamentos',
'$2a$10$eJ1rJBNUJKGomoqdLvDrfugiuvJH824oYonZ2j98rtrgmZhAOBSb2',
0);

insert into userPermissions(userId,permissionId) values(1,1);