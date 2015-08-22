package quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobA implements Job {
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		// 判断是否服务器端，是服务器则执行，本地或测试不执行
		// 任务
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("hi,JobA" + format.format(new Date()));
	}
}
