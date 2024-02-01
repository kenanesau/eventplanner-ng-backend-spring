--Places
INSERT INTO place (id, name, locked, locked_comment) VALUES (1, 'Schlossplatz', false, NULL);
INSERT INTO place (id, name, locked, locked_comment) VALUES (2, 'Scheune', false, NULL);
INSERT INTO place (id, name, locked, locked_comment) VALUES (3, 'Pferdestall', false, NULL);

--Customers
INSERT INTO customer (id, first_name, last_name, email) VALUES (1, 'Max', 'Mustermann', 'max.mustermann@web.de');
INSERT INTO customer (id, first_name, last_name, email) VALUES (2, 'Richard', 'Löwenherz', 'richard.loewenh@gmail.com');
INSERT INTO customer (id, first_name, last_name, email) VALUES (3, 'Richard', 'Müller', 'muellerchen@t-online.de');

--Events
INSERT INTO event_table (name, start_time, end_time, customer_id) VALUES ('Geburtstag', '2022-05-02 12.00.00', '2022-05-02 15.00.00', 1);
INSERT INTO event_table_places(event_id, places_id) VALUES (1, 1);
INSERT INTO event_table_places(event_id, places_id) VALUES (1, 2);
INSERT INTO event_table (name, start_time, end_time, customer_id) VALUES ('Grillen', '2022-05-03 22.00.00', '2022-05-04 12.30.00', 2);
INSERT INTO event_table_places(event_id, places_id) VALUES (2, 1);
INSERT INTO event_table (name, start_time, end_time, customer_id) VALUES ('Jugendweihe', '2022-05-05 22.00.00', '2022-05-07 11.00.00', 3);
INSERT INTO event_table_places(event_id, places_id) VALUES (3, 2);
