package com.hjh.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建 一个JobDetail实列，将该实列与HE来咯Job进行绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData("message","hello world")
                .usingJobData("FloatJobValue",3.14F)
                .build();
        //创建一个Trigger实列，定义该job立即执行，并且每隔秒钟重复执行一次，直到永远
        CronTrigger trigger =(CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("Mytrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("* * * * * ? *")
                ).build();
        //创建Schedualer实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        System.out.println("开始任务调度");
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
