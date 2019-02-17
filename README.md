背景:网络打字练习的文件导入处理

框架：基于springBoot batch
一.SpringBoot batch 的流程（https://spring.io/guides/gs/batch-processing/）

--1.src/main/resources/
----------------------ＳＱＬ文件作成
----------------------導入 文件作成
※Spring Boot runs schema-@@platform@@.sql automatically during startup. -all is the default for all platforms.

--2./src/main/java
----------------------①【Create a business class】：创建一个业务类:DTO
＞＞

----------------------②【Create an intermediate processor】：创建一个中间处理器
＞＞XXXItemProcessor implements ItemProcessor<T,T>

----------------------③【Put together a batch job】：将批处理作业放到一起
＞＞BatchConfiguration
＞＞＞＞FlatFileItemReader
＞＞＞＞PersonItemProcessor
＞＞＞＞JdbcBatchItemWriter
＞＞＞＞importUserJob
＞＞＞＞step1

----------------------④【JobCompletionNotificationListener 】：监听处理
＞＞This code listens for when a job is BatchStatus.COMPLETED, and then uses JdbcTemplate to inspect the results.
＞＞此代码侦听作业是BatchStatus.COMPLETED，然后使用JdbcTemplate检查结果。

----------------------⑤【Make the application executable】：使应用程序可执行
＞＞


二.多个Job执行


三.关系数据库myibatis配置