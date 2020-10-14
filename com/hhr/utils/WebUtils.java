package com.hhr.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author qyk
 * @date 2020:09:20
 */
public class WebUtils {

    /**
     * 把Map中的数据注入到javaBean属性中
     * @param map
     * @param bean
     */
    public static <T>T copyParamToBean(Map map, T bean){

        try {
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String id , int defaultValue){
        try {
            return Integer.parseInt(id);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
