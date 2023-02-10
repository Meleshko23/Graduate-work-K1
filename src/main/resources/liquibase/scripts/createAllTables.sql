-- liquebase formatted sql
-- changeset abdullinru:1

create table users(
        id	        Serial primary key,
        email	    varchar not null,
        first_name	varchar not null,
        last_name	varchar not null,
        phone	    varchar not null,
        reg_date	varchar not null,
        city	    varchar not null,
        role        varchar not null
);
create table ads(
        pk	    Serial primary key,
        price	integer not null,
        title	varchar not null,
        author	REFERENCES users (id)
);
create table comments(
        pk	        Serial primary key,
        createdAt	varchar not null,
        text	    varchar not null,
        author	    REFERENCES users (id)
);
create table images(
        id          Serial primary key,
        file_path    varchar not null,
        file_size    BIGINT not null,
        media_type   varchar not null,
        data         oid,
        ads_id       int REFERENCES ads (pk),
        user_id      int REFERENCES users (id)
);