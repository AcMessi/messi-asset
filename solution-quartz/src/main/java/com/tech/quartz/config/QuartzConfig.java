package com.tech.quartz.config;

import com.tech.quartz.factory.QuartzJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @Classname QuartzConfig
 * @Description 任务调度配置
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private QuartzJobFactory jobFactory;

    /**
     * SchedulerFactoryBean这个类的真正作用提供了对org.quartz.Scheduler的创建与配置，并且会管理它的生命周期与Spring同步。
     * org.quartz.Scheduler: 调度器。所有的调度都是由它控制。
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        // 解决SpringBoot不能在Quartz中注入Bean的问题
        factory.setJobFactory(jobFactory);
//		factory.setStartupDelay(1);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    /*
     * 加载配置文件
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        //在quartz.properties中的属性被读取并注入后再初始化对象
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    /**
     * quartz初始化监听器
     * 这个监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行。
     * 有这个会导致报错：Active Scheduler of name 'MyClusterScheduler' already registered in Quartz SchedulerRepository. Cannot create a new Spring-managed Scheduler of the same name!
     * @return
     */
//	@Bean
//	public QuartzInitializerListener executorListener() {
//		return new QuartzInitializerListener();
//	}

    /**
     * 通过SchedulerFactoryBean获取Scheduler的实例
     */
    @Bean(name = "cmsScheduler")
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        return scheduler;
    }
}
