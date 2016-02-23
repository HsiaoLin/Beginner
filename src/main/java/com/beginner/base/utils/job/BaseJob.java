package com.beginner.base.utils.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beginner.base.utils.DateUtil;

/**
* <b>类名称：</b>BaseJob<br/>
* <b>类描述：</b>定时任务基类<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public abstract class BaseJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(BaseJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		JobDetailImpl jobDetail = (JobDetailImpl) context.getJobDetail();
		logger.info("定时任务执行开始,JobKey:[" + jobDetail.getKey() + "],执行时间:" + DateUtil.getTimeFormatDateString(context.getFireTime()));
		jobHandler(context.getMergedJobDataMap());
		logger.info("定时任务执行完成,JobKey:[" + jobDetail.getKey() + "]");
	}

	public abstract void jobHandler(JobDataMap jobDataMap);
}
