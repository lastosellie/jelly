DROP TABLE PROJECT_MEMBER;
DROP TABLE MEMBER;
DROP SEQUENCE TODO_SEQ;
DROP TABLE TODO;
DROP TABLE TB_TASK;

CREATE TABLE TB_TASK(
  PROJECT_ID NUMBER(4),
  TTITLE VARCHAR2(200),
  TSDATE DATE,
  TEDATE DATE, 
  TDURATION NUMBER GENERATED ALWAYS AS (TEDATE-TSDATE+1),
  CONSTRAINT PK_TB_TASK PRIMARY KEY (PROJECT_ID)
 );

CREATE TABLE TODO (
	PROJECT_ID NUMBER(4),
	ASSIGNEE VARCHAR2(10),
	TODO_ID NUMBER(4),
	TITLE VARCHAR2(200),
	START_DATE DATE,
	END_DATE DATE,
	PARENT_ID NUMBER,
	CONTENT VARCHAR2(500),
	PROGRESS NUMBER,
	CONSTRAINT PK_TODO PRIMARY KEY (TODO_ID),
	CONSTRAINT FK_TODO_1 FOREIGN KEY(PROJECT_ID) REFERENCES TB_TASK
);

CREATE SEQUENCE TODO_SEQ
START WITH 1
MINVALUE 1
MAXVALUE 9999
INCREMENT BY 1;

 create table MEMBER(
	MEMBR_NAME VARCHAR2(10),
	GENDER NUMBER(1),
	EMPNO VARCHAR2(20),
	PASSWORD VARCHAR2(20),
	PROJECT_ID NUMBER(8),
	DEPTNO NUMBER(8),
	CONSTRAINT PK_MEMBER PRIMARY KEY (EMPNO)
);

CREATE TABLE PROJECT_MEMBER(
	TB_TASK_PROJECT_ID NUMBER(4),
	MEMBER_EMPNO VARCHAR2(10),
	CONSTRAINT FK_PROJECT_MEMBER_01 FOREIGN KEY(TB_TASK_PROJECT_ID) REFERENCES TB_TASK,
	CONSTRAINT FK_PROJECT_MEMBER_02 FOREIGN KEY(MEMBER_EMPNO) REFERENCES MEMBER
);


--------------------------------------------------------------------------------

DROP TABLE TODO;
CREATE TABLE TODO (
	PROJECT_ID NUMBER(4),
	ASSIGNEE VARCHAR2(10),
	TODO_ID NUMBER(4),
	TITLE VARCHAR2(200),
	START_DATE DATE,
	END_DATE DATE,
	PARENT_ID NUMBER,
	CONTENT VARCHAR2(500),
	PROGRESS NUMBER
);

DROP SEQUENCE TODO_SEQ;
CREATE SEQUENCE TODO_SEQ
START WITH 1
MINVALUE 1
MAXVALUE 9999
INCREMENT BY 1;

 DROP TABLE MEMBER;
 create table MEMBER(
	MEMBR_NAME VARCHAR2(10),
	GENDER NUMBER(1),
	EMPNO VARCHAR2(20) PRIMARY KEY,
	PASSWORD VARCHAR2(20),
	PROJECT_ID NUMBER(8),
	DEPTNO NUMBER(8)
);

DROP TABLE TB_TASK;
CREATE TABLE TB_TASK(
  PROJECT_ID NUMBER(4),
  TTITLE VARCHAR2(200),
  TSDATE DATE,
  TEDATE DATE, 
  TDURATION NUMBER GENERATED ALWAYS AS (TEDATE-TSDATE+1)
 );
 
DROP TABLE PROJECT_MEMBER;
CREATE TABLE PROJECT_MEMBER(
	TB_TASK_PROJECT_ID NUMBER(4),
	MEMBER_EMPNO VARCHAR2(10)
);

--테스트용-----------------------------------------------------------------------
EXEC CREATE_TABLE_IF_DOESNT_EXIT('TTSS', 'CREATE TABLE TTSS (ID NUMBER)', 'CREATE SEQUENCE TODO_SEQ START WITH 1 MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1');
EXEC CREATE_TABLE_IF_DOESNT_EXIT('TTSS', 'CREATE TABLE TTSS (ID NUMBER)', '');

SELECT * FROM MEMBER;
SELECT * FROM TODO;
SELECT * FROM USER_SEQUENCES;
INSERT INTO MEMBER(MEMBER_ID) VALUES(10);

INSERT INTO MEMBER(MEMBR_NAME, GENDER, EMPNO, PASSWORD) VALUES('한누리', 1, 'ellie', 'ellie');
create table MEMBER(
	MEMBR_NAME VARCHAR2(10),
	GENDER NUMBER(1),
	EMPNO VARCHAR2(20),
	PASSWORD VARCHAR2(20),
	PROJECT_ID NUMBER(8),
	DEPTNO NUMBER(8)
)

CREATE TABLE MEMBER(
	MEMBER_ID NUMBER,
	MEMBER_NAME VARCHAR2(10),
	PROJECT_ID NUMBER,
	GENDER NUMBER,
	IMAGE_URL VARCHAR2(260)
)

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
