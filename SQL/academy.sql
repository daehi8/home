create table academy (
    num varchar2(1000) primary key,
    name varchar2(100) not null,
    phone varchar2(100),
    address varchar2(100),
    birth date,
    state number default 0,
    reg date default sysdate
);

create table status(
    num number primary key,
    name varchar2(100)
);

alter table academy add(img varchar2(100));


create sequence state_seq nocache;
create sequence test_seq;

insert into state values(state_seq.nextval,'���');
insert into state values(state_seq.nextval,'����');
insert into state values(state_seq.nextval,'����');
insert into state values(state_seq.nextval,'�ߵ����');
insert into state values(state_seq.nextval,'Ż��');
select * from seq;

desc academy;
select * from academy;
commit;