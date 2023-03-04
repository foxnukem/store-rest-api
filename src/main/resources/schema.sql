create sequence if not exists users_seq start 100;
create sequence if not exists categories_seq start 100;
create sequence if not exists products_seq start 100;
create sequence if not exists reviews_seq start 100;
create sequence if not exists carts_seq start 100;

create table if not exists users
(
    id         bigint not null default nextval('users_seq'),
    email      text   not null,
    first_name text,
    last_name  text,
    password   text,
    phone      text,
    role       text,
    constraint users_pk primary key (id),
    constraint users_email_uq unique (email)
);

create table if not exists addresses
(
    user_id      bigint not null,
    city         text,
    street       text,
    house_number text,
    house_type   text,
    flat_number  bigint,
    zipcode      text,
    constraint addresses_users_id_fk foreign key (user_id) references users (id),
    constraint addresses_pk primary key (user_id)
);
create table if not exists categories
(
    id   bigint not null default nextval('categories_seq'),
    name text,
    constraint categories_pk primary key (id),
    constraint categories_name_uq unique (name)
);
create table if not exists products
(
    id          bigint not null default nextval('products_seq'),
    title       text   not null,
    image_url   text,
    price       numeric(38, 2),
    description text,
    category_id bigint not null,
    constraint products_pk primary key (id),
    constraint products_categories_id_fk foreign key (category_id) references categories (id)
);
create table if not exists reviews
(
    id         bigint not null default nextval('reviews_seq'),
    text       text,
    rating     double precision,
    author_id  bigint not null,
    product_id bigint not null,
    constraint reviews_pk primary key (id),
    constraint reviews_users_id_fk foreign key (author_id) references users (id),
    constraint reviews_products_id_fk foreign key (product_id) references products (id)
);
create table if not exists carts
(
    id          bigint         not null default nextval('carts_seq'),
    created_at  timestamp               default now(),
    status      text           not null,
    total_price numeric(38, 2) not null,
    user_id     bigint         not null,
    constraint carts_pk primary key (id),
    constraint carts_users_id_fk foreign key (user_id) references users (id)
);
create table if not exists carts_products_quantity
(
    cart_id bigint,
    product_id bigint,
    quantity bigint,
    constraint carts_products_quantity_pk primary key (cart_id, product_id),
    constraint carts_products_quantity_carts_id_fk foreign key (cart_id) references carts(id),
    constraint carts_products_quantity_products_id_fk foreign key (product_id) references products(id)
);
