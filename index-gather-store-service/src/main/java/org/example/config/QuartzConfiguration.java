package org.example.config;

import org.example.job.IndexDataSyncJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：cjy
 * 类名：QuartzConfiguration
 * 全路径类名：org.example.config.QuartzConfiguration
 * 父类或接口：
 * 描述：定时器配置，每隔4个小时一次。
 */
@Configuration
public class QuartzConfiguration {

    private static final int interval = 4;

    /**
     * 方法名：weatherDataSyncJobDetail
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link JobDetail }
     * 描述：找到定时任务类
     */
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob")
                .storeDurably().build();
    }

    /**
     * 方法名：weatherDataSyncTrigger
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link Trigger }
     * 描述：数据触发设定
     */
    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(interval)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(weatherDataSyncJobDetail())
                .withIdentity("indexDataSyncTrigger")
                .withSchedule(schedBuilder)
                .build();
    }
}
