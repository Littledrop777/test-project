-- liquibase formatted sql

-- changeset aplehanova:1
CREATE TABLE IF NOT EXISTS app_user
(
    id          BIGSERIAL PRIMARY KEY,
    email       VARCHAR(64) NOT NULL UNIQUE,
    password    VARCHAR(64) NOT NULL,
    role        VARCHAR(32) NOT NULL,
    created_at  TIMESTAMP,
    modified_at TIMESTAMP
);

-- changeset aplehanova:2
CREATE TABLE IF NOT EXISTS chat
(
    id             BIGSERIAL PRIMARY KEY,
    first_user_id  BIGSERIAL REFERENCES app_user (id),
    second_user_id BIGSERIAL REFERENCES app_user (id),
    created_at     TIMESTAMP,
    modified_at    TIMESTAMP,
    UNIQUE (first_user_id, second_user_id)

);

-- changeset aplehanova:3
CREATE TABLE IF NOT EXISTS message
(
    id           BIGSERIAL PRIMARY KEY,
    chat_id      BIGSERIAL NOT NULL REFERENCES chat (id),
    sender_id    BIGSERIAL NOT NULL REFERENCES app_user (id),
    recipient_id BIGSERIAL NOT NULL REFERENCES app_user (id),
    content      TEXT,
    create_time  TIMESTAMP NOT NULL
);


