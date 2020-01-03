package hr.tvz.dupan.job;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

	
	 @Bean
	 public JobDetail testJobDetail() {
		 
	 return JobBuilder.newJob(ObjaviJob.class)
			 	.withIdentity("objaviJob")
			 	.storeDurably()
			 	.build();
	 }
	 
	 @Bean
	 public Trigger testJobTrigger() {
		 SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				 .withIntervalInSeconds(10)
				 .repeatForever();
		 
		 return TriggerBuilder.newTrigger()
					 .forJob(testJobDetail())
					 .withIdentity("objaviTrigger")
					 .withSchedule(scheduleBuilder)
					 .build();
	 }

}
