CREATE TABLE items (
id INT AUTO_INCREMENT COMMENT '아이디' PRIMARY KEY,
NAME VARCHAR(50) NOT NULL COMMENT '상품 이름',
img_path VARCHAR(100) NOT NULL COMMENT '상품 사진 경로',
price INT NOT NULL COMMENT '상품 가격',
discount_per INT NOT NULL COMMENT '상품 할인율',
created DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '생성 일시'
) COMMENT '상품';



INSERT INTO items (NAME, img_path, price, discount_per)
VALUES ('Starry', '001.jpg', 10000000, 5)
, ('Seascape', '002.jpg', 20000000, 10)
, ('Arles', '003.jpg', 30000000, 15)
, ('Mountain', '004.jpg', 40000000, 20)
, ('Provence', '005.jpg', 50000000, 25)
, ('Houses', '006.jpg', 60000000, 30);

SELECT * FROM items;



CREATE TABLE members (
id INT AUTO_INCREMENT COMMENT '아이디' PRIMARY KEY,
NAME VARCHAR(50) NOT NULL COMMENT '회원명',
login_id VARCHAR(50)  NOT NULL COMMENT '로그인 아이디' UNIQUE , 
login_pw VARCHAR(50) NOT NULL COMMENT '로그인 패스워드',
created DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '생성 일시'
);


INSERT INTO members
SET NAME = '홍길동'
, login_id = 'hong'
, login_pw = '123';


SELECT * FROM members
WHERE login_id = 'hong'
AND login_pw = '1234'