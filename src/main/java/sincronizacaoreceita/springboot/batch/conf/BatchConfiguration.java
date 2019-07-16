/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sincronizacaoreceita.springboot.batch.conf;

import sincronizacaoreceita.springboot.batch.job.Processor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import sincronizacaoreceita.springboot.constant.ConstantFile;
import sincronizacaoreceita.springboot.batch.entity.LinhaIn;
import sincronizacaoreceita.springboot.batch.entity.LinhaOut;

/**
 * @author tiago.silva@compasso.com.br
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<LinhaIn> reader() {
        return new FlatFileItemReaderBuilder<LinhaIn>()
                .name("itemReader")
                .resource(new ClassPathResource(ConstantFile.IN))
                .delimited()
                .delimiter(";")
                .names(new String[]{"agencia", "conta", "saldo", "status"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<LinhaIn>() {
                    {
                        setTargetType(LinhaIn.class);
                    }
                }).build();
    }

    @Bean
    public Processor processor() {
        return new Processor();
    }

    @Bean
    public FlatFileItemWriter<LinhaOut> writer() {
        Resource outputResource = new FileSystemResource(ConstantFile.OUT);
        FlatFileItemWriter<LinhaOut> writer = new FlatFileItemWriter<>();
        writer.setResource(outputResource);
        writer.setAppendAllowed(true);
        writer.setLineAggregator(new DelimitedLineAggregator<LinhaOut>() {
            {
                setDelimiter(";");
                setFieldExtractor(new BeanWrapperFieldExtractor<LinhaOut>() {
                    {
                        setNames(new String[]{"agencia", "conta", "saldo", "status", "retorno"});
                    }
                });
            }
        });
        return writer;
    }

    @Bean
    public Step step(FlatFileItemWriter<LinhaOut> writer) {
        return stepBuilderFactory.get("step")
                .<LinhaIn, LinhaOut>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job itemJob(Step step) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }
}
