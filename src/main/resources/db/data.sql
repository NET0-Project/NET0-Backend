-- users table
INSERT INTO users (NAME, KAKAO_EMAIL, KAKAOPK, KAKAO_PROFILE, PASSWORD, REFRESH_TOKEN, LAST_LOGIN_AT, SHOP_ID, USER_TYPE)
VALUES ('John Doe', 'john.doe@kakao.com', 1234567890, 'https://kakao.com/profile/johndoe', 'hashed_password_1', 'refresh_token_1', CURRENT_TIMESTAMP, null, 'ROLE_USER');

INSERT INTO users (NAME, KAKAO_EMAIL, KAKAOPK, KAKAO_PROFILE, PASSWORD, REFRESH_TOKEN, LAST_LOGIN_AT, SHOP_ID, USER_TYPE)
VALUES ('Jane Smith', 'jane.smith@kakao.com', 9876543210, 'https://kakao.com/profile/janesmith', 'hashed_password_2', 'refresh_token_2', CURRENT_TIMESTAMP, null, 'ROLE_USER');

INSERT INTO users (NAME, KAKAO_EMAIL, KAKAOPK, KAKAO_PROFILE, PASSWORD, REFRESH_TOKEN, LAST_LOGIN_AT, SHOP_ID, USER_TYPE)
VALUES ('Alice Johnson', 'alice.johnson@kakao.com', 1928374650, 'https://kakao.com/profile/alicejohnson', 'hashed_password_3', 'refresh_token_3', CURRENT_TIMESTAMP, null, 'ROLE_USER');

-- shops table
INSERT INTO shop (NAME, ADDRESS, CALL, SHOP_DESCRIPTION, LATITUDE, LONGITUDE, OPENING_HOUR, STATUS)
VALUES ('Shop 1', '123 Main St', '123-456-7890', 'A description for Shop 1', 37.450354677762, 126.65915614333, '9:00 AM - 9:00 PM', 'FOOD');

INSERT INTO shop (NAME, ADDRESS, CALL, SHOP_DESCRIPTION, LATITUDE, LONGITUDE, OPENING_HOUR, STATUS)
VALUES ('Shop 2', '124 Main St', '010-456-7890', 'A description for Shop 2', 37.450354677763, 126.65915614334, '9:00 AM - 9:00 PM', 'NOFOOD');

INSERT INTO shop (NAME, ADDRESS, CALL, SHOP_DESCRIPTION, LATITUDE, LONGITUDE, OPENING_HOUR, STATUS)
VALUES ('Shop 3', '125 Main St', '011-456-7890', 'A description for Shop 3', 37.450354677764, 126.65915614335, '9:00 AM - 10:00 AM', 'CLOSE');

-- item table
INSERT INTO item (SHOP_ID, ITEM_NAME, ORIGIN_PRICE, PRICE, DISCOUNT_RATE, STOCK, EXP, ITEM_DESCRIPTION)
VALUES (1,'소금빵',2000,1000,50,1,'2024-05-30','따끈따끈 소금빵');

INSERT INTO item (SHOP_ID, ITEM_NAME, ORIGIN_PRICE, PRICE, DISCOUNT_RATE, STOCK, EXP)
VALUES (1,'크림빵',1000,900,10,2,'2024-05-31');

INSERT INTO item (SHOP_ID, ITEM_NAME, ORIGIN_PRICE, PRICE, DISCOUNT_RATE, STOCK, EXP)
VALUES (2,'찰깨빵',1000,900,10,3,'2024-05-31');