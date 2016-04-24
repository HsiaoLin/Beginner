/*
 * Copyright 2015-9999 the original author or authors.
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
package com.beginner.core.quartz;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetailImpl jobDetail = (JobDetailImpl) context.getJobDetail();
		logger.info("定时任务执行开始,JobKey:[" + jobDetail.getKey() + "],执行时间:" + DateTime.now().toString(DateTimeFormat.fullDateTime()));
		jobHandler(context.getMergedJobDataMap());
		logger.info("定时任务执行完成,JobKey:[" + jobDetail.getKey() + "],完成时间:" + DateTime.now().toString(DateTimeFormat.fullDateTime()));
	}

	public abstract void jobHandler(JobDataMap jobDataMap);
}
