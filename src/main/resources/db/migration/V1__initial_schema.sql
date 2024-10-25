CREATE TABLE t_client (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(100) NOT NULL,
                          last_name VARCHAR(100) NOT NULL
);

CREATE TABLE t_user (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       is_active BOOLEAN NOT NULL,
                       client_id BIGINT UNIQUE REFERENCES t_client(id) ON DELETE CASCADE

);

CREATE TABLE t_user_role (
    user_id BIGINT NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    roles VARCHAR(100) NOT NULL,
    PRIMARY KEY (user_id, roles)
);

CREATE TABLE t_bank (
                        id BIGSERIAL PRIMARY KEY,
                        bank_name VARCHAR(100) NOT NULL
);

CREATE TABLE t_card (
                        id BIGSERIAL PRIMARY KEY,
                        card_number BIGINT UNIQUE NOT NULL,
                        expiration_date DATE NOT NULL,
                        client_id BIGINT NOT NULL REFERENCES t_client(id) ON DELETE CASCADE,
                        bank_id BIGINT NOT NULL REFERENCES t_bank(id) ON DELETE CASCADE
);

CREATE TABLE t_place (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

CREATE TABLE t_transaction (
                               id BIGSERIAL PRIMARY KEY,
                               amount DECIMAL(15, 2) NOT NULL,
                               category VARCHAR(50) NOT NULL,
                               transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               place_id BIGINT NOT NULL REFERENCES t_place(id) ON DELETE CASCADE,
                               card_id BIGINT NOT NULL REFERENCES t_card(id) ON DELETE CASCADE
);

CREATE TABLE t_bank_clients (
                                bank_id BIGINT NOT NULL REFERENCES t_bank(id) ON DELETE CASCADE,
                                clients_id BIGINT NOT NULL REFERENCES t_client(id) ON DELETE CASCADE,
                                PRIMARY KEY (bank_id, clients_id)
);

CREATE TABLE t_child_client (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(100) NOT NULL,
                                current_client_id BIGINT REFERENCES t_client(id) ON DELETE CASCADE,
                                client_id BIGINT NOT NULL REFERENCES t_client(id) ON DELETE CASCADE
);

