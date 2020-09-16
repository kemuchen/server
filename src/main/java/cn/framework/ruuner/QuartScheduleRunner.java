package cn.framework.ruuner;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.util.List;

import cn.framework.system.dao.auto.entity.SysQuartzJobEntity;
import cn.framework.system.dao.auto.mapper.SysQuartzJobMapper;
import cn.framework.security.exception.codes.SysErrorCode;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import cn.framework.Constants;
import cn.framework.security.exception.AppException;

/**
 * @author ：柯雷
 * @ClassName:：QuartScheduleRunner
 * @Description： 定时器启动类
 * @date ：2018年11月16日 下午4:54:04
 */
@Component //被spring容器管理
@Order(2) //如果多个自定义ApplicationRunner，用来标明执行顺序
public class QuartScheduleRunner implements ApplicationRunner {

    /**
     * 日志打印对象
     */
    private static final Logger logger = LoggerFactory.getLogger(QuartScheduleRunner.class);

    /**
     * spring boot上下文环境
     */
    @Autowired
    private Environment environment;

    /**
     * 公共DAO
     */
    @Autowired
    SysQuartzJobMapper sysQuartzJobMapper;

    /**
     * @return void
     * @Description 启动定时器
     * @Date 15:36 2020/3/28
     * @Param [args]
     **/
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ("true".equals(environment.getProperty("app.timer"))) {
            logger.info("【QuartScheduleRunner.run】启动定时器");
            // 查询所有定时器配置
            SysQuartzJobEntity sysQuartzJobEntity = new SysQuartzJobEntity();
            sysQuartzJobEntity.setValid(Constants.YES);
            List<SysQuartzJobEntity> sysQuartzJobEntities = sysQuartzJobMapper.getSysQuartzJobs(sysQuartzJobEntity);

            // 遍历所有定时器并开始执行计划
            for (SysQuartzJobEntity quartzJobEntity : sysQuartzJobEntities) {
                this.doSchedule(quartzJobEntity);
            }
        }
    }

    /**
     * @param ：@param  clazz 定时任务
     * @param ：@param  jobId 任务id
     * @param ：@param  intervalInMillis 执行时间间隔
     * @param ：@throws SchedulerException
     * @return ：void
     * @throws
     * @Title：doSchedule
     * @Description：开始定时调度
     */
    public void doSchedule(SysQuartzJobEntity hotelQuartzJobEntity) throws AppException {
        logger.info("【QuartScheduleRunner.doSchedule】开始执行定时任务：" + hotelQuartzJobEntity.getDescription());
        try {
            // 创建任务实例
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(hotelQuartzJobEntity.getTask());
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(hotelQuartzJobEntity.getId() + "",
                    hotelQuartzJobEntity.getJob_group()).build();

            // 创建一个Trigger实例，定义该Job在triggerStartTime时间点执行，并且后续按照schedule永远执行
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(hotelQuartzJobEntity.getId() + "",
                    "quartz").startAt(hotelQuartzJobEntity.getStart_time())
                    .withSchedule(cronSchedule(hotelQuartzJobEntity.getSchedule())).build();

            // 创建scheduler实例
            SchedulerFactory sFactory = new StdSchedulerFactory();
            Scheduler scheduler = sFactory.getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            logger.error("【QuartScheduleRunner.doSchedule】启动定时任务出错：" + e);
            throw new AppException(SysErrorCode.SYSTEM_ERROR);
        }
    }
}
