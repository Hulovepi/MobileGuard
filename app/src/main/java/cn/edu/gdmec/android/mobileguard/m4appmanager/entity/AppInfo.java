package cn.edu.gdmec.android.mobileguard.m4appmanager.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AppInfo {
     /**应用程序包名*/
     public String packageName;
     /**应用程序图标*/
     public Drawable icon;
     /**应用程序名称*/
     public String appName;
     /**应用程序路径*/
     public String apkPath;
     /**应用程序大小*/
     public long appSize;
    /** app的版本号*/
    public String appVersion;
    /**  安装时间*/
    public String installTime;
    /** apk证书签署者信息*/
    public String certificateIssuer;
    /** 权限申请信息*/
    public String appPermissions;
    /** 应用程序的活动*/
    public String appActivities;

    /**是否是手机存储*/
     public boolean isInRoom;
     /**是否是用户应用*/
     public boolean isUserApp;
     /**是否选中，默认都为false*/
     public boolean isSelected = false;


      /**拿到App位置字符串*/
      public String getAppLocation(boolean isInRoom){
          if (isInRoom){
              return "手机内存";
          }else {
              return "外部存储";
          }
      }
    /**应用程序是否加速*/
    public boolean isLock;
}
