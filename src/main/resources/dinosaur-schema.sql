drop table if exists dinosaur CASCADE;

create table dinosaur 
(
	id integer PRIMARY KEY AUTO_INCREMENT, 
	genus varchar(255), 
	length integer not null, 
	carnivore boolean
);