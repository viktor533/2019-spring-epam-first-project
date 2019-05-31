CREATE SCHEMA IF NOT EXISTS pets_hotel (
    CREATE TABLE IF NOT EXISTS bill (
        id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
        booking_id        BIGINT      NOT NULL,
        usr_id            BIGINT      NOT NULL,
        status            VARCHAR(6)  NOT NULL,
        FOREIGN KEY (booking_id) references booking (id),
        FOREIGN KEY (usr_id) references usr (id)
    );

    CREATE TABLE IF NOT EXISTS booking (
        id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
        room_id           BIGINT      NOT NULL,
        start             DATE        NOT NULL,
        endd              DATE        NOT NULL,
        FOREIGN KEY (booking_id) references booking (id)
    );

    CREATE TABLE IF NOT EXISTS hotel (
        id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name              VARCHAR(50) NOT NULL,
        location          VARCHAR(50) NOT NULL,
        luxury            INT         NOT NULL
    );

    CREATE TABLE IF NOT EXISTS room (
        id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
        hotel_id          BIGINT      NOT NULL,
        num_of_guests     INTEGER     NOT NULL,
        price_per_night   INTEGER     NOT NULL,
        class             VARCHAR(8)  NOT NULL,
        FOREIGN KEY (hotel_id) references hotel (id)
    );

    CREATE TABLE IF NOT EXISTS usr (
        id                BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
        login             VARCHAR(50) NOT NULL,
        password          VARCHAR(50) NOT NULL,
        role              VARCHAR(5)  NOT NULL
    );
);