package com.type.jobtogether;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.type.ItemProcessor.ArticleItemProcessor;
import com.type.dto.ArticleDto;
import com.type.listener.JobCompletionNotificationListener;

/**
 * @author qiguangjie
 * 将批处理放到一起
 *
 */
@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchJobArticleImport {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // tag::readerWriterprocesser[]
    // 读取文件处理
    @Bean
    public FlatFileItemReader<ArticleDto> reader() {
        return new FlatFileItemReaderBuilder<ArticleDto>()
                .name("articleItemReader")
                .resource(new ClassPathResource("file/db_article_file.csv"))
                .delimited()
                .names(new String[] { "articleName", "articleContent", "articleLangFlg" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ArticleDto>() {{
                        setTargetType(ArticleDto.class);
                    }})
                .build();
    }

    // instantce ArticleItemProcessor
    // 创建中间件对象
    @Bean
    public ArticleItemProcessor processor() {
        return new ArticleItemProcessor();
    }

    // 写入 文件处理
    @Bean
    public JdbcBatchItemWriter<ArticleDto> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ArticleDto>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("insert into db_article (article_name,article_content,article_lang_flg) values (:articleName,:articleContent,:articleLangFlg)")
                .dataSource(dataSource)
                .build();
    }

    // batch Job 配置
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    /**chunk()以<ArticleDto,PeArticleDtorson>为前缀，
     * 因为它是一个泛型方法。它表示处理的每个“块”的输入和输出类型，
     * 并与ItemReader对齐*/

    @Bean
    public  Step step1(JdbcBatchItemWriter<ArticleDto> writer) {
        return stepBuilderFactory.get("step1")
                .<ArticleDto,ArticleDto> chunk(3)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }






}
