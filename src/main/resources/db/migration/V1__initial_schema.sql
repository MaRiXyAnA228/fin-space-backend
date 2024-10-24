CREATE TABLE t_user (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       is_active BOOLEAN NOT NULL
);

CREATE TABLE t_user_role
(
    user_id BIGINT NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    roles VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id, roles)
)
