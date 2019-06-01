CREATE TABLE IF NOT EXISTS USER (
    id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login             VARCHAR(50) NOT NULL,
    password          VARCHAR(50) NOT NULL,
    role              VARCHAR(5)  NOT NULL
);

CREATE TABLE IF NOT EXISTS HOTEL (
    id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50) NOT NULL,
    location          VARCHAR(50) NOT NULL,
    luxury            INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS ROOM (
    id              BIGINT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
    hotel_id        BIGINT     NOT NULL,
    num_of_guests   INTEGER    NOT NULL,
    price_per_night INTEGER    NOT NULL,
    class           VARCHAR(8) NOT NULL,
    FOREIGN KEY (hotel_id) references HOTEL (id)
);

CREATE TABLE IF NOT EXISTS BILL (
    id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id           BIGINT      NOT NULL,
    status            VARCHAR(7)  NOT NULL,
    FOREIGN KEY (user_id) references USER (id)
);

CREATE TABLE IF NOT EXISTS BOOKING (
    id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    room_id           BIGINT      NOT NULL,
    bill_id           BIGINT      NOT NULL,
    start_date        DATE        NOT NULL,
    end_date          DATE        NOT NULL,
    FOREIGN KEY (bill_id) references BILL (id),
    FOREIGN KEY (room_id) references ROOM (id)
);