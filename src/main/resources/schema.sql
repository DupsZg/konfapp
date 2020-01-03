create table if not exists Predavac(
	id identity,
	ime varchar(20) not null,
	pozicija varchar(10) not null,
	datumUpisa timestamp not null
);

create table if not exists Predavanje(
	id identity,
	tema varchar(50) not null,
	kratkiSadrzaj varchar(250) not null,
	vrsta varchar(15) not null,
	objavljeno bit not null,
	datumUpisa timestamp not null
);

create table if not exists Predavanje_Predavac(
	predavanje bigint not null,
	predavac bigint not null
);
create table if not exists Korisnik(
	username varchar(20) not null,
	password varchar(100) not null,
	enabled bit not null
);
create table if not exists KorisnikPrava(
	username varchar(20) not null,
	authority varchar(20) not null
);