--DROP TABLE JOURNALJPAENTRY IF EXISTS;
--CREATE TABLE JOURNALJPAENTRY(id SERIAL, title VARCHAR(255),summary VARCHAR(255), created TIMESTAMP);
INSERT INTO JOURNALJPAENTRY(title,summary,created) VALUES('Get to know Spring Boot','Today I will
learn Spring Boot','2016-12-02 00:00:00.00');
INSERT INTO JOURNALJPAENTRY(title,summary,created) VALUES('Simple Spring Boot Project','I will do my
first Spring Boot project','2016-12-03 00:00:00.00');
INSERT INTO JOURNALJPAENTRY(title,summary,created) VALUES('Spring Boot Reading','Read more about
Spring Boot','2016-12-04 00:00:00.00');
INSERT INTO JOURNALJPAENTRY(title,summary,created) VALUES('Spring Boot in the Cloud','Learn Spring
Boot using Cloud Foundry','2016-12-05 00:00:00.00');

-- USERS IN JOURNAL
--INSERT INTO ACCOUNT(account_name , password) VALUES('springboot', 'isawesome');
--INSERT INTO ACCOUNT(account_name , password) VALUES('springsecurity', 'isawesometoo');