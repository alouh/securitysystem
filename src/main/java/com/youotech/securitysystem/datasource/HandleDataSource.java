package com.youotech.securitysystem.datasource;

/**
 * @author sundongyang
 * @date 2017/11/23 0023
 * @time 15:46
 * @desc 利用ThreadLocal解决线程安全问题
 * @see
 */
public class HandleDataSource {

    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    public static String getDataSource() {
        return holder.get();
    }
}
