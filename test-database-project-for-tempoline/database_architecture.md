# Задание №1

Требуется спроектировать БД для сайта интернет-магазина.
У сайта есть категории, которые могут быть вложены друг в друга. В категориях находятся товары, при этом, один товар, может принадлежать различным категориям.
Также, на сайте есть личный кабинет покупателя. Покупатели могут регистрироваться на сайте. У покупателя могут быть заказы, в которых есть товары.
---
### Диаграмма архитектуры базы данных 

![Диаграмма архитектуры базы данных](https://i.ibb.co/hRdNkYw/db4.png)

---
### Таблица customer
Таблица для сохранения покупателей / пользователей интернет магазина.
Добавлен уникальный идентификатор и классические поля для пользователей.

```postgresql
create table "customer"
(
    id serial primary key,
    first_name text not null,
    last_name text not null,
    email text not null,
    password text not null
);
```
---
### Таблица order
Таблица для сохранения заказов. 
Имеет уникальный идентификатор, ссылку на пользователя, продукт, цену, количество и дату создания. 
```postgresql
create table "order"
(
    id serial primary key,
    customer_id serial,
    product_id  serial,
    product_count serial not null,
    created_at timestamp not null
);
```
---
### Таблица product
Таблица для сохранения продуктов / товаров / изделий. Имеет уникальный идентификатор, тип продукта / товара, наименование (не уникальное), цену.


```postgresql
create table "product"
(
    id serial primary key,
    product_type serial,
    name text not null,
    price numeric not null
);
```
---
### Таблица category
Таблица для хранения категорий и разделов в интернет магазине. 
Имеет только название и уникальные идентификатор. 
```postgresql
create table category
(
    id serial primary key,
    name text
);
```
---
### Таблица state_category
Вспомогательная таблица для таблицы "category". Хранит данные о местоположении в дереве. 
1) **id** - уникальный идентификатор
2) **parent_id** - предок
3) **child_id** - дочерний элемент
4) **level** - удаленность записей друг от друга в дереве

Благодаря такой таблице, мы всего в один запрос сможем найти все необходимые разделы и их подразделы. 
```postgresql
create table "state_category"
(
    id        serial
        primary key,
    parent_id serial,
    child_id  serial,
    level     serial
);
```
---
### Таблица category_to_product 
Вспомогательная таблица для таблиц **"category"** и **"product"**. Необходима для реализации отношения **многие-ко-многим**. Так как в интернет магазине может иметься несколько товаров в одной категории и несколько категорий у одного товара.

```postgresql
create table category_to_product
(
    id serial primary key,
    product_id integer,
    category_id integer
);
```
---
### Таблица product_type
Таблица для хранения типа товара / продукта. Имеет уникальный идентификатор и тип.
```postgresql
create table product_type
(
    id serial primary key,
    product_id serial,
    category_id serial
);
```
---
### Вывод
Спроектирован макет БД для интернет магазина. 