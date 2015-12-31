package com.beginner.base.utils.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
* <b>类名称：</b>QuartzManager<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年11月6日 下午7:32:31<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class QuartzManager {

	private static SchedulerFactory scheduler = new StdSchedulerFactory();

	private static final String JOB_NAME = "DEFAULT_JOB";

	private static final String JOB_GROUP_NAME = "DEFAULT_JOBGROUP";

	private static final String TRIGGER_NAME = "DEFAULT_TRIGGER";

	private static final String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP";

	/**
	* addJob(方法描述：添加定时任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* 使用默认的作业名称：		DEFAULT_JOB
	* 使用默认的作业组名称：	DEFAULT_JOBGROUP
	* 使用默认的触发器名称：	DEFAULT_TRIGGER
	* 使用默认的触发器组名称：	DEFAULT_TRIGGERGROUP
	* 
	* @param cls		调用定时任务的class
	* @param cron		定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void addJob(Class cls, String cron) {
		addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, cls, cron);
	}

	/**
	* addJob(方法描述：添加定时任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* 使用默认的作业组名称：	DEFAULT_JOBGROUP
	* 使用默认的触发器组名称：DEFAULT_TRIGGERGROUP
	* 
	* @param jobName	作业名称
	* @param triggerName触发器名称
	* @param cls		调用定时任务的class
	* @param cron		定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void addJob(String jobName, String triggerName, Class cls, String cron) {
		addJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, cls, cron);
	}

	/**
	* addJob(方法描述：添加一个定时任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName			作业名称
	* @param jobGroupName		作业组名称
	* @param triggerName		触发器名称
	* @param triggerGroupName	触发器组名称
	* @param cls				定时任务的class
	* @param cron				定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls, String cron) {
		try {

			//获取调度器
			Scheduler sched = scheduler.getScheduler();

			//创建一项作业
			JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).build();

			//创建一个触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

			//调度器使用该触发器来安排作业
			sched.scheduleJob(job, trigger);

			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* modifyJobTime(方法描述：) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param cron	定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void modifyJobTime(String cron) {
		modifyJobTime(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, cron);
	}

	/**
	* modifyJobTime(方法描述：) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName		作业名称
	* @param triggerName	触发器名称
	* @param cron			定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void modifyJobTime(String jobName, String triggerName, String cron) {
		modifyJobTime(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, cron);
	}

	/**
	* modifyJobTime(方法描述：) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName			作业名称
	* @param jobGroupName		作业组名称
	* @param triggerName		触发器名称
	* @param triggerGroupName	触发器组名称
	* @param cls				定时任务的class
	* @param cron				定时任务的时间通配符
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String cron) {
		try {
			Scheduler sched = scheduler.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldCron = trigger.getCronExpression();
			if (!oldCron.equalsIgnoreCase(cron)) {

				CronTrigger ct = trigger;

				//JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);

				TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

				// 重置触发器时间
				ct.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();

				// 重启触发器
				sched.resumeTrigger(triggerKey);

				//JobDetail job = sched.getJobDetail(jobKey);

				//Class jobClass = job.getJobClass();

				// 停止触发器
				//sched.pauseTrigger(triggerKey);
				// 移除触发器
				//sched.unscheduleJob(triggerKey);
				// 删除任务
				//sched.deleteJob(jobKey);

				//addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//	public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
	//		try {
	//			Scheduler sched = gSchedulerFactory.getScheduler();
	//			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
	//			if (trigger == null) {
	//				return;
	//			}
	//			String oldTime = trigger.getCronExpression();
	//			if (!oldTime.equalsIgnoreCase(time)) {
	//				CronTrigger ct = (CronTrigger) trigger;
	//				// 修改时间
	//				ct.setCronExpression(time);
	//				// 重启触发器
	//				sched.resumeTrigger(triggerName, triggerGroupName);
	//			}
	//		} catch (Exception e) {
	//			throw new RuntimeException(e);
	//		}
	//	}
	//	public static void removeJob(String jobName) {
	//		try {
	//			Scheduler sched = gSchedulerFactory.getScheduler();
	//			sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
	//			sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
	//			sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
	//		} catch (Exception e) {
	//			throw new RuntimeException(e);
	//		}
	//	}
	//	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
	//		try {
	//			Scheduler sched = gSchedulerFactory.getScheduler();
	//			sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
	//			sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
	//			sched.deleteJob(jobName, jobGroupName);// 删除任务
	//		} catch (Exception e) {
	//			throw new RuntimeException(e);
	//		}
	//	}
	/**
	* startJobs(方法描述：开始所有定时任务) <br />
	* (方法适用条件描述： – 可选)
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void startJobs() {
		try {
			Scheduler sched = scheduler.getScheduler();
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	* shutdownJobs(方法描述：停止所有定时任务) <br />
	* (方法适用条件描述： – 可选)
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void shutdownJobs() {
		try {
			Scheduler sched = scheduler.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}