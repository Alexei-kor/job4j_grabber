package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AlertRabbit {

    private static final Logger LOG = LoggerFactory.getLogger(AlertRabbit.class.getName());

    public static void main(String[] args) {
        Properties prop = loadProperties();
        try (Connection cn = DriverManager.getConnection(
                prop.getProperty("url"),
                prop.getProperty("username"),
                prop.getProperty("password"))) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connect", cn);
            JobDetail job = JobBuilder.newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(prop.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception se) {
            LOG.error("error start sheduler", se);
        }
}

public static class Rabbit implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("Rabbit runs here ...");
        Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connect");
        try (Statement st = cn.createStatement()) {
            String sql = "CREATE SCHEMA if not exists myschema;"
                    + "create table if not exists myschema.rabbit("
                    + "created_date timestamp"
                    + ");";
            st.execute(sql);
            try (PreparedStatement prSt = cn.prepareStatement("insert into myschema.rabbit(created_date) values(?)")) {
                prSt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                prSt.execute();
            }
        } catch (SQLException e) {
            LOG.error("error executing request", e);
        }
    }
}

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = AlertRabbit.class
                .getClassLoader()
                .getResourceAsStream("rabbit.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            LOG.error("error load file 'rabbit.properties'", e);
        }
        return properties;
    }
}
