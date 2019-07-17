package com.zjp.route;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

/**
 * Created by zjp on 2019-06-05.
 *
 * @author zjp
 * @date 2019-06-05
 */
public class ARouter {

    //map  这个map就是存储整个项目中的类对象
    private Map<String,Class<? extends Activity>> activityList;
    private static ARouter aRouter = new ARouter();
    //上下文
    private Context context;

    private ARouter(){
        activityList = new HashMap<>();
    }

    public static ARouter getInstance(){
        return aRouter;
    }

    /**
     * 初始化的方法
     * @param application
     */
    public void init(Application application){
        this.context = application;
        List<String> className = getClassName("com.ziproute.util");
        for (String s : className) {
            try {
                Class<?> aClass = Class.forName(s);
                //判断当前这个类是否是IRoute的实现类
                if(IRoute.class.isAssignableFrom(aClass)){
                    IRoute iRoute = (IRoute) aClass.newInstance();
                    iRoute.putActivity();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将activity对象存入到map中
     * @param path
     * @param clazz
     */
    public void putActivity(String path,Class<? extends Activity> clazz){
        if(path!=null && clazz!=null){
            activityList.put(path,clazz);
        }
    }

    /**
     * 跳转
     * @param path
     * @param bundle
     */
    public void jumpActivity(String path, Bundle bundle){
        Class<? extends Activity> aClass = activityList.get(path);
        if(aClass == null){
            return;
        }
        Intent intent = new Intent(context,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(bundle!=null){
            intent.putExtra("bundle",bundle);
        }
        context.startActivity(intent);
    }


    /**
     * com.dongnao.util
     * @param packageName
     * @return
     */
    private List<String> getClassName(String packageName) {
        //创建一个class对象的集合
        List<String> classList = new ArrayList<>();
        String path = null;
        try {
            //通过包管理器   获取到应用信息类然后获取到APK的完整路径
            path = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), 0).sourceDir;
            //根据APK的完整路径获取到编译后的dex文件
            DexFile dexfile = new DexFile(path);
            // 获得编译后的dex文件中的所有class
            Enumeration entries = dexfile.entries();
            //然后进行遍历
            while (entries.hasMoreElements()) {
                //通过遍历所有的class的包名
                String name = (String) entries.nextElement();
                // 判断类的包名是否符合 com.dongnao.util
                if(name.contains(packageName)){
                    //如果符合 就添加到集合中
                    classList.add(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
    }
}
