package com.beginner.base.utils.job;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
* <b>类名称：</b>ScheduleHandler<br/>
* <b>类描述：</b>ScheduleHandler工厂<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class ScheduleHandler {

	public ScheduleHandler() {
	}

	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	public Scheduler getScheduler() throws SchedulerException {
		return schedulerFactory.getScheduler();
	}
}
