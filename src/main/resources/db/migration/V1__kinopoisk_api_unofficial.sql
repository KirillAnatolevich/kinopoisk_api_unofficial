create table test_user (
    id bigserial not null,
    filmId varchar(255),
    filmName varchar(255),
    year varchar(255),
    rating varchar(255),
    description varchar(10000),
    primary key (id)
)