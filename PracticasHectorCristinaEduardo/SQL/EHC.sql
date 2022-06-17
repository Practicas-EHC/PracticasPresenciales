-----------------------------
DROP TABLE ORDERS_LIST;      
DROP TABLE ORDERS;          
DROP TABLE CLOTHES;         
DROP TABLE STYLES;          
DROP TABLE USERS;           
-----------------------------


CREATE TABLE USERS(

USERNAME VARCHAR2(20) NOT NULL,
PASWORD VARCHAR2(20) NOT NULL,
ADRESS VARCHAR2(30) NOT NULL,
EMAIL VARCHAR2(50) NOT NULL,
TELEPHONE VARCHAR2(9) NOT NULL,

CONSTRAINT PK_USERNAME PRIMARY KEY(USERNAME)
);


CREATE TABLE STYLES(
STYLE_ID INTEGER,
STYLE_NAME VARCHAR2 (30) NOT NULL,

CONSTRAINT PK_STYLE_ID PRIMARY KEY (STYLE_ID)
);


CREATE TABLE CLOTHES(
CLOTHES_ID INTEGER,
STYLE_ID INTEGER,
COLLECTION VARCHAR2(20),
CLOTHES_NAME VARCHAR2(20) NOT NULL,
PRICE NUMBER (*,2) NOT NULL,

CONSTRAINT PK_CLOTHES_ID PRIMARY KEY(CLOTHES_ID),
CONSTRAINT FK_STYLE_ID FOREIGN KEY (STYLE_ID) REFERENCES STYLES
);

CREATE TABLE ORDERS(
ORDER_ID INTEGER,
USERNAME VARCHAR2(20),
ORDER_DATE DATE,

CONSTRAINT PK_ORDER_ID PRIMARY KEY (ORDER_ID),
CONSTRAINT FK_USERNAME FOREIGN KEY (USERNAME) REFERENCES USERS
);

CREATE TABLE ORDERS_LIST (
CLOTHES_ID INTEGER,
ORDER_ID INTEGER,
CLOTHE_SIZE VARCHAR2(5),
QUANTITY NUMBER (10),

CONSTRAINT FK_CLOTHES_ID FOREIGN KEY (CLOTHES_ID) REFERENCES CLOTHES,
CONSTRAINT FK_ORDER_ID FOREIGN KEY (ORDER_ID) REFERENCES ORDERS
);
--------------------------------------------------------------------------------


DROP SEQUENCE SECUENCIA_ORDERS;
DROP SEQUENCE SECUENCIA_CLOTHES;
DROP SEQUENCE SECUENCIA_STYLES;




CREATE SEQUENCE SECUENCIA_STYLES
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_STYLE
BEFORE INSERT ON STYLES
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_STYLES.NEXTVAL INTO :NEW.STYLE_ID FROM dual;
END;
/



--------------------------------




CREATE SEQUENCE SECUENCIA_CLOTHES
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_CLOTHES
BEFORE INSERT ON CLOTHES
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_CLOTHES.NEXTVAL INTO :NEW.CLOTHES_ID FROM dual;
END;
/


----------


CREATE SEQUENCE SECUENCIA_ORDERS
START WITH 1
INCREMENT BY 1
NOMINVALUE
NOMAXVALUE;

CREATE OR REPLACE TRIGGER TRIGGER_ID_ORDER
BEFORE INSERT ON ORDERS
FOR EACH ROW
BEGIN
    SELECT SECUENCIA_ORDERS.NEXTVAL INTO :NEW.ORDER_ID FROM dual;
END;
/

-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------

BEGIN

INSERT INTO USERS (USERNAME, PASWORD, ADRESS, EMAIL, TELEPHONE) VALUES ('EDUARDO', '1234', 'MADRID', 'eduardo@gmail.com', '222222222');
INSERT INTO USERS (USERNAME, PASWORD, ADRESS, EMAIL, TELEPHONE) VALUES ('CRISTINA', '1234', 'VALENCIA', 'cristina@gmail.com', '33333333');
INSERT INTO USERS (USERNAME, PASWORD, ADRESS, EMAIL, TELEPHONE) VALUES ('HECTOR', '1234', 'ZARAGOZA', 'hector@gmail.com', '111111111');

INSERT INTO STYLES (STYLE_ID, STYLE_NAME) VALUES ('1', 'MANGA CORTA');
INSERT INTO STYLES (STYLE_ID, STYLE_NAME) VALUES ('2', 'MANGA LARGA');
INSERT INTO STYLES (STYLE_ID, STYLE_NAME) VALUES ('3', 'BASICA');


INSERT INTO CLOTHES (STYLE_ID, COLLECTION, CLOTHES_NAME, PRICE) VALUES ('1','VERANO','CAMISETA','10');
INSERT INTO CLOTHES (STYLE_ID, COLLECTION, CLOTHES_NAME, PRICE) VALUES ('2','PRIMAVERA','CAMISETA','20');
INSERT INTO CLOTHES (STYLE_ID, COLLECTION, CLOTHES_NAME, PRICE) VALUES ('3','INVIERNO','PANTALON','55');
INSERT INTO CLOTHES (STYLE_ID, COLLECTION, CLOTHES_NAME, PRICE) VALUES ('3','VERANO','PANTALON','40');

INSERT INTO ORDERS (USERNAME, ORDER_DATE) VALUES ('CRISTINA','14/06/2022');
INSERT INTO ORDERS (USERNAME, ORDER_DATE) VALUES ('EDUARDO','20/07/2022');
INSERT INTO ORDERS (USERNAME, ORDER_DATE) VALUES ('HECTOR','15/08/2022');

COMMIT;
END;

SELECT * FROM USERS ;
SELECT * FROM STYLES;
SELECT * FROM CLOTHES;
SELECT * FROM ORDERS;




SELECT STYLE_NAME ,COLLECTION, CLOTHES_NAME 
FROM STYLES ST JOIN CLOTHES CL  
ON ST.STYLE_ID = CL.STYLE_ID  
WHERE INSTR (STYLE_NAME ,'BAS')!=0;



SELECT STYLE_NAME ,COLLECTION, CLOTHES_NAME 
FROM STYLES ST JOIN CLOTHES CL  ON ST.STYLE_ID = CL.STYLE_ID  
WHERE INSTR(STYLE_NAME,'ON') != 0 OR INSTR(COLLECTION, 'ON') != 0 OR INSTR(CLOTHES_NAME,'ON') != 0;



