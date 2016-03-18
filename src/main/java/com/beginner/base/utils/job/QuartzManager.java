/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beginner.base.utils.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
* <b>类名称：</b>QuartzManager<br/>
* <b>类描述：</b>Quartz定时任务工具类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@SuppressWarnings("rawtypes")
public class QuartzManager {

	private static SchedulerFactory ssf = new StdSchedulerFactory();

	private static final String JOB_NAME = "DEFAULT_JOB";

	private static final String JOB_GROUP_NAME = "DEFAULT_JOBGROUP";

	private static final String TRIGGER_NAME = "DEFAULT_TRIGGER";

	private static final String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP";

	/**
	* addJob(方法描述：添加定时任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* 使用默认的作业名称：	DEFAULT_JOB
	* 使用默认的作业组名称：	DEFAULT_JOBGROUP
	* 使用默认的触发器名称：	DEFAULT_TRIGGER
	* 使用默认的触发器组名称：DEFAULT_TRIGGERGROUP
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
	* @param time				时间表达式
	* void
	* @exception
	* @since  1.0.0
	*/
	@SuppressWarnings("unchecked")
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class cls, String cron) {
		try {
			//获取调度器
			Scheduler sched = ssf.getScheduler();
			//创建一项作业
			JobDetail job = JobBuilder.newJob(cls).withIdentity(jobName, jobGroupName).build();
			//创建一个触发器
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
			//告诉调度器使用该触发器来安排作业
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
	* modifyJobTime(方法描述：修改定时任务-先删除在新增) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName			作业名称
	* @param jobGroupName		作业组名称
	* @param triggerName		触发器名称
	* @param triggerGroupName	触发器组名称
	* @param cron				时间表达式
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void modifyJobTime(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron) {
		try {
			Scheduler sched = ssf.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
			if (trigger == null) {
				return;
			}
			String oldCron = trigger.getCronExpression();
			if (!oldCron.equalsIgnoreCase(cron)) {

				JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
				TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);

				JobDetail job = sched.getJobDetail(jobKey);
				Class jobClass = job.getJobClass();
				// 停止触发器
				sched.pauseTrigger(triggerKey);
				// 移除触发器
				sched.unscheduleJob(triggerKey);
				// 删除任务
				sched.deleteJob(jobKey);
				addJob(jobName, jobGroup, triggerName, triggerGroup, jobClass, cron);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	* modifyJobTime(方法描述：修改定时任务-只修改触发器和时间重启触发器) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param triggerName		触发器名称
	* @param triggerGroupName	触发器组名称
	* @param time
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void modifyJobTime(String triggerName, String triggerGroupName, String time) {
		try {
			Scheduler sched = ssf.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				ct.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
				// 重启触发器
				sched.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	* removeJob(方法描述：删除默认组任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName			作业名称
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void removeJob(String jobName) {
		try {
			Scheduler sched = ssf.getScheduler();
			// 停止触发器
			sched.pauseTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));
			// 移除触发器
			sched.unscheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));
			// 删除任务
			sched.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	* removeJob(方法描述：删除指定组任务) <br />
	* (方法适用条件描述： – 可选)
	* 
	* @param jobName			作业名称
	* @param jobGroupName		作业组名称
	* @param triggerName		触发器名称
	* @param triggerGroupName	触发器组名称
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = ssf.getScheduler();
			// 停止触发器
			sched.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroupName));
			// 移除触发器
			sched.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroupName));
			// 删除任务
			sched.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	* startJobs(方法描述：开始所有定时任务) <br />
	* (方法适用条件描述： – 可选)
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void startJobs() {
		try {
			Scheduler sched = ssf.getScheduler();
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
			Scheduler sched = ssf.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}