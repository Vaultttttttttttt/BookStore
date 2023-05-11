package com.atguigu.utils;

import com.atguigu.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wxj27
 * @version 1.0
 * @description: TODO
 * @date 2023-03-04 16:11
 */

public class WebUtils {
    public static void copyParamToBean(HttpServletRequest request, Object bean) {
        try {
            BeanUtils.populate(bean, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description:将字符串转换成为int类型的数据
     * @param: strInt
     * @param: defaultValue
     * @return: int
     * @author wxj27
     * @date: 2023-03-11 15:31
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
