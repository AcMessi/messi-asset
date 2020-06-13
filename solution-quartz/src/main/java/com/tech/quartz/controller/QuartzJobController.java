package com.tech.quartz.controller;


import com.tech.quartz.service.QuartzJobService;
import com.tech.quartz.util.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 定时器接口
 * @Date 4/20/2020
 * @Created by messi.chaoqun.wang
 */
@Api(tags = "定时任务接口")
@RestController
@RequestMapping("api/quartz/")
@Slf4j
public class QuartzJobController {

    @Autowired
    private QuartzJobService quartzJobService;

    @Autowired
    @Qualifier("cmsScheduler")
    private Scheduler scheduler;

    @PostMapping("/add")
    @ResponseBody
    public ApiResult addJob(String jobClassName, String jobGroupName, String cronExpression) {
        if (StringUtils.isEmpty(jobClassName) || StringUtils.isEmpty(jobGroupName) || StringUtils.isEmpty(cronExpression)) {
            return ApiResult.failure("参数不可为空");
        }

        try {
            quartzJobService.addJob(scheduler, jobClassName, jobGroupName, cronExpression);
        } catch (Exception e) {
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }

    @PostMapping("/pause")
    @ResponseBody
    public ApiResult pauseJob(String jobClassName, String jobGroupName) {
        if (StringUtils.isEmpty(jobClassName) || StringUtils.isEmpty(jobGroupName)) {
            return ApiResult.failure("参数不可为空");
        }

        try {
            quartzJobService.pauseJob(scheduler, jobClassName, jobGroupName);
        } catch (Exception e) {
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }

    @PostMapping("/resume")
    @ResponseBody
    public ApiResult resumeJob(String jobClassName, String jobGroupName) {
        if (StringUtils.isEmpty(jobClassName) || StringUtils.isEmpty(jobGroupName)) {
            return ApiResult.failure("参数不可为空");
        }

        try {
            quartzJobService.resumeJob(scheduler, jobClassName, jobGroupName);
        } catch (Exception e) {
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }

    @PostMapping("/reschedule")
    @ResponseBody
    public ApiResult rescheduleJob(String jobClassName, String jobGroupName, String cronExpression) {
        if (StringUtils.isEmpty(jobClassName) || StringUtils.isEmpty(jobGroupName)) {
            return ApiResult.failure("参数不可为空");
        }

        try {
            quartzJobService.rescheduleJob(scheduler, jobClassName, jobGroupName, cronExpression);
        } catch (Exception e) {
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }

    @PostMapping("/delete")
    @ResponseBody
    public ApiResult deleteJob(String jobClassName, String jobGroupName) {
        if (StringUtils.isEmpty(jobClassName) || StringUtils.isEmpty(jobGroupName)) {
            return ApiResult.failure("参数不可为空");
        }

        try {
            quartzJobService.deleteJob(scheduler, jobClassName, jobGroupName);
        } catch (Exception e) {
            return ApiResult.failure(e.getMessage());
        }

        return ApiResult.success();
    }

}

