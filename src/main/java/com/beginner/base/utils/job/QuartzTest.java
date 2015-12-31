package com.beginner.base.utils.job;

public class QuartzTest {

	public static void main(String[] args) {
		try {
			String orderNotice = "orderNotice";
			String triggerName = "orderNoticeTrigger";
			String orderNoticeGroup = "orderNoticeGroup";
			String orderNoticeTriggerGroup = "orderNoticeTriggerGroup";
			System.out.println("【系统启动】开始(每20秒输出一次)...");
			QuartzManager.addJob(orderNotice, orderNoticeGroup, triggerName, orderNoticeTriggerGroup, QuartzJob.class, "0/10 * * * * ?");

			//			System.out.println("【修改时间】开始(每2秒输出一次)...");  
			//			QuartzManager.modifyJobTime(job_name, "10/2 * * * * ?");  
			//			System.out.println("【移除定时】开始...");  
			//			QuartzManager.removeJob(job_name);  
			//			System.out.println("【移除定时】成功");  
			//			
			//			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");  
			//			QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?");  
			//			System.out.println("【移除定时】开始...");  
			//			QuartzManager.removeJob(job_name);  
			//			System.out.println("【移除定时】成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
