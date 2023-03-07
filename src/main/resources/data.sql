insert into users (id, email, password, first_name, last_name, phone, role) values (1, 'nick@mail.com', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', 'Nick', 'Green', '1-570-236-7032', 'ADMIN');
insert into users (id, email, password, first_name, last_name, phone, role) values (2, 'nora@mail.com', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', 'Nora', 'White', '1-570-236-7031', 'MANAGER');
insert into users (id, email, password, first_name, last_name, phone, role) values (3, 'mike@mail.com', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', 'Mike', 'Brown', '1-570-236-7033', 'USER');

insert into addresses (user_id, city, flat_number, house_number, house_type, street, zipcode) values (1, 'Vinnytsia', 10, 46, 'FLAT', 'Yunosti Ave', '21012');
insert into addresses (user_id, city, flat_number, house_number, house_type, street, zipcode) values (2, 'Vinnytsia', 0, 1, 'HOUSE', '600-years street', '21012');
insert into addresses (user_id, city, flat_number, house_number, house_type, street, zipcode) values (3, 'Vinnytsia', 10, 46, 'FLAT', 'Kosmonavtiv avenue', '21012');

insert into categories (id, name) values (1, 'electronics');
insert into categories (id, name) values (2, 'jewelery');
insert into categories (id, name) values (3, 'men''s clothing');
insert into categories (id, name) values (4, 'women''s clothing');

insert into products (id, description, image_url, price, title, category_id) values (1, 'some products description', 'https://localhost:8080/assets/electronics/device.jpg', 300.2, 'Device', 1);
insert into products (id, description, image_url, price, title, category_id) values (2, 'some products description', 'https://localhost:8080/assets/jewelery/brooch.jpg', 3000.12, 'Brooch', 2);
insert into products (id, description, image_url, price, title, category_id) values (3, 'some products description', 'https://localhost:8080/assets/menclothing/shirt.jpg', 30.0, 'Shirt', 3);
insert into products (id, description, image_url, price, title, category_id) values (4, 'some products description', 'https://localhost:8080/assets/womenclothing/skirt.jpg', 100.0, 'Skirt', 4);

insert into reviews (id, rating, text, author_id, product_id) values (1, 4.5, '', 1, 1);
insert into reviews (id, rating, text, author_id, product_id) values (2, 4.3, '', 3, 1);
insert into reviews (id, rating, text, author_id, product_id) values (3, 1.0, 'useless', 2, 1);
insert into reviews (id, rating, text, author_id, product_id) values (4, 5.0, 'very comfortable', 3, 3);
insert into reviews (id, rating, text, author_id, product_id) values (5, 4.5, 'looks fantastic', 2, 2);

insert into carts (id, created_at, status, total_price, user_id) VALUES (1, '2020-09-16 14:00:04.810221', 3, 3600.52, 1);
insert into carts (id, created_at, status, total_price, user_id) VALUES (2, '2021-10-16 14:00:04.810221', 0, 60.0, 2);
insert into carts (id, created_at, status, total_price, user_id) VALUES (3, '2022-01-06 14:00:04.810221', 4, 300.2, 2);
insert into carts (id, created_at, status, total_price, user_id) VALUES (4, '2022-09-16 09:00:04.810221', 5, 200.0, 3);
insert into carts (id, created_at, status, total_price, user_id) VALUES (5, '2023-01-16 14:00:04.810221', 2, 600.4, 2);

insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (1, 2, 1);
insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (2, 1, 1);
insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (2, 3, 2);
insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (1, 1, 3);
insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (2, 4, 4);
insert into carts_products_quantity (quantity, product_id, cart_id) VALUES (2, 1, 5);