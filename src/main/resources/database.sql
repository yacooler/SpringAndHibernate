/*
DROP TABLE public.custom;
DROP TABLE public.price;
DROP TABLE public.customer;
DROP TABLE public.product;
*/
CREATE TABLE public.customer
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);


CREATE TABLE public.product
(
    id bigserial NOT NULL,
	title character varying(255) NOT NULL,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);


CREATE TABLE public.price
(
	id bigserial NOT NULL,
	product_id bigint NOT NULL,
	price_date date NOT NULL,
	price int NOT NULL,
	CONSTRAINT price_pkey PRIMARY KEY (id),
	CONSTRAINT fk_price_ref_product FOREIGN KEY (product_id)
		REFERENCES public.product (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID
);

CREATE TABLE public.custom
(
    customer_id bigint NOT NULL,
    price_id bigint NOT NULL,
    CONSTRAINT fk_custom_ref_customer FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID,
    CONSTRAINT fk_custom_ref_price FOREIGN KEY (price_id)
        REFERENCES public.price (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID
);

INSERT INTO public.customer(name)
VALUES
	('Иванов Иван')
	,('Александров Николай')
	,('Новикова Татьяна');


INSERT INTO public.product(title)
VALUES
	('Английский для начинающих'),
	('Oxford Pre-Intermediate student`s book'),
	('Oxford Pre-Intermediate student`s workbook'),
	('Oxford Advanced student`s book'),
	('Oxford Advanced workbook'),
	('Англо-русский словарь'),
	('Русско-английский словарь');

INSERT INTO public.price(product_id, price_date, price)
VALUES
	(1, '2019-01-01', 450), 	(1, '2020-01-01', 520), 	(1, '2020-06-01', 420),
	(2, '2019-01-01', 1970),	(2, '2020-01-01', 2130),
	(3, '2019-01-01', 640),		(3, '2020-01-01', 740),
	(4, '2019-01-01', 1720),	(4, '2020-01-01', 1860),
	(5, '2019-01-01', 580),		(5, '2020-01-01', 690),
	(6, '2019-01-01', 260),
	(7, '2019-01-01', 260);

INSERT INTO public.custom(customer_id, price_id)
VALUES
	(1, (SELECT id FROM price WHERE product_id = 1 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-09-01'))),
	(1, (SELECT id FROM price WHERE product_id = 2 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-03-27'))),
	(1, (SELECT id FROM price WHERE product_id = 3 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-03-27'))),
	(1, (SELECT id FROM price WHERE product_id = 6 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-03-27'))),

	(2, (SELECT id FROM price WHERE product_id = 6 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2019-06-01'))),
	(2, (SELECT id FROM price WHERE product_id = 7 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2019-06-01'))),

	(3, (SELECT id FROM price WHERE product_id = 4 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2019-06-01'))),
	(3, (SELECT id FROM price WHERE product_id = 5 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-06-01'))),
	(3, (SELECT id FROM price WHERE product_id = 7 AND price_date = (SELECT MAX(p.price_date) from public.price p where p.product_id = price.product_id and p.price_date <= '2020-03-27')))
;


	