package org.example.job;

import java.util.List;
import cn.hutool.core.date.DateUtil;
import org.example.pojo.Index;
import org.example.service.IndexDataService;
import org.example.service.IndexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 作者：cjy
 * 类名：IndexDataSyncJob
 * 全路径类名：org.example.job.IndexDataSyncJob
 * 父类或接口：@see QuartzJobBean
 * 描述：定时刷新Redis数据任务类，刷新指数代码和指数数据。
 */
public class IndexDataSyncJob extends QuartzJobBean {

    @Autowired
    private IndexService indexService;

    @Autowired
    private IndexDataService indexDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时启动：" + DateUtil.now());

        List<Index> indexes = indexService.fresh();//刷新Redis的指数
        for (Index index : indexes) {
            indexDataService.fresh(index.getCode());//刷新Redis的指数数据
        }

        System.out.println("定时结束：" + DateUtil.now());

    }

}