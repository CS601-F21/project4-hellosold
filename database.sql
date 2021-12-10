
CREATE TABLE users (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(6) NOT NULL,
    location VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE events (
    id INTEGER NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    description TEXT NOT NULL,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    event_place VARCHAR(30) NOT NULL,
    num_ticket INTEGER NOT NULL,
    image TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE purchases (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    num INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE transfers (
    id INTEGER NOT NULL AUTO_INCREMENT,
    from_user_id INTEGER NOT NULL,
    to_user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    num INTEGER NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (from_user_id) REFERENCES users (id),
    FOREIGN KEY (to_user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);