-- liquebase formatted sql
-- changeset abdullinru:1

create table users
(
    id         Serial primary key,
    email      varchar not null,
    first_name varchar not null,
    last_name  varchar not null,
    phone      varchar not null,
    reg_date   varchar not null,
    city       varchar,
    role       varchar not null
);
create table ads
(
    id          Serial primary key,
    price       integer not null,
    title       varchar not null,
    description varchar not null,
    author      int REFERENCES users (id) on delete cascade
);
create table comments
(
    id        Serial primary key,
    create_at date    not null,
    text      varchar not null,
    author    int REFERENCES users (id) on delete cascade,
    ads_id    int REFERENCES ads (id) on delete cascade
);
create table images
(
    id         Serial primary key,
    file_path  varchar not null,
    file_size  BIGINT  not null,
    media_type varchar not null,
    data       bytea,
    ads_id     int REFERENCES ads (id) on delete cascade,
    author     int REFERENCES users (id) on delete cascade
);