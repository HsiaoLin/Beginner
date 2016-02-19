package com.beginner.system.controller.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beginner.base.common.Const;
import com.beginner.base.controller.BaseController;
import com.beginner.base.plugin.page.PageData;
import com.beginner.base.utils.job.JobEntity;
import com.beginner.base.utils.job.QuartzManager;

/**
* <b>类名称：</b>JobController<br/>
* <b>类描述：</b>定时任务监控<br/>
* <b>创建人：</b>Hsiao Lin Studio<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年05月21日 下午6:18:18<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
@Controller
@SuppressWarnings("unchecked")
@RequestMapping(value = "/job")
public class JobController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "quartzScheduler")
	private Scheduler quartzScheduler;

	//去列表定时任务页面
	@RequestMapping(value = "/goList")
	public ModelAndView goList() throws Exception {
		logger.info("去定时任务列表页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(pd));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//去编辑定时任务页面
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit() throws Exception {
		logger.info(Const.START_CN);
		logger.info("去编辑定时任务页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		JobDetail jd = quartzScheduler.getJobDetail(jobKey);
		List<CronTrigger> triggers = (List<CronTrigger>) quartzScheduler.getTriggersOfJob(jobKey);
		CronTrigger trigger = triggers.get(0);
		TriggerKey triggerKey = trigger.getKey();
		String cron = trigger.getCronExpression();
		pd.put("jobName", jobKey.getName());
		pd.put("jobGroup", jobKey.getGroup());
		pd.put("triggerName", triggerKey.getName());
		pd.put("triggerGroupName", triggerKey.getGroup());
		pd.put("cron", cron);
		pd.put("clazz", jd.getJobClass().getCanonicalName());
		mv.setViewName("system/tools/edit_job");
		mv.addObject("pd", pd);
		mv.addObject("msg", "edit");
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//去新增定时任务页面
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() throws Exception {
		logger.info(Const.START_CN);
		logger.info("去新增定时任务页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/tools/edit_job");
		mv.addObject("pd", pd);
		mv.addObject("msg", "add");
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//暂停任务
	@RequestMapping(value = "/pauseJob")
	@ResponseBody
	public ModelAndView pauseJob() throws Exception {
		logger.info(Const.START_CN);
		logger.info("暂停任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		quartzScheduler.pauseJob(jobKey);
		mv.addObject("data", "succ");
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(new PageData()));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//恢复任务
	@RequestMapping(value = "/resumeJob")
	public ModelAndView resumeJob() throws Exception {
		logger.info(Const.START_CN);
		logger.info("恢复任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		quartzScheduler.resumeJob(jobKey);
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(new PageData()));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//删除任务
	@RequestMapping(value = "/deleteJob")
	public ModelAndView deleteJob() throws Exception {
		logger.info(Const.START_CN);
		logger.info("删除任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		quartzScheduler.deleteJob(jobKey);
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(new PageData()));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//立即运行任务
	@RequestMapping(value = "/triggerJob")
	public ModelAndView triggerJob() throws Exception {
		logger.info(Const.START_CN);
		logger.info("立即运行任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
		quartzScheduler.triggerJob(jobKey);
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//添加一个定时任务
	@RequestMapping(value = "/add")
	public ModelAndView add() throws Exception {
		logger.info(Const.START_CN);
		logger.info("添加定时任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String cron = (String) pd.get("cron");
		String clazz = (String) pd.get("clazz");
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		String triggerName = (String) pd.get("triggerName");
		String triggerGroupName = (String) pd.get("triggerGroupName");
		QuartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, Class.forName(clazz), cron);
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(new PageData()));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}
	//修改一个定时任务
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logger.info(Const.START_CN);
		logger.info("修改定时任务");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String cron = (String) pd.get("cron");
		String jobName = (String) pd.get("jobName");
		String jobGroupName = (String) pd.get("jobGroupName");
		String triggerName = (String) pd.get("triggerName");
		String triggerGroupName = (String) pd.get("triggerGroupName");
		QuartzManager.modifyJobTime(jobName, jobGroupName, triggerName, triggerGroupName, cron);
		mv.setViewName("system/tools/task");
		mv.addObject("pd", pd);
		mv.addObject("jobInfos", getSchedulerJobInfo(new PageData()));
		logger.info(Const.END_CN);
		logger.info(StringUtils.EMPTY);
		return mv;
	}

	private List<JobEntity> getSchedulerJobInfo(PageData pd) throws SchedulerException {

		//查询条件
		//任务状态
		String jobStatus = (String) pd.get("jobStatus");
		//任务名称
		String jobName = (String) pd.get("jobName");
		//起止时间
		//String startDate = (String) pd.get("startDate");
		//String endDate = (String) pd.get("endDate");

		List<JobEntity> jobInfos = new ArrayList<JobEntity>();
		List<String> triggerGroupNames = quartzScheduler.getTriggerGroupNames();
		for (String triggerGroupName : triggerGroupNames) {
			Set<TriggerKey> triggerKeySet = quartzScheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName));
			for (TriggerKey triggerKey : triggerKeySet) {
				Trigger t = quartzScheduler.getTrigger(triggerKey);
				if (t instanceof CronTrigger) {
					CronTrigger trigger = (CronTrigger) t;
					JobKey jobKey = trigger.getJobKey();
					JobDetail jd = quartzScheduler.getJobDetail(jobKey);
					JobEntity jobInfo = new JobEntity();
					jobInfo.setJobName(jobKey.getName());
					jobInfo.setJobGroup(jobKey.getGroup());
					jobInfo.setTriggerName(triggerKey.getName());
					jobInfo.setTriggerGroupName(triggerKey.getGroup());
					jobInfo.setCronExpr(trigger.getCronExpression());
					jobInfo.setNextFireTime(trigger.getNextFireTime());
					jobInfo.setPreviousFireTime(trigger.getPreviousFireTime());
					jobInfo.setStartTime(trigger.getStartTime());
					jobInfo.setEndTime(trigger.getEndTime());
					jobInfo.setJobClass(jd.getJobClass().getCanonicalName());
					Trigger.TriggerState triggerState = quartzScheduler.getTriggerState(trigger.getKey());
					jobInfo.setJobStatus(triggerState.toString());// NONE未知, NORMAL正常运行, PAUSED暂停状态, COMPLETE完成状态, ERROR错误状态, BLOCKED锁定状态
					JobDataMap map = quartzScheduler.getJobDetail(jobKey).getJobDataMap();
					if (null != map && CollectionUtils.isNotEmpty(map.keySet())) {
						jobInfo.setCount(Integer.parseInt((String) map.get("count")));
						jobInfo.setJobDataMap(map);
					} else {
						jobInfo.setJobDataMap(new JobDataMap());
					}
					if ((org.apache.commons.lang3.StringUtils.isEmpty(jobStatus) && org.apache.commons.lang3.StringUtils.isEmpty(jobName))
							|| (org.apache.commons.lang3.StringUtils.isNotEmpty(jobStatus) && triggerState.toString().contains(jobStatus)
									&& org.apache.commons.lang3.StringUtils.isNotEmpty(jobName) && jobKey.getName().contains(jobName))
							|| (org.apache.commons.lang3.StringUtils.isNotEmpty(jobStatus) && triggerState.toString().contains(jobStatus))
							|| (org.apache.commons.lang3.StringUtils.isNotEmpty(jobName) && jobKey.getName().contains(jobName))) {
						jobInfos.add(jobInfo);
					}
				}
			}
		}
		return jobInfos;
	}
}
