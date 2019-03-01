--文章表Article表
--drop table db_article if exists;
create table db_article (
    article_name varchar (30) not null primary key,  --文章名
    article_content varchar (10000) not null,  --文章内容
    article_lang_flg varchar (2) not null,  --文章语言flg 0: 英语 1:中文 2:日文
    create_date date,
    update_date date
);
--insert into db_article (article_name,article_content,article_lang_flg) values('myName','My name is john . My Dream is become a best man.','0');
--insert into db_article (article_name,article_content,article_lang_flg) values('我的名字','我的名字叫做约翰。我想成为一个好人。.','1');
--insert into db_article (article_name,article_content,article_lang_flg) values('私の名前','私の名前はジョン。私いい男になりたいです。','2');