package com.beginner.base.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* <b>类名称：</b>TestThreadSegment<br/>
* <b>类描述：</b>多线程分段处理一个List里面的对象：这里List中所有数据做+1操作<br/>
* <b>创建人：</b>Hsiao Lin Studio-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2016-3-13 下午9:17:30<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class TestThreadSegment {

	private static Logger logger = LoggerFactory.getLogger(TestThreadSegment.class);

	public static void main(String[] args) throws InterruptedException {
		// 这里简单演示数据正好作为线程的倍数
		// 数据量特别大的情况下：
		// 参照TestThreadCountDownLatch等待所有线程执行完毕实例
		final List<Integer> list = new ArrayList<Integer>(100);
		for (int i = 0; i < 100; i++) {
			list.add(i);
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);

		//每个线程处理一段
		for (int thredNo = 0; thredNo < 10; thredNo++) {
			Obj obj = new Obj();
			//第几个线程
			obj.setThredNo(thredNo);
			obj.setList(list);

			executor.execute(obj);
		}

		if (!executor.isShutdown()) {
			executor.shutdownNow();
			executor = null;
		}

		for (int i = 0; i < list.size(); i++) {
			logger.info("list全部加1之后：{}", list.get(i));
		}
	}
}

class Obj implements Runnable {

	//第几个线程
	private int thredNo = 0;

	//要分段的list
	private List<Integer> list = null;

	public int getThredNo() {
		return thredNo;
	}

	public void setThredNo(int thredNo) {
		this.thredNo = thredNo;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int j = thredNo * 10; j < (thredNo + 1) * 10; j++) {
			//每个值都加一
			int count = list.get(j) + 1;
			list.set(j, count);
		}
	}
}
