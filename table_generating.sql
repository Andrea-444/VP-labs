
-- Table Definition: location
CREATE TABLE "public"."location" (
    "id" int8 NOT NULL,
    "name" varchar(255),
    "address" varchar(255),
    "capacity" varchar(255),
    "description" varchar(255),
    PRIMARY KEY ("id")
);

-- Table Definition: event
CREATE TABLE "public"."event" (
    "id" int8 NOT NULL,
    "name" varchar(255),
    "description" varchar(255),
    "popularity_score" float8,
    "location_id" int8,
    "tickets" int8,
    CONSTRAINT "fk_event_location" FOREIGN KEY ("location_id") REFERENCES "public"."location"("id"),
    PRIMARY KEY ("id")
);

CREATE TABLE "public"."app_users" (
    "username" varchar(255) NOT NULL,
   	 "name" varchar(255),
    PRIMARY KEY ("username")
);

-- Table Definition: event_booking
CREATE TABLE "public"."event_booking" (
    "id" int8 NOT NULL,
    "event_name" varchar(255),
    "attendee_name" varchar(255),
    "attendee_address" varchar(255),
    "number_of_tickets" int8,
    PRIMARY KEY ("id")
);

-- Insert Data into location
INSERT INTO location (name, address, capacity, description)
VALUES
    ('Skopje', 'Sk1', '100', 'Opera i balet'),
    ('Prilep', 'Pp1', '10', 'City Hall'),
    ('Minsk', 'Mn1', '200', 'Concert Hall'),
    ('Vienna', 'Vn1', '40', 'Arena'),
    ('Milan', 'Mi1', '60', 'Movie Hall');

-- Insert Data into event
INSERT INTO event (name, description, popularity_score, location_id, tickets)
VALUES
    ('Opera - Skopje', 'Carmen', 10, 1, 15),
    ('Concert - Minsk', 'Nkeeei', 9.5, 3, 20),
    ('Ballet - Skopje', 'Swan lake', 3, 1, 30),
    ('Festival - Berlin', 'Octoberfest', 4.5, 2, 25),
    ('Concert - Vienna', 'Harry Styles', 5.6, 4, 50),
    ('Concert - Skopje', 'Funk Shui', 6, 4, 40),
    ('Opera - Sofia', 'Tosca', 7.9, 1, 45),
    ('Movie premiere - Los Angeles', 'Avatar', 8.4, 5, 55),
    ('Movie premiere - Milan', 'Whiplash', 9, 5, 20),
    ('Festival - Prilep', 'Beerfest', 3.6, 2, 15);

-- Verify Tables
SELECT * FROM location;
SELECT * FROM event;
SELECT * FROM event_booking;
