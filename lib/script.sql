Hibernate:
create table film (
    id bigserial not null,
    description varchar(30000),
    film_id bigint,
    film_name varchar(255),
    rating float(53),
    year integer,
    primary key (id)
)