create table signup
(
	id varchar2(100) primary key,
	pw varchar2(100),
	name varchar2(100),
	gender varchar2(100),
	nickname varchar2(100),
	num varchar2(100),
	email varchar2(100),
	address varchar2(100),
	reg date default sysdate
);

insert into signup values('java','1111','ydh','��','java','01000000000','java@naver.com','����',sysdate);
insert into signup values('jsp','0000','abc','��','jsp','01011111111','jsp@google.com','�λ�',sysdate);
insert into signup values('oracle','2222','def','��','oracle','01022222222','oracle@naver.com','����',sysdate);
insert into signup values('naver','3333','ghi','��','naver','01033333333','naver@google.com','�λ�',sysdate);
insert into signup values('google','4444','jkl','��','google','01044444444','google@google.com','����',sysdate);
insert into signup values('eclipse','5555','nmo','��','ecll','01055555555','ecccc@naver.com','��⵵',sysdate);
insert into signup values('html','6666','htm','��','html','01066666666','httmm@google.com','�λ�',sysdate);
insert into signup values('css','7777','ssc','��','scs','01077777777','sccscsc@google.com','����',sysdate);
insert into signup values('avaj','8888','jav','��','avaj','010888888888','avjavj@google.com','����',sysdate);
insert into signup values('espil','9988','esp','��','lllll','01099999999','99999@google.com','�λ�',sysdate);
insert into signup values('psj','0101','psj','��','psj','01035353535','ppssjj@google.com','��⵵',sysdate);

select * from signup;
delete from signup where name = '123';
alter table signup add(img varchar2(100));
commit;
