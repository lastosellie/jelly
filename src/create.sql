
SET SERVEROUT ON

DROP PROCEDURE CREATE_TABLE_IF_DOESNT_EXIT;

CREATE OR REPLACE PROCEDURE CREATE_TABLE_IF_DOESNT_EXIT(
  TABLENAME VARCHAR2,
  QUERY VARCHAR2,
  QYERYSEQ VARCHAR2
)
IS N NUMBER;
BEGIN
  SELECT COUNT(*) INTO N FROM USER_TABLES WHERE TABLE_NAME = UPPER(TABLENAME);
  IF (N = 0) THEN
    EXECUTE IMMEDIATE QUERY;
    IF (QYERYSEQ IS NOT NULL) THEN
    EXECUTE IMMEDIATE QYERYSEQ;
  	END IF;
  END IF;
END;

--테스트용
EXEC CREATE_TABLE_IF_DOESNT_EXIT('TTSS', 'CREATE TABLE TTSS (ID NUMBER)', 'CREATE SEQUENCE TODO_SEQ START WITH 1 MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1');
EXEC CREATE_TABLE_IF_DOESNT_EXIT('TTSS', 'CREATE TABLE TTSS (ID NUMBER)', '');

SELECT * FROM MEMBER;
SELECT * FROM TODO;
SELECT * FROM USER_SEQUENCES;

SELECT COUNT(*) INTO N FROM USER_TABLES WHERE TABLE_NAME = UPPER('TTSS');
INSERT INTO MEMBER(MEMBER_ID) VALUES(10);

DROP TABLE TODO;
DROP TABLE MEMBER;
DROP SEQUENCE TODO_SEQ;

-- 멤버 이름 : 10자
CREATE TABLE TODO (
	PROJECT_ID NUMBER(4),
	ASSIGNEE VARCHAR2(10),
	TODO_ID NUMBER(4),
	TITLE VARCHAR(200),
	START_DATE DATE,
	END_DATE DATE,
	PARENT_ID NUMBER,
	CONTENT VARCHAR(500),
	PROGRESS NUMBER
);

CREATE TABLE MEMBER(
	MEMBER_ID NUMBER,
	MEMBER_NAME VARCHAR2(10),
	PROJECT_ID NUMBER,
	GENDER NUMBER,
	IMAGE_URL VARCHAR2(260)
)

CREATE SEQUENCE TODO_SEQ
START WITH 1
MINVALUE 1
MAXVALUE 9999
INCREMENT BY 1;








