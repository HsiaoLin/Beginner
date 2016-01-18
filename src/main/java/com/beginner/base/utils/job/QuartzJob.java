package com.beginner.base.utils.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.beginner.base.utils.Logger;

public class QuartzJob implements Job {

	protected Logger log = Logger.getLogger(this.getClass());

	//private IUserService userService = (IUserService) SpringUtil.getBean("userService");

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "    定时任务成功！");
	}
}
