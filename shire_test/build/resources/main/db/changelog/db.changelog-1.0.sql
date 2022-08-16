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
CREATE TABLE IF NOT EXISTS user_chat
(
    id             BIGSERIAL PRIMARY KEY,
    first_user_id  BIGSERIAL REFERENCES app_user (id),
    second_user_id BIGSERIAL REFERENCES app_user (id),
    created_at     TIMESTAMP,
    modified_at    TIMESTAMP,
        UNIQUE (first_user_id, second_user_id)

);

-- changeset aplehanova:3
CREATE TABLE IF NOT EXISTS chat_message
(
    id           BIGSERIAL PRIMARY KEY,
    user_chat_id BIGSERIAL NOT NULL REFERENCES user_chat (id),
    sender_id    BIGSERIAL NOT NULL REFERENCES app_user (id),
    recipient_id BIGSERIAL NOT NULL REFERENCES app_user (id),
    message      TEXT,
    create_time  TIMESTAMP NOT NULL
);

