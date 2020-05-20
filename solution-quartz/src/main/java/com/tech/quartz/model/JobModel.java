package com.tech.quartz.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Description 定时任务model
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Data
@Slf4j
public class JobModel implements Serializable {

    private static final long serialVersionUID = -437204764055579977L;

    // 任务名称
    private String jobName;

    // 任务所在组
    private String jobGroup;

    // 任务类名称
    private String jobClassName;

    // 触发器名称
    private String triggerName;

    // 触发器所在组
    private String triggerGroup;

    // cron表达式
    private String cronExpression;

    // 时区
    private String timeZoneId;

    // 状态
    private String status;

    public JobModel() {
    }

}
