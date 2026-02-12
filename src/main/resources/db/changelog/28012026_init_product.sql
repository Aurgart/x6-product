CREATE TABLE x6_product.product
(
    id serial NOT NULL,
    name varchar NOT NULL,
    price float NOT NULL,
    description varchar,
    type varchar,
    PRIMARY KEY (id)
);
