package com.tech.quartz.util;

import com.tech.quartz.job.BaseJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description quartz utility file
 * @Date 5/20/2020
 * @Created by messi.chaoqun.wang
 */
@Slf4j
public class QuartzJobUtil {

    /**
     * 根据类名称，通过反射得到该类，然后创建一个BaseJob的实例
     *
     * @param className 需要实例化的类名
     * @return BaseJob
     * @throws Exception
     */
    public static BaseJob getClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return (BaseJob) clazz.newInstance();
    }
}