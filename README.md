# 亿帆SDK-Android 快速指引

# 目录

<!-- toc --><!-- TOC -->

* [亿帆SDK-Android 快速指引](#亿帆sdk-android-快速指引)
* [目录](#目录)
* [1. 支持的SDK平台及广告位](#1-支持的sdk平台及广告位)
* [2. 快速接入](#2-快速接入)
    * [2.1 SDK包导入](#21-sdk包导入)
        * [2.1.1 申请应用广告位](#211-申请应用广告位)
    * [2.1.2 导入aar和SDK依赖的jar包](#212-导入aar和sdk依赖的jar包)
        * [2.1.2.1 添加仓库](#2121-添加仓库)
        * [2.1.2.2 添加依赖](#2122-添加依赖)
    * [2.2 AndroidManifest配置](#22-androidmanifest配置)
        * [2.2.1 添加权限](#221-添加权限)
    * [2.3 混淆问题](#23-混淆问题)
    * [2.4 初始化SDK](#24-初始化sdk)
    * [2.5 隐私开关](#25-隐私开关)
    * [2.6 合规三步走](#26-合规三步走)
    * [2.7 关闭个性化](#27-关闭个性化)
* [3. 聚合三方SDK](#3-聚合三方sdk)
    * [3.1  穿山甲SDK](#31--穿山甲sdk)
    * [3.1.1 添加依赖](#311-添加依赖)
    * [3.1.2 添加权限](#312-添加权限)
    * [3.1.3 添加组件](#313-添加组件)
    * [3.1.4 添加混淆](#314-添加混淆)
    * [3.2  百度SDK](#32--百度sdk)
    * [3.2.1 添加依赖](#321-添加依赖)
    * [3.2.2 添加权限](#322-添加权限)
    * [3.2.3 添加组件](#323-添加组件)
    * [3.2.4 添加混淆](#324-添加混淆)
    * [3.3  快手SDK](#33--快手sdk)
    * [3.3.1 添加依赖](#331-添加依赖)
    * [3.3.2 添加权限](#332-添加权限)
    * [3.3.3 添加组件](#333-添加组件)
    * [3.3.4 添加混淆](#334-添加混淆)
    * [3.3.5 快手so架构问题](#335-快手so架构问题)
    * [3.4  优量汇SDK](#34--优量汇sdk)
    * [3.4.1 添加依赖](#341-添加依赖)
    * [3.4.2 添加权限](#342-添加权限)
    * [3.4.3 添加组件](#343-添加组件)
    * [3.4.4 添加混淆](#344-添加混淆)
    * [3.5  京东SDK](#35--京东sdk)
    * [3.4.1 添加依赖](#341-添加依赖-1)
    * [3.4.2 添加权限](#342-添加权限-1)
    * [3.4.3 添加组件](#343-添加组件-1)
    * [3.4.4 添加混淆](#344-添加混淆-1)
* [4. 加载广告](#4-加载广告)
    * [4.1 Banner位（Banner和信息流）](#41-banner位banner和信息流)
    * [4.2 插屏](#42-插屏)
    * [4.3 激励视频](#43-激励视频)
    * [4.4 开屏](#44-开屏)
    * [4.5 模板信息流](#45-模板信息流)
    * [4.6 全屏视频](#46-全屏视频)
    * [4.7 draw信息流](#47-draw信息流)
* [5. 使用注意事项](#5-使用注意事项)
    * [5.1 常见问题](#51-常见问题)

<!-- TOC -->

# 1. 支持的SDK平台及广告位

| SDK平台 | 开屏  | 激励视频 | 横幅  | 插屏(弹窗) | 模板信息流 | 全屏视频 | draw信息流 |
|-------|-----|------|-----|--------|-------|------|---------| 
| 穿山甲   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ✅       |
| 优量汇   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ❌       |
| 百青藤   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ❌       |
| 快手    | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ✅       |
| ADX   | ✅   | ✅    | ✅   | ✅      | ❌     | ✅    | ❌       |
| 京东    | ✅   | ❌    | ✅   | ✅      | ❌     | ❌    | ❌       |

# 2. 快速接入

下面介绍亿帆SDK的快速接入方法，开发中也可以参考[Example]下的示例工程，快速了解。

开发环境

```
操作系统：支持 Linux/Mac/Windows 系统，具体依赖开发者选择的 IDE
开发工具：支持 Android studio
支持设备：运行了 Android 4.2.2 以及以上系统的 Android 设备(minSdkVersion 17) 
```

## 2.1 SDK包导入

### 2.1.1 申请应用广告位

请在亿帆平台上申请应用ID和广告位

## 2.1.2 导入aar和SDK依赖的jar包

### 2.1.2.1 添加仓库

将本SDK压缩包内的yfsdk.aar文件及依赖的三方SDK文件夹中的aar文件复制到Application Module/libs文件夹(
没有的话须手动创建), 并将以下代码添加到您app的build.gradle中：

```
    repositories {
        flatDir {
            dirs 'libs'
        }
    }
```

### 2.1.2.2 添加依赖

AndroidX版本

因为亿帆SDK还没有使用AndroidX库，为了避免应用程序崩溃，请在项目的
```gradle.properties```中添加<br />

```
   android.enableJetifier=true 
   android.useAndroidX=true
```

来打开Jetifier。

```
    dependencies {
        ...
        implementation (name: 'yfsdk', ext: 'aar')
        implementation "com.android.support:appcompat-v7:28.0.0"
        implementation "com.android.support:support-media-compat:28.0.0"
        ...
    }
```

## 2.2 AndroidManifest配置

### 2.2.1 添加权限

```
    <!--必要权限-->
    <uses-permission android:name="android.permission.INTERNET" />
    
    <!--强烈推荐权限-->
    <!--强烈建议您在AndroidManifest.xml添加以下权限声明，若您的targetSDKVersion >= 23您还需要在运行时进行动态权限申请。
    注意：SDK不强制校验以下权限（即:无以下权限sdk也可正常工作），但建议您申请以下权限。
          针对单媒体的用户，允许获取权限的，投放定向广告；
          不允许获取权限的用户，投放通投广告。
          媒体可以选择是否把以下权限提供给亿帆，并承担相应广告填充和eCPM单价下降损失的结果。-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />    <!-- 可选，如果需要精确定位的话请加上此权限 -->
    
    <!--可选权限-->
    <!--下面权限可以优化用户的交互体验，提高eCPM。-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
    <!--注意: 该权限不强制获取，即使没有获取也能正常运行；获取该权限将帮助优化投放广告精准度。-->
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
```

## 2.3 混淆问题

请添加下面混淆规则到应用混淆规则中。

```

-keepattributes Signature

-keepattributes *Annotation*

-dontwarn sun.misc.**

-keep class com.google.gson.examples.android.model.** { <fields>; }

-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

##---------------End: proguard configuration for Gson  ----------

-keep class com.yfanads.android.** { *; }
-keep class com.yfanads.ads.** { *; }
-keep class com.google.** { *; }
-dontwarn okio.**
-dontwarn com.ak.**
-dontwarn android.webkit.**
-dontwarn com.bytedance.sdk.**
-keep class android.** { *; }
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
```

## 2.4 初始化SDK

**<font color="red">注意：需要在主线程中执行初始化方法</font>**</br>

****开发者需要在Application#onCreate()方法中调用以下代码来初始化亿帆SDK。
目前SDK已支持多进程，如果明确某个进程不会使用到广告SDK，可以只针对特定进程初始化广告SDK。****</br>

**接入代码示例：参考Demo中的MyApplication类**

```
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        

        // 一般是在主进程中初始化SDK，避免在其它不展示广告的进程中初始化SDK，浪费资源
        if (TextUtils.equals(this.getPackageName(), getProcessName(this))) {
            initSDK();
        }
    }

    private void initSDK() {
        YFAdsConfig YFAdsConfig = new YFAdsConfig.YFAdsConfigBuilder(GlobalConst.APP_ID, "你的应用版本", "你的应用包名")
                .setDebug(true)
                .builder();
        // 当前是否有权限
        fcAdsConfig.setCanUsePhoneState(hasPermission(Manifest.permission.READ_PHONE_STATE));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fcAdsConfig.setCanUseAppList(hasPermission(Manifest.permission.REQUEST_INSTALL_PACKAGES));
        } else {
            fcAdsConfig.setCanUseAppList(true);
        }
        fcAdsConfig.setCanUseLocation(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
        fcAdsConfig.setCanUseWifiState(hasPermission(Manifest.permission.ACCESS_WIFI_STATE));
        fcAdsConfig.setCanUseAndroidId(hasPermission(Manifest.permission.READ_PHONE_STATE));

        YFAdsManager.getInstance().init(this, YFAdsConfig);
    }
    
    private boolean hasPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private String getProcessName(Context context) {
        if (context == null) return null;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName;
            }
        }
        return null;
    }
}
```

**主要API**

**YFAdsManager**

| 方法名                                                    | 方法介绍                        |
|:-------------------------------------------------------|:----------------------------|
| init(Application application, YFAdsConfig YFAdsConfig) | 初始化SDK                      |
| boolean isInit()                                       | 是否初始化                       |
| YFAdsConfig getYFAdsConfig()                           | 获取亿帆SDK的配置,详细参考YFAdsConfig类 |

**YFAdsConfigBuilder**

| 方法名                                                                            | 方法介绍                                                                                     |
|:-------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| public YFAdsConfigBuilder(String appId, String appVer, String appName)         | 初始化的方法，appId在平台申请的AppId、 appVer当前应用的版本号、appName当前应用的名字                                   |
| public YFAdsConfig setDebug(Boolean debug)                                     | 是否是调试模式                                                                                  |
| public YFAdsConfigBuilder setCanUseLocation(boolean canUseLocation)            | 设置是否允许SDK主动使用地理位置信息。true可以获取，false禁止获取。默认为true                                           |
| public YFAdsConfigBuilder setYFLocation(YFLocation yfLocation)                 | 传入地理位置信息, 当canUseLocation=false时，亿帆sdk使用您传入的地理位置信息                                       |         
| public YFAdsConfigBuilder setCanUsePhoneState(boolean canUsePhoneState)        | 设置是否允许SDK主动使用手机硬件参数，如：imei。true可以使用，false禁止使用。默认为true                                    |
| public YFAdsConfigBuilder setDevImei(String devImei)                           | 可传入imei信息，当canUsePhoneState=false时，亿帆sdk使用您传入的imei信息                                     |
| public YFAdsConfigBuilder setCanUseWifiState(boolean canUseWifiState)          | 设置是否允许SDK主动使用ACCESS_WIFI_STATE权限获取MAC信息。true可以使用，false禁止使用。默认为true                       |
| public YFAdsConfigBuilder setCanUseWriteExternal(boolean canUseWriteExternal)	 | 设置是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限。true可以使用，false禁止使用。默认为true                         |	                                         |
| public YFAdsConfigBuilder setDevOaid(String devOaid)                           | 	传入OAID，当canUseOaid=false时，亿帆sdk使用您传入的OAID                                               |
| public YFAdsConfigBuilder setCanUseAppList(boolean canUseAppList)              | 设置是否允许SDK主动获取应用列表，判定广告对应的应用是否在用户的app上安装，避免投放错误的广告，以此提高用户的广告体验。true可以使用，false禁止使用。默认为true |
| public YFAdsConfigBuilder setCanUseAndroidId(boolean canUseAndroidId)          | 设置是否允许SDK主动使用AndroidId。true可以使用，false禁止使用。默认为true                                        |
| public YFAdsConfigBuilder setLimitPersonal(boolean limitPersonal)              | 设置是否允许SDK设置个性化推荐。true限制个性化推荐，false不限制。默认为false                                           |
| public YFAdsConfigBuilder setUseAdx(boolean isOnlyAdx)                         | 设置只使用Adx功能                                                                               |
| public YFAdsConfig builder()                                                   | 创建配置对象实例                                                                                 |

## 2.5 隐私开关

* 目前SDK已支持工信部隐私敏感权限要求</br>
* 敏感权限，用户同意则使用，若用户拒绝则不再获取</br>
* 申请权限界面由应用依据自身情况、用户真实选择，使用如下API更新隐私信息权限开关</br>
  **注意：目前OAID不属于个人信息保护法律范畴，建议提供获取的能力。**</br>

**主要API**

**YFAdsConfig**

| 方法名                                                              | 方法介绍                                                                                     |
|:-----------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| public void setCanUseLocation(boolean canUseLocation)            | 设置是否允许SDK主动使用地理位置信息。true可以获取，false禁止获取。默认为true                                           |
| public void setYFLocation(YFLocation yfLocation)                 | 传入地理位置信息, 当canUseLocation=false时，亿帆sdk使用您传入的地理位置信息                                       |         
| public void setCanUsePhoneState(boolean canUsePhoneState)        | 设置是否允许SDK主动使用手机硬件参数，如：imei。true可以使用，false禁止使用。默认为true                                    |
| public void setDevImei(String devImei)                           | 可传入imei信息，当canUsePhoneState=false时，亿帆sdk使用您传入的imei信息                                     |
| public void setCanUseWifiState(boolean canUseWifiState)          | 设置是否允许SDK主动使用ACCESS_WIFI_STATE权限获取MAC信息。true可以使用，false禁止使用。默认为true                       |
| public void setCanUseWriteExternal(boolean canUseWriteExternal)	 | 设置是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限。true可以使用，false禁止使用。默认为true                         |	                                            |
| public void setDevOaid(String devOaid)                           | 	传入OAID，当canUseOaid=false时，亿帆sdk使用您传入的OAID                                               |
| public void setCanUseAppList(boolean canUseAppList)              | 设置是否允许SDK主动获取应用列表，判定广告对应的应用是否在用户的app上安装，避免投放错误的广告，以此提高用户的广告体验。true可以使用，false禁止使用。默认为true |
| public void setCanUseAndroidId(boolean canUseAndroidId)          | 设置是否允许SDK主动使用AndroidId。true可以使用，false禁止使用。默认为true                                        |
| public void setLimitPersonal(boolean limitPersonal)              | 设置是否允许SDK设置个性化推荐。true限制个性化推荐，false不限制。默认为false                                           |

## 2.6 合规三步走

* [参考合规指南](http://www.yfanads.cn/guideline.html)

*

您需要确保App有<font color=#0099ff face="黑体">[隐私政策](http://www.yfanads.cn/privacy.html)</font>
并且在用户首次启动App时就弹出<font color=#0099ff face="黑体">[隐私政策](http://www.yfanads.cn/privacy.html)</font>
取得用户同意</br>
</br>

*

您务必告知用户，您选择亿帆SDK服务；请在<font color=#0099ff face="黑体">[隐私政策](http://www.yfanads.cn/privacy.html)</font>
中增加如下参考条款：</br>
我们的产品集成亿帆SDK，亿帆SDK需要收集您的设备Mac地址、唯一设备识别码(
IMEI/android ID)、SIM卡IMSI信息、地理位置信息以提供个性化推荐内容，提升用户体验。</br>
</br>

*

您务必确保用户同意<font color=#0099ff face="黑体">[隐私政策](http://www.yfanads.cn/privacy.html)</font>
之后，再初始化亿帆SDK。</br>

## 2.7 关闭个性化

*

为遵循《个人信息保护法》相关法规，亿帆SDK将为开发者提供个性化广告关闭能力接口，开发者可以调用接口，为开发者媒体应用的用户提供个性化广告关闭能力。 </br>
</br>

*

开发者应遵循法律法规要求，在客户端为用户创建可便捷查找的个性化广告关闭按钮，并保证用户点击关闭按钮后调用亿帆SDK关闭能力接口，保证个性化广告关闭功能真实有效。</br>

**主要API**

**YFAdsConfig**

| 方法名                                     | 方法介绍                                              |
|:----------------------------------------|:--------------------------------------------------|
| setLimitPersonal(boolean limitPersonal) | 设置限制个性化广告推荐。true 开启个性化推荐能力，false关闭个性化推荐能力，默认为true |

# 3. 聚合三方SDK

## 3.1  穿山甲SDK

## 3.1.1 添加依赖

* 请在工程根目录下找到一个名为 libs 的子目录;
* 找到工程的build.gradle文件，在dependencies节点进行如下添加即可：
  dependencies {

```
  // csj
  implementation(name: "open_ad_sdk_5615", ext: "aar")
  ...
  }
```

* 添加aar依赖需要在build.gradle文件的根节点下添加

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

## 3.1.2 添加权限

```
<!-- 穿山甲 -->
<uses-permission android:name="android.permission.GET_TASKS" />
<!-- 如果有视频相关的广告且使用textureView播放，请务必添加，否则黑屏 -->
<uses-permission android:name="android.permission.WAKE_LOCK" />

```

## 3.1.3 添加组件

亿帆SDK已经自动导入Manifest，如有需要可单独接入

```
<!-- bytedance start -->
<provider
	android:name="com.bytedance.sdk.openadsdk.TTFileProvider"
	android:authorities="${applicationId}.TTFileProvider"
	android:exported="false"
	android:grantUriPermissions="true">
	<meta-data
		android:name="android.support.FILE_PROVIDER_PATHS"
		android:resource="@xml/tt_provider_paths" />
</provider>
<provider
	android:name="com.bytedance.sdk.openadsdk.multipro.TTMultiProvider"
	android:authorities="${applicationId}.TTMultiProvider"
	android:exported="false" />
<!-- bytedance end -->
```

## 3.1.4 添加混淆

```
# csj sdk start
#3500以及以下版本混淆规则如下：
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class com.pgl.sys.ces.* {*;}
-keep class com.bytedance.embed_dr.** {*;}
-keep class com.bytedance.embedapplog.** {*;}
#3600版本-3800版本混淆规则如下：
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class ms.bd.c.**{*;}
-keep class com.bytedance.mobsec.**{*;} 
-keep class com.bytedance.embed_dr.** {*;}
-keep class com.bytedance.embedapplog.** {*;}
-keep interface com.bytedance.frameworks.baselib.network.http.cronet.I* {*;}
-keepnames class com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils
#3900版本及以上版本混淆规则如下：
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep class com.bytedance.frameworks.** { *; }
-keep class ms.bd.c.Pgl.**{*;}
-keep class com.bytedance.mobsec.metasec.ml.**{*;}
-keep class com.ss.android.**{*;}
-keep class com.bytedance.embedapplog.** {*;}
-keep class com.bytedance.embed_dr.** {*;}
-keep class com.bykv.vk.** {*;}
# csj sdk end

#gromore start
-keep class bykvm*.**
-keep class com.bytedance.msdk.adapter.**{ public *; }
-keep class com.bytedance.msdk.api.** {
 public *;
}
-keep class com.bytedance.msdk.base.TTBaseAd{*;}
-keep class com.bytedance.msdk.adapter.TTAbsAdLoaderAdapter{
    public *;
    protected <fields>;
}
#gromore end
```

## 3.2  百度SDK

## 3.2.1 添加依赖

* 请在工程根目录下找到一个名为 libs 的子目录;
* 找到工程的build.gradle文件，在dependencies节点进行如下添加即可：
  dependencies {

```
  // baidu
  implementation(name: "Baidu_MobAds_SDK-release_v93223", ext: "aar")
  ...
  }
```

* 添加aar依赖需要在build.gradle文件的根节点下添加

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

## 3.2.2 添加权限

```
无
```

## 3.2.3 添加组件

亿帆SDK已经自动导入Manifest，如有需要可单独接入

```
无
```

## 3.2.4 添加混淆

```
#baidu start
-keep class com.baidu.mobads.** { *; }
#baidu end
```

如果您的工程中接入了资源混淆插件AndResGuard，为了保证SDK的资源可以被正常使用，需要在build.gradle中新增白名单配置，内容如下：

```
andResGuard {
  // 白名单配置
  whiteList = [
               "R.integer.min_screen_width_bucket",
               "R.style.DialogAnimationUp",
               "R.style.DialogAnimationRight",
               "R.style.DialogFullScreen",
               "R.drawable.gdt_*"
               ]
}
```

## 3.3  快手SDK

## 3.3.1 添加依赖

* 请在工程根目录下找到一个名为 libs 的子目录;
* 找到工程的build.gradle文件，在dependencies节点进行如下添加即可：
  dependencies {

```
  // ks
  implementation(name: "kssdk-ad-33552", ext: "aar")
  ...
  }
```

* 添加aar依赖需要在build.gradle文件的根节点下添加

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

## 3.3.2 添加权限

```
无
```

## 3.3.3 添加组件

亿帆SDK已经自动导入Manifest，如有需要可单独接入

```
无
```

## 3.3.4 添加混淆

```
#kuaishou begin
-keep class org.chromium.** {*;}
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**
#kuaishou end
```

## 3.3.5 快手so架构问题

目前快手最新的SDK版本不支持armeabi架构，如果apk集成的so文件和apk安装的OS仅匹配到armeabi目录下的so，会导致快手广告无法填充。</br>
解决方案：将快手aar后缀改为zip，解压之后进入jni/armeabi-v7a目录，将其中所有的so文件拷贝到工程的app/libs/armeabi中，然后打包apk即可

## 3.4  优量汇SDK

## 3.4.1 添加依赖

* 请在工程根目录下找到一个名为 libs 的子目录;
* 找到工程的build.gradle文件，在dependencies节点进行如下添加即可：
  dependencies {

```
  // ylh
  implementation(name: "GDTSDK_4542", ext: "aar")
  ...
  }
```

* 添加aar依赖需要在build.gradle文件的根节点下添加

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

## 3.4.2 添加权限

```
无
```

## 3.4.3 添加组件

亿帆SDK已经自动导入Manifest，如有需要可单独接入

```
<provider
    android:name="com.qq.e.comm.GDTFileProvider"
    android:authorities="${applicationId}.gdt.fileprovider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/gdt_file_path" />
</provider>
```

## 3.4.4 添加混淆

```
# GDT sdk start
-dontwarn com.qq.e.**
-keep class com.qq.e.** {*;}
-dontpreverify
# GDT sdk end
```

## 3.5  京东SDK

## 3.4.1 添加依赖

* 请在工程根目录下找到一个名为 libs 的子目录;
* 找到工程的build.gradle文件，在dependencies节点进行如下添加即可：
  dependencies {

```
  // jd
  implementation(name: "jad_yun_sdk_jingdong_2.5.6_20231114", ext: "aar")
  ...
  }
```

* 添加aar依赖需要在build.gradle文件的根节点下添加

```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```

## 3.4.2 添加权限

```
无
```

## 3.4.3 添加组件

亿帆SDK已经自动导入Manifest

```
无
```

## 3.4.4 添加混淆

```
# JD sdk start
-keep class com.jd.ad.sdk.** { *; }
# JD sdk end
```

# 4. 加载广告

目前亿帆SDK支持以下物理广告位类型：[Banner位](#41-banner位banner和信息流)、[插屏](#42-插屏)、[激励视频](#43-激励视频)、[开屏](#44-开屏)、[模板信息流](#45-模板信息流)、[全屏视频](#46-全屏视频)、[draw信息流](#47-draw信息流)。 </br>
请根据自身广告位类型进行对接。</br>

**<font color="red">注意</font>：
Banner位广告和自渲染广告依附广告容器的上下文必须是Activity（通过viewGroup.getContext（）获取到的是Activity），否则可能会出现下面合规问题：1.
下载类广告二次确认框无法弹出;2. 负反馈弹窗无法弹出;**

**主要API**

**BaseAdListener**

| 方法名                                   | 方法介绍   |
|:--------------------------------------|:-------|
| void onAdSuccess();                   | 加载广告成功 |
| void onAdExposure();                  | 广告曝光成功 |
| void onAdClicked();                   | 广告点击   |
| void onAdClosed();                    | 广告关闭   |
| void onAdFailed(YFAdError yfAdError); | 广告加载失败 |

**YFAdError**

| 状态码 （code） | 说明                     |
|:-----------|:-----------------------|
| 99         | 加载广告成功                 |
| 1004       | 请求广告超过策略时长             |
| 1005       | 广告策略超时                 |
| 9901       | 广告返回的数据为空              |
| 9902       | 广告加载异常                 |
| 9903       | 广告展示失败                 |
| 9904       | 广告渲染失败                 |
| 9905       | 未配置SDK渠道               |
| 9906       | 执行SDK渠道选择是发生异常         |
| 9907       | 没有策略                   |
| 9908       | 联盟SDK启动失败              |
| 9909       | 穿山甲SDK加载超时，不再加载后续SDK渠道 |
| 9910       | 穿山甲SDK加载超时             |
| 9911       | 百度SDK加载失败              |
| 9912       | 快手SDK加载失败，广告位id类型转换异常  |
| 9913       | 当前activity已被销毁，不再请求广告  |
| 9914       | 快手SDK初始化失败             |
| 99141      | 无快手类型                  |
| 99142      | 无百度类型                  |
| 9915       | 广告渲染失败                 |
| 9916       | 穿山甲SDK初始化失败            |
| 9917       | 没有该sdk                 |
| 9920       | 自渲染失败                  |

## 4.1 Banner位（Banner和信息流）

具体可参见demo中BannerActivity

请求Banner位广告时，需要设置广告View的宽度（单位：dp），会根据设置的宽度计算出最优高度。</br>
当选择竖版样式时，接入方可以设置广告View的高度，SDK会自动等比例缩放广告图片，如果不设置高度，会根据设置的宽度计算出最优高度。</br>

**接入代码示例**

```
void initListener() {
    listener = new YFBannerListener() {

        @Override
        public void onAdSuccess() {
            logAndToast("广告加载成功");
        }

        @Override
        public void onAdExposure() {
            logAndToast("广告展现");
        }

        @Override
        public void onAdClicked() {
            logAndToast("广告点击");
        }

        @Override
        public void onAdClosed() {
            logAndToast("广告关闭");
        }

        @Override
        public void onAdFailed(YFAdError yfAdError) {
            logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
        }
    };
}
    
public void loadAndShowAd(View view) {
    final YFAdBanner showAdBanner = new YFAdBanner(this, adView, listener);
    logAndToast("广告请求中");
    showAdBanner.toGetData(potId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
              // 这里示例使用的广告位宽高比为640：100。
              int width = ScreenUtil.px2dip(getApplicationContext(),
                      (float) (ScreenUtil.getScreenWidth(getApplicationContext())));
              Log.d("TEST", "width = " + width + " , screen width ="
                      + ScreenUtil.getScreenWidth(getApplicationContext()));
              //如果高度传入0代表自适应高度
              showADBanner.setViewAcceptedSize(width, 250);
              Log.d("TEST", "开始加载");
              showADBanner.loadAndShow(jsonString);
        }

        @Override
        public void onFailed(int errorCode, String message) {

        }
    });
}
    
@Override
protected void onDestroy() {
  super.onDestroy();
  if (listener != null) {
      listener = null;
  }
}

```

**主要API**

**YFADBanner**

| 方法名                                                                                          | 方法介绍                                                               |
|:---------------------------------------------------------------------------------------------|:-------------------------------------------------------------------|
| public YFAdBanner(Activity activity, final ViewGroup adContainer, YFBannerListener listener) | Banner位广告位构造方法                                                     |
| public void setViewAcceptedSize(int expressViewWidth, int expressViewHeight)                 | 期望模板广告view的size,单位dp；注意：参数请按照平台勾选的比例去进行请求。现有1:1，3:2 ，2:3 三种比例可供选择。 |
| public void toGetData(String adId, OnResultListener onResultListener)                        | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。                             |
| public void loadAndShow(String jsonString)                                                   | 展示当前广告                                                             |
| public void loadOnly(String jsonString)                                                      | 请求广告（与showAds一一对应）                                                 |
| public void showAds()                                                                        | 展示广告（与loadOnly一一对应）                                                |

**YFBannerListener**

## 4.2 插屏

具体可参见demo中InterstitialActivity

**接入代码示例**

```

private void startInterstitial(String adId) {
    releaseListener();
    createListener();
    //初始化
    YFAdInterstitialAds easyInterstitial = new YFAdInterstitialAds(this, listener);
    easyInterstitial.setKeyBackCloseAdOfSelfRender(true);
    easyInterstitial.setClickCloseAdOfSelfRender(false);
    easyInterstitial.setViewAcceptedSize(300, 533);
    //必须：设置策略信息
    easyInterstitial.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyInterstitial.loadAndShow(jsonString);
        }

        @Override
        public void onFailed(int errorCode, String message) {
        }
    });
}

public void createListener() {
    listener = new YFInterstitialListener() {

        @Override
        public void onAdSuccess() {
            logAndToast("广告就绪");
        }

        @Override
        public void onAdExposure() {
            logAndToast("广告展示");
        }

        @Override
        public void onAdClicked() {
            logAndToast("广告点击");
        }

        @Override
        public void onAdClosed() {
            logAndToast("广告关闭");
        }

        @Override
        public void onAdFailed(YFAdError yfAdError) {
            logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
        }
    };
}

private void releaseListener() {
    if (listener != null) {
        listener = null;
    }
}

```

**主要API**

**YFAdInterstitialAds**

| 方法名                                                                           | 方法介绍                                                                                              |
|:------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------|
| public YFAdInterstitialAds(Activity activity, YFBannerListener listener)      | 插屏广告位构造方法                                                                                         |
| public void setKeyBackCloseAdOfSelfRender(boolean keyBackCloseAdOfSelfRender) | 自渲染下按下back键后，关闭广告。                                                                                |
| public void setClickCloseAdOfSelfRender(boolean clickCloseAdOfSelfRender)     | 自渲染下点击广告后，关闭广告。                                                                                   |
| public void setViewAcceptedSize(int expressViewWidth, int expressViewHeight)  | 期望模板广告view的size,单位dp；注意：参数请按照平台勾选的比例去进行请求。现有1:1，3:2 ，2:3 三种比例可供选择。</br>自渲染下，按照宽度的9：16比例自适应，单位也是dp |
| public void toGetData(String adId, OnResultListener onResultListener)         | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。                                                            |
| public void loadAndShow(String jsonString)                                    | 展示当前广告                                                                                            |
| public void loadOnly(String jsonString)                                       | 请求广告（与showAds一一对应）                                                                                |
| public void showAds()                                                         | 展示广告（与loadOnly一一对应）                                                                               |

**YFInterstitialListener**

## 4.3 激励视频

具体可参见demo中RewardVideoActivity

**接入代码示例**

```
private void startReward(String adId) {
    //必须：核心事件监听回调
    listener = new YFRewardVideoListener() {

        @Override
        public void onAdSuccess() {
            logAndToast("广告加载成功");
        }

        @Override
        public void onAdExposure() {
            logAndToast("广告展示");
        }

        @Override
        public void onAdClicked() {
            logAndToast("广告点击");
        }

        @Override
        public void onAdClosed() {
            logAndToast("广告关闭");
            RewardVideoActivity.this.finish();
        }

        @Override
        public void onAdFailed(YFAdError yfAdError) {
            logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
        }


        @Override
        public void onVideoCached() {
            logAndToast("广告缓存成功");
        }

        @Override
        public void onVideoComplete() {
            logAndToast("视频播放完毕");
        }

        @Override
        public void onVideoSkip() {
            logAndToast("跳过视频");
        }

        @Override
        public void onAdReward() {
            logAndToast("激励发放");
        }

        @Override
        public void onRewardServerInf(YFRewardServerCallBackInf inf) {
            logAndToast("奖励发放成功");
            if (inf != null) {
                onRewardServer(inf.rewardInf);
            }
        }
    };
    //初始化，注意需要时再初始化，不要复用。
    final YFAdRewardAds easyRewardVideo = new YFAdRewardAds(this, listener, "123");
    easyRewardVideo.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyRewardVideo.loadAndShow(jsonString);
        }

        @Override
        public void onFailed(int errorCode, String message) {
        }
    });
    
    // 返回不同激励视频得回调
    private void onRewardServer(YFRewardServerCallBackInf.RewardInf rewardInf) {

        Log.d("reward", "type = " + rewardInf.type);
        if (rewardInf instanceof YFRewardServerCallBackInf.BDRewardInf) {
            Log.d("reward", "type = BDRewardInf ");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.YlhRewardInf) {
            Log.d("reward", "type = YlhRewardInf");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.KsRewardInf) {
            Log.d("reward", "type = KsRewardInf");
        } else if (rewardInf instanceof YFRewardServerCallBackInf.CsjRewardInf) {
            Log.d("reward", "type = CsjRewardInf");
        } else {
            Log.d("reward", "type = Other");
        }
    }
}

```

**主要API**

**YFAdRewardAds**

| 方法名                                                                                          | 方法介绍                                   |
|:---------------------------------------------------------------------------------------------|:---------------------------------------|
| public YFAdRewardAds(Activity activity, YFRewardVideoListener baseAdListener, String userId) | 激励视频广告位构造方法,userId：用户ID                |
| public void toGetData(String adId, OnResultListener onResultListener)                        | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。 |
| public void loadAndShow(String jsonString)                                                   | 展示当前广告                                 |
| public void loadOnly(String jsonString)                                                      | 请求广告（与showAds一一对应）                     |
| public void showAds()                                                                        | 展示广告（与loadOnly一一对应）                    |

**YFRewardVideoListener**

| 方法名                                                    | 方法介绍                                                   |
|:-------------------------------------------------------|:-------------------------------------------------------|
| void onVideoCached();                                  | 广告缓存成功                                                 |
| void onVideoComplete();                                | 视频播放完毕                                                 |
| void onVideoSkip();                                    | 跳过视频                                                   |
| void onAdReward();                                     | 激励发放                                                   |
| void onRewardServerInf(YFRewardServerCallBackInf inf); | 激励视频返回的服务器回调信息，穿山甲一直支持，优量汇自v4.330.1200 开始支持,百度9.13开始支持 |

**YFRewardVideoListener**

| 变量                  | 变量介绍 |
|:--------------------|:-----|
| RewardInf rewardInf | 回调类型 |

**RewardInf**

| 变量                           | 变量介绍    |
|:-----------------------------|:--------|
| Type type                    | 类型      |
| Map<String, String> appExtra | App传递的值 |

**Type**

| 变量 | 变量介绍 |
|:-----|:--|
| KS | 快手 |
| YLH | 优量汇 |
| BD | 百度 |
| CSJ | 穿山甲 |
| XM | 小米 |
| OPPO | 穿山甲 |
| HW | 华为 |

**BDRewardInf**

| 变量                     | 变量介绍     |
|:-----------------------|:---------|
| boolean bdRewardVerify | 百度是否需要验证 |

**YlhRewardInf**

| 变量                | 变量介绍    |
|:------------------|:--------|
| String ylhTransId | 优量汇交易ID |

**KsRewardInf**

| 变量  | 变量介绍 |
|:----|:-----|

**CsjRewardInf**

| 变量                   | 变量介绍                |
|:---------------------|:--------------------|
| boolean rewardVerify | 是否有效                |
| int rewardType       | 奖励类型，0:基础奖励 >0:进阶奖励 |
| int rewardAmount     | 发放数量                |
| String rewardName    | 奖励名称                |
| float rewardPropose  | 建议奖励百分比             |
| int code             | 状态码                 |
| String message       | 信息                  |

## 4.4 开屏

具体可参见demo中SplashActivity

**注意：** </br>
**1. 展示开屏广告的容器状态必须为可见，否则广告通不返回广告。**</br>
**2. 开屏广告容器高度必须大于等于屏幕高度的75%，否则广告通广告会出现一闪而过现象。**

<font color="red">
重要1：开屏页请设置为全屏样式，并隐藏虚拟按键和状态栏，防止跳过按钮和广告标识被遮挡</font>


<font color="red">重要2: 开屏对物理返回键屏蔽，影响曝光数据</font>

```
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
```

**接入代码示例**

```
@Override
public void initView(Bundle savedInstanceState) {
    type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
    String potId = getIntent().getStringExtra("potId");

    adContainer = findViewById(R.id.splash_container);
    adLogoLly = findViewById(R.id.ll_logo);

    if (!TextUtils.isEmpty(potId)) {
        loadSplash(adContainer, potId);
    }
}

/* *
 * 加载开屏广告，开屏推荐使用加载并展示开屏广告方式
 *
 * @param adContainer    广告承载布局，不可为空
 * @param logoContainer  底部logo布局，可以为空
 * @param singleActivity 是否为单独activity中展示开屏广告
 * @param callBack       跳转回调，在回调中进行跳转主页或其他操作
 */
private void loadSplash(ViewGroup adContainer, String adId) {
    releaseListener();
    createListener();
    YFAdSplashAds fcAdSplash = new YFAdSplashAds(this, adContainer, listener);
    // 实际展示的高度，但不低于屏幕高度的75% （屏幕高度-LOGO高度-底部状态栏区域）
    int expressViewHeight = AppUtils.px2dip(this, AppUtils.getScreenHeight(this));
    // 如果底部需要展示logo，此为logo的大小，单位是dp
    expressViewHeight -= 63;
    // 如果是沉浸式状态栏，需要加上状态栏的高度
    expressViewHeight += AppUtils.px2dip(this, StatusBar.getStatusBarHeight(this));
    // 设置实际请求的高度
    fcAdSplash.setHeight(expressViewHeight);
    fcAdSplash.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            //必须：请求并展示广告
            fcAdSplash.loadAndShow(jsonString);
        }

        @Override
        public void onFailed(int errorCode, String message) {
            logAndToast("广告请求失败");
            finish();
        }
    });
    logAndToast("广告请求中");
}

private void createListener() {
    listener = new YFSplashListener() {
        @Override
        public void onAdSuccess() {
            logAndToast("广告加载成功 ");
            if (adLogoLly.getVisibility() == View.GONE) {
                adLogoLly.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAdExposure() {
            //设置开屏父布局背景色为白色
            logAndToast("广告展示成功");
        }

        @Override
        public void onAdClicked() {
            logAndToast("广告点击");
        }

        @Override
        public void onAdClosed() {

            logAndToast("广告关闭");

            goToMainActivity();
        }

        @Override
        public void onAdFailed(YFAdError yfAdError) {
            logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
            finish();
        }

    };
}

private void releaseListener() {
    if (listener != null) {
        listener = null;
    }
}

/**
* 跳转到主页面
*/
private void goToMainActivity() {
    finish();
}

@Override
protected void onDestroy() {
    super.onDestroy();
    Log.d("TEST", "SplashActivity onDestroy ");
    releaseListener();
}

```

**主要API**

**YFAdSplashAds**

| 方法名                                                                                             | 方法介绍                                   |
|:------------------------------------------------------------------------------------------------|:---------------------------------------|
| public YFAdSplashAds(Activity activity, ViewGroup adContainer, YFSplashListener baseAdListener) | 开屏广告位构造方法                              |
| public void toGetData(String adId, OnResultListener onResultListener)                           | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。 |
| public void loadAndShow(String jsonString)                                                      | 展示当前广告                                 |
| public void loadOnly(String jsonString)                                                         | 请求广告（与showAds一一对应）                     |
| public void showAds()                                                                           | 展示广告（与loadOnly一一对应）                    |

**YFSplashListener** 同 **BaseAdListener**回调监听

## 4.5 模板信息流

具体可参见demo中NativeExpressRecyclerViewActivity

**接入代码示例**

```
  @Override
  public void onBindViewHolder(final CustomViewHolder customViewHolder, final int position) {
      int type = getItemViewType(position);
      if (TYPE_AD == type) {
          // 动态计算需要渲染的位置
          loadNativeExpress(potId, customViewHolder.container, position);
      } else {
          customViewHolder.title.setText(mData.get(position).getTitle());
      }
  }
  
  /**
   * 加载并展示原生模板信息流广告.
   *
   * @param adId        adId
   * @param adContainer adContainer
   * @author JamesQian
   * @date 2023/9/9 17:29
   **/
  private void loadNativeExpress(String adId, ViewGroup adContainer, int pos) {
      // 需要计算当前的index
      int index = (pos) / ITEMS_PER_AD;
      Log.d("TEST", "loadNativeExpress isNativeLoading " + index);
      // 请求下一页广告数据
      if (index >= adViewList.size()) {
          startRequestAds(adId, adContainer, index);
          return;
      }
      if (adsStatus == 2) {
          //同一位置广告，请求完成，直接渲染
          addView(adContainer, index);
          return;
      }

      if (adsStatus == 1) {
          //同一位置广告，正在请求中，不再重复请求, 缓存对应得view和pos，请求成功后加入到视图中
          Log.d("TEST", "loadNativeExpress isNativeLoading");
          adViewTemp.add(new AdView(adContainer, index));
          return;
      }

      //广告请求
      startRequestAds(adId, adContainer, index);
  }

  private void startRequestAds(String adId, ViewGroup adContainer, int index) {
      adsStatus = 1;
      adViewTemp.add(new AdView(adContainer, index));
      final YFAdNativeExpressAds easyNativeExpress =
              new YFAdNativeExpressAds(mActivity, new YFNativeExpressListener() {

                  @Override
                  public void onAdRenderSuccess(List<View> viewList) {
                      logAndToast("广告渲染成功 " + viewList.size());
                      Log.e("ad", viewList.size() + " loadNativeExpress " + adViewTemp);
                      adViewList.addAll(viewList);
                      addView();
                      adsStatus = 2;
                  }

                  @Override
                  public void onAdSuccess() {
                      logAndToast("广告加载成功");
                  }

                  @Override
                  public void onAdExposure() {
                      logAndToast("广告展示");
                  }

                  @Override
                  public void onAdRenderFailed() {
                      adsStatus = 0;
                      logAndToast("广告渲染失败");
                  }

                  @Override
                  public void onAdClicked() {
                      logAndToast("广告点击");
                  }

                  @Override
                  public void onAdClosed() {
                      logAndToast("广告关闭");
                  }

                  @Override
                  public void onAdFailed(YFAdError fcAdError) {
                      adsStatus = 0;
                      logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
                  }

              });
      // 设置期望的广告的数量，一次请求不能超过5条，返回0-5条数据。
      easyNativeExpress.setAdsNumbers(realAdNumber);
      //必须：设置策略信息
      easyNativeExpress.toGetData(adId, new OnResultListener() {
          @Override
          public void onSuccess(String jsonString) {
              easyNativeExpress.loadAndShow(jsonString);
          }

          @Override
          public void onFailed(int errorCode, String message) {

          }
      });
      logAndToast("广告请求中");
  }
```

**主要API**

**YFAdNativeExpressAds**

| 方法名                                                                              | 方法介绍                                   |
|:---------------------------------------------------------------------------------|:---------------------------------------|
| public YFAdNativeExpressAds(Activity activity, YFNativeExpressListener listener) | 广告位构造方法                                |
| public void toGetData(String adId, OnResultListener onResultListener)            | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。 |
| public void setAdContainer(ViewGroup container)                                  | 设置用来展示广告的父布局                           |
| public void loadAndShow(String jsonString)                                       | 展示当前广告                                 |
| public void loadOnly(String jsonString)                                          | 请求广告（与showAds一一对应）                     |
| public void showAds()                                                            | 展示广告（与loadOnly一一对应）                    |
| public void destroy()                                                            | 资源释放                                   |
| public void setAdsNumbers(int realAdNumber)                                      | 设置期望的广告的数量，一次请求不能超过5条，返回0-5条数据。        |

**YFNativeExpressListener**

| 方法名                      | 方法介绍 |
|:-------------------------|:-----|
| void onAdRenderFailed()  | 渲染失败 |
| void onAdRenderSuccess() | 渲染成功 |

## 4.6 全屏视频

具体可参见demo中FullScreenVideoActivity

**接入代码示例**

```
@Override
protected void onDestroy() {
    super.onDestroy();
    if (listener != null) {
        listener = null;
    }
}

/**
 * 初始化获取展示全屏视频的广告对象。
 * 也可以选择先提前加载，然后在合适的时机再调用展示方法
 */
private void startFullVideo(String adId) {

    //推荐：核心事件监听回调
    listener = new YFFullScreenVideoListener() {

        @Override
        public void onVideoComplete() {
            logAndToast("视频播放结束");
        }

        @Override
        public void onVideoSkipped() {
            logAndToast("跳过视频");
        }

        @Override
        public void onVideoCached() {
            //广告缓存成功，可以在此记录状态，但要注意：不一定所有的广告会返回该回调
            logAndToast("广告缓存成功");
        }

        @Override
        public void onAdSuccess() {
            logAndToast("广告加载成功");
        }

        @Override
        public void onAdExposure() {
            logAndToast("广告展示");
        }


        @Override
        public void onAdClicked() {
            logAndToast("广告点击");
        }

        @Override
        public void onAdClosed() {
            logAndToast("广告关闭");
        }

        @Override
        public void onAdFailed(YFAdError yfAdError) {
            logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
        }
    };
    //初始化
    YFAdFullScreenVideoAds easyFullScreenVideo = new YFAdFullScreenVideoAds(this, listener);
    easyFullScreenVideo.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyFullScreenVideo.loadAndShow(jsonString);
        }

        @Override
        public void onFailed(int errorCode, String message) {
        }
    });
}

```

**主要API**

**YFAdFullScreenVideoAds**

| 方法名                                                                                        | 方法介绍                                   |
|:-------------------------------------------------------------------------------------------|:---------------------------------------|
| public YFAdFullScreenVideoAds(Activity activity, YFFullScreenVideoListener baseAdListener) | 广告位构造方法                                |
| public void toGetData(String adId, OnResultListener onResultListener)                      | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。 |
| public void loadAndShow(String jsonString)                                                 | 展示当前广告                                 |
| public void loadOnly(String jsonString)                                                    | 请求广告（与showAds一一对应）                     |
| public void showAds()                                                                      | 展示广告（与loadOnly一一对应）                    |

**YFFullScreenVideoListener**

| 方法名                     | 方法介绍   |
|:------------------------|:-------|
| void onVideoCached();   | 广告缓存成功 |
| void onVideoComplete(); | 视频播放完毕 |
| void onVideoSkip();     | 跳过视频   |

## 4.7 draw信息流

具体可参见demo中DrawActivity

**接入代码示例**

```
  private void loadDraw(String adId, ViewGroup adContainer) {
  
    YFDrawListener listener = new YFDrawListener() {
    
      @Override
      public void onAdSuccess() {
          logAndToast("广告加载成功");
      }
    
      @Override
      public void onAdExposure() {
          logAndToast("广告展示");
    
      }
    
      @Override
      public void onAdClicked() {
          logAndToast("广告点击");
    
      }
    
      @Override
      public void onAdClosed() {
          logAndToast("广告关闭");
      }
    
      @Override
      public void onAdFailed(YFAdError yfAdError) {
          logAndToast("广告加载失败 code=" + YFAdError.code + " msg=" + YFAdError.msg);
      }
  };
  YFAdDrawAds easyAdDraw = new YFAdDrawAds(mContext, listener);
  easyAdDraw.setAdContainer(adContainer);
  logAndToast("广告请求中");
  //必须：设置策略信息
  easyAdDraw.toGetData(adId, new OnResultListener() {
    @Override
    public void onSuccess(String jsonString) {
        //必须：请求并展示广告
        easyAdDraw.loadAndShow(jsonString);
    }
  
    @Override
    public void onFailed(int errorCode, String message) {
  
    }
  });
}

```

**主要API**

**YFAdDrawAds**

| 方法名                                                                   | 方法介绍                                   |
|:----------------------------------------------------------------------|:---------------------------------------|
| public YFAdDrawAds(Activity activity, YFDrawListener listener)        | 广告位构造方法                                |
| public void setAdContainer(final ViewGroup adContainer)               | 设置展示的布局                                |
| public void toGetData(String adId, OnResultListener onResultListener) | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。 |
| public void loadAndShow(String jsonString)                            | 展示当前广告                                 |
| public void loadOnly(String jsonString)                               | 请求广告（与showAds一一对应）                     |
| public void showAds()                                                 | 展示广告（与loadOnly一一对应）                    |

**YFDrawListener** 同 **BaseAdListener**

# 5. 使用注意事项

## 5.1 常见问题

```
Q：对接和测试中出现问题了怎么办？
A：首先查看常见问题中是否有相关问题；如果没有找到请设置下面属性打开亿帆日志开关抓取日志反馈给开发人员。
  代码中设置YFAdsConfigBuilder setDebug(true) {
  // 抓取main日志
  adb logcat -v time > d:\log.txt
```

```
Q：接入开屏广告需要注意什么？
A：1. 开屏广告不支持预加载；
   2. 请求开屏的超时时间最好为5秒，时间太短会影响广告展示量；
```

```
Q：接入Banner位广告需要注意什么？
A：1. 请求Banner位广告时，需要设置广告View的宽度（单位：dp），如果没有设置广告宽度，默认广告宽度为屏幕宽度；
   2. 广告View没有留白，如有留白需求，应用自己处理；
```

```
Q：接入插屏广告需要注意什么？
A：展示插屏广告时需要确保依赖的Activity不能为停止和销毁状态，否则广告无法展示；
```