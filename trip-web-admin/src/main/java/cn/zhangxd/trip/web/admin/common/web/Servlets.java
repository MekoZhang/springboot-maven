/**
 * Copyright (c) 2005-2012 springside.org.cn
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.zhangxd.trip.web.admin.common.web;

import cn.zhangxd.trip.util.StringHelper;
import cn.zhangxd.trip.web.admin.base.property.Global;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Http与Servlet工具类.
 */
public class Servlets {

    // 静态文件后缀
    private final static String[] staticFiles = StringHelper.split(Global.getConfig("web.staticFile"), ",");

    /**
     * 获取当前请求对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断访问URI是否是静态文件请求
     *
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (staticFiles == null) {
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                        + "web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return StringHelper.endsWithAny(uri, staticFiles)
                && !StringHelper.endsWithAny(uri, ".jsp")
                && !StringHelper.endsWithAny(uri, ".java");
    }
}
