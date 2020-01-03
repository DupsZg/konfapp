insert into Korisnik(username, password, enabled)
values('admin', '$2a$04$zDgJYv8Sz1JyIUcpfhXoM.Cv04NU3zcOcMLN076BOm3bl514zgCge', 1);
insert into Korisnik(username, password, enabled)
values('student', '$2a$04$2kVa4njnD7KC4Vt2cM2nMukjOX/fGJuJ8fGhxRz8KV34ujvMTQCV6', 1);
insert into KorisnikPrava(username, authority)
values('admin', 'ROLE_ADMIN');
insert into KorisnikPrava(username, authority)
values('admin', 'ROLE_USER');
insert into KorisnikPrava(username, authority)
values('student', 'ROLE_USER');