CREATE TABLE EMP
       (EMPNO NUMBER(4) NOT NULL,
        ENAME VARCHAR2(10),
        JOB VARCHAR2(9),
        MGR NUMBER(4),
        HIREDATE DATE,
        SAL NUMBER(7, 2),
        COMM NUMBER(7, 2),
        DEPTNO NUMBER(2));

INSERT INTO EMP VALUES(7369, 'SMITH',  'CLERK',     7902,
        TO_DATE('17-12-1980', 'DD-MM-YYYY'),  800, NULL, 20);

INSERT INTO EMP VALUES(7499, 'ALLEN',  'SALESMAN',  7698,
        TO_DATE('20-02-1981', 'DD-MM-YYYY'), 1600,  300, 30);

INSERT INTO EMP VALUES(7521, 'WARD',   'SALESMAN',  7698,
        TO_DATE('22-02-1981', 'DD-MM-YYYY'), 1250,  500, 30);

INSERT INTO EMP VALUES(7566, 'JONES',  'MANAGER',   7839,
        TO_DATE('02-04-1981', 'DD-MM-YYYY'),  2975, NULL, 20);

INSERT INTO EMP VALUES(7654, 'MARTIN', 'SALESMAN',  7698,
        TO_DATE('28-09-1981', 'DD-MM-YYYY'), 1250, 1400, 30);

INSERT INTO EMP VALUES(7698, 'BLAKE',  'MANAGER',   7839,
        TO_DATE('01-05-1981', 'DD-MM-YYYY'),  2850, NULL, 30);

INSERT INTO EMP VALUES(7782, 'CLARK',  'MANAGER',   7839,
        TO_DATE('09-06-1981', 'DD-MM-YYYY'),  2450, NULL, 10);

INSERT INTO EMP VALUES(7788, 'SCOTT',  'ANALYST',   7566,
        TO_DATE('09-12-1982', 'DD-MM-YYYY'), 3000, NULL, 20);

INSERT INTO EMP VALUES(7839, 'KING',   'PRESIDENT', NULL,
        TO_DATE('17-11-1981', 'DD-MM-YYYY'), 5000, NULL, 10);

INSERT INTO EMP VALUES(7844, 'TURNER', 'SALESMAN',  7698,
        TO_DATE('08-09-1981', 'DD-MM-YYYY'),  1500, NULL, 30);

INSERT INTO EMP VALUES(7876, 'ADAMS',  'CLERK',     7788,
        TO_DATE('12-01-1983', 'DD-MM-YYYY'), 1100, NULL, 20);

INSERT INTO EMP VALUES(7900, 'JAMES',  'CLERK',     7698,
        TO_DATE('03-12-1981', 'DD-MM-YYYY'),   950, NULL, 30);

INSERT INTO EMP VALUES(7902, 'FORD',   'ANALYST',   7566,
        TO_DATE('03-12-1981', 'DD-MM-YYYY'),  3000, NULL, 20);

INSERT INTO EMP VALUES(7934, 'MILLER', 'CLERK',     7782,
        TO_DATE('23-01-1982', 'DD-MM-YYYY'), 1300, NULL, 10);



CREATE TABLE DEPT
       (DEPTNO NUMBER(2),
        DNAME VARCHAR2(14),
        LOC VARCHAR2(13) );

INSERT INTO DEPT VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO DEPT VALUES (20, 'RESEARCH',   'DALLAS');
INSERT INTO DEPT VALUES (30, 'SALES',      'CHICAGO');
INSERT INTO DEPT VALUES (40, 'OPERATIONS', 'BOSTON');



commit;

select empno, ename, sal from emp where deptno =10;
select * from emp where ename = 'ALLEN';
select ename, deptno, sal from emp where hiredate='1983/01/12';
select ename, sal, deptno from emp where sal >= 800;
select * from emp where ename >= 'L%';
select * from emp where hiredate < '1981/12/09';
select * from emp where not empno between 7654 and 7782;
select * from emp where hiredate not like '1981%'
select * from emp where ename like 'S___T';
select ename, sal, deptno from emp where deptno = 30 and sal >= 1500;
select * from emp where deptno=30 order by empno;
select * from emp order by deptno asc, sal desc;
select * from emp order by deptno desc, ename asc, sal desc;
select * from emp order by sal desc;
select * from emp where sal >=1500 and deptno = 30 and job = 'MANAGER';
select empno, ename, deptno from emp where ename like 'K%' or deptno = 30;