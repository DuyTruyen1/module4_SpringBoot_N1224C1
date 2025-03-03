create database student_management_n1224c1;

use student_management_n1224c1;

create table student
(
    id    int auto_increment primary key,
    name  varchar(50) not null,
    score double
);

select * from student;

insert into student(name, score) VALUE ('Truyền',8.8);
insert into student(name, score) VALUE ('Truyền 1',9.4);
insert into student(name, score) VALUE ('Truyền 2',7.2);

select id , name , score from student;

update student set name = ? , score = ? where id = ?;





