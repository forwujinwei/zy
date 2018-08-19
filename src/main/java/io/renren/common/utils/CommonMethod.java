package io.renren.common.utils;

import java.util.UUID;

/**
 * @author jinweia.wu
 * @create 2018-08-18 10:44
 **/
public class CommonMethod {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
