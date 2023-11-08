-- Sample data for Users
INSERT INTO Users (username, email)
VALUES
    ('user1', 'user1@example.com'),
    ('user2', 'user2@example.com');
   
-- Sample data for Security_Profile
INSERT INTO Security_Profile (username, password)
VALUES
    ('user1', '$2a$10$ehJpRaP4sj8g3iLBAZ897OboORdj6A.qXIzVwf3DYEwloqyc91Ue2'),
    ('user2', '$2a$10$ZyIxLnwYMVvOfAQduPRsB.xiaOmz4bPXnSLWJ/NFZmiAksP3f9VxW');

-- Sample data for Budgets with the updated structure
INSERT INTO Budgets (user_id, month, year)
VALUES
    (1, 'Jan', 2023),
    (1, 'Feb', 2023),
    (2, 'Jan', 2023);

-- Sample data for Categories
INSERT INTO Categories (budget_id, name, user_id)
VALUES
    (1, 'Groceries', 1),
    (1, 'Utilities', 1),
    (2, 'Entertainment', 2),
    (3, 'Transportation', 1);


-- Sample data for Line Items
INSERT INTO Line_Items (category_id, name, budgeted_amount, user_id)
VALUES
    (1, 'Food', 300.00, 1),
    (1, 'Toiletries', 50.00, 1),
    (2, 'Electricity', 100.00, 1),
    (2, 'Water', 50.00, 1),
    (3, 'Movie Night', 75.00, 2),
    (4, 'Gasoline', 50.00, 1);


INSERT INTO Transactions (line_item_id, transaction_date, amount, merchant, user_id)
VALUES
    (1, '2023-01-05', 75.00, 'Grocery Store 1', 1),
    (1, '2023-01-15', 50.00, 'Toiletries Shop', 1),
    (2, '2023-01-10', 100.00, 'Power Utility', 2),
    (3, '2023-01-20', 50.00, 'Water Company', 1),
   	(null,'2023-01-20', 50.00, 'Water Company', 1),
   	(null, '2023-01-15', 50.00, 'Toiletries Shop', 1);

--     Hibernate: 
--     alter table if exists budgets
--        drop constraint if exists FKln0tm5tgf3f9q3sp9sa5m8m7b
-- Hibernate:
--     alter table if exists categories
--        drop constraint if exists FKjfeip1fesndyacyyy077lr8od
-- Hibernate: 
--     alter table if exists categories
--        drop constraint if exists FKghuylkwuedgl2qahxjt8g41kb
-- Hibernate:
--     alter table if exists line_items
--        drop constraint if exists FKss1s6qorogrtoyq9rjjspfd5d
-- Hibernate:
--     alter table if exists line_items
--        drop constraint if exists FK1jlsvwsomhckf8rdasnonr4e7
-- Hibernate:
--     alter table if exists transactions
--        drop constraint if exists FK3jm99pcu8r0cxc1fsnd0uclos
-- Hibernate:
--     alter table if exists transactions
--        drop constraint if exists FKqwv7rmvc8va8rep7piikrojds
-- Hibernate:
--     drop table if exists budgets cascade
-- Hibernate:
--     drop table if exists categories cascade
-- Hibernate: 
--     drop table if exists line_items cascade
-- Hibernate:
--     drop table if exists security_profile cascade
-- Hibernate:
--     drop table if exists transactions cascade
-- Hibernate:
--     drop table if exists users cascade
-- Hibernate: 
--     create table budgets (
--         year integer not null,
--         budget_id bigserial not null,
--         user_id bigint,
--         month varchar(255) not null check (month in ('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec')),
--         primary key (budget_id)
--     )
-- Hibernate: 
--     create table categories (
--         budget_id bigint,
--         category_id bigserial not null,
--         user_id bigint,
--         name varchar(255),
--         primary key (category_id)
--     )
-- Hibernate:
--     create table line_items (
--         budgeted_amount float(53),
--         category_id bigint,
--         line_item_id bigserial not null,
--         user_id bigint,
--         name varchar(255),
--         primary key (line_item_id)
--     )
-- Hibernate: 
--     create table security_profile (
--         security_profile_id bigserial not null,
--         password varchar(255),
--         username varchar(255),
--         primary key (security_profile_id)
--     )
-- Hibernate: 
--     create table transactions (
--         amount float(53) not null,
--         transaction_date date not null,
--         line_item_id bigint,
--         transaction_id bigserial not null,
--         user_id bigint,
--         merchant varchar(30),
--         primary key (transaction_id)
--     )
-- Hibernate:
--     create table users (
--         user_id bigserial not null,
--         email varchar(255),
--         username varchar(255),
--         primary key (user_id)
--     )
-- Hibernate: 
--     alter table if exists budgets
--        add constraint FKln0tm5tgf3f9q3sp9sa5m8m7b
--        foreign key (user_id)
--        references users
-- Hibernate:
--     alter table if exists categories
--        add constraint FKjfeip1fesndyacyyy077lr8od
--        foreign key (budget_id)
--        references budgets
-- Hibernate:
--     alter table if exists categories
--        add constraint FKghuylkwuedgl2qahxjt8g41kb
--        foreign key (user_id)
--        references users
-- Hibernate:
--     alter table if exists line_items
--        add constraint FKss1s6qorogrtoyq9rjjspfd5d
--        foreign key (category_id)
--        references categories
-- Hibernate:
--     alter table if exists line_items
--        add constraint FK1jlsvwsomhckf8rdasnonr4e7
--        foreign key (user_id)
--        references users
-- Hibernate:
--     alter table if exists transactions
--        add constraint FK3jm99pcu8r0cxc1fsnd0uclos
--        foreign key (line_item_id)
--        references line_items
-- Hibernate: 
--     alter table if exists transactions
--        add constraint FKqwv7rmvc8va8rep7piikrojds
--        foreign key (user_id)
--        references users