CREATE TABLE file_metadata (
    id uuid PRIMARY KEY ,
    original_name varchar(255) NOT NULL ,
    stored_name varchar(255) NOT NULL UNIQUE ,
    type varchar(55) NOT NULL ,
    size_bytes bigint NOT NULL ,
    uploaded_at timestamptz DEFAULT now() NOT NULL
);

CREATE TABLE users (
    id uuid primary key ,
    name varchar(55) NOT NULL ,
    email varchar(128) not null ,
    registered_at timestamptz default now() not null
)