# 亿帆SDK-Android 快速指引

# 目录

<!-- toc -->

- [1. 支持的SDK平台及广告位](#1-%E6%94%AF%E6%8C%81%E7%9A%84sdk%E5%B9%B3%E5%8F%B0%E5%8F%8A%E5%B9%BF%E5%91%8A%E4%BD%8D)
- [2. 快速接入](#2-%E5%BF%AB%E9%80%9F%E6%8E%A5%E5%85%A5)
  * [2.1 SDK包导入](#21-sdk%E5%8C%85%E5%AF%BC%E5%85%A5)
    + [2.1.1 申请应用广告位](#211-%E7%94%B3%E8%AF%B7%E5%BA%94%E7%94%A8%E5%B9%BF%E5%91%8A%E4%BD%8D)
  * [2.1.2 导入aar和SDK依赖的jar包](#212-%E5%AF%BC%E5%85%A5aar%E5%92%8Csdk%E4%BE%9D%E8%B5%96%E7%9A%84jar%E5%8C%85)
    + [2.1.2.1 添加仓库](#2121-%E6%B7%BB%E5%8A%A0%E4%BB%93%E5%BA%93)
    + [2.1.2.2 添加依赖](#2122-%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)
  * [2.2 AndroidManifest配置](#22-androidmanifest%E9%85%8D%E7%BD%AE)
    + [2.2.1 添加权限](#221-%E6%B7%BB%E5%8A%A0%E6%9D%83%E9%99%90)
  * [2.3 混淆问题](#23-%E6%B7%B7%E6%B7%86%E9%97%AE%E9%A2%98)
  * [2.4 初始化SDK](#24-%E5%88%9D%E5%A7%8B%E5%8C%96sdk)
  * [2.5 隐私开关](#25-%E9%9A%90%E7%A7%81%E5%BC%80%E5%85%B3)
  * [2.6 合规三步走](#26-%E5%90%88%E8%A7%84%E4%B8%89%E6%AD%A5%E8%B5%B0)
  * [2.7 关闭个性化](#27-%E5%85%B3%E9%97%AD%E4%B8%AA%E6%80%A7%E5%8C%96)
- [3. 聚合三方SDK](#3-%E8%81%9A%E5%90%88%E4%B8%89%E6%96%B9sdk)
  * [3.1 穿山甲SDK](#31--%E7%A9%BF%E5%B1%B1%E7%94%B2sdk)
  * [3.1.1 添加依赖](#311-%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)
  * [3.1.2 添加权限](#312-%E6%B7%BB%E5%8A%A0%E6%9D%83%E9%99%90)
  * [3.1.3 添加组件](#313-%E6%B7%BB%E5%8A%A0%E7%BB%84%E4%BB%B6)
  * [3.1.4 添加混淆](#314-%E6%B7%BB%E5%8A%A0%E6%B7%B7%E6%B7%86)
  * [3.2 百度SDK](#32--%E7%99%BE%E5%BA%A6sdk)
  * [3.2.1 添加依赖](#321-%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)
  * [3.2.2 添加权限](#322-%E6%B7%BB%E5%8A%A0%E6%9D%83%E9%99%90)
  * [3.2.3 添加组件](#323-%E6%B7%BB%E5%8A%A0%E7%BB%84%E4%BB%B6)
  * [3.2.4 添加混淆](#324-%E6%B7%BB%E5%8A%A0%E6%B7%B7%E6%B7%86)
  * [3.3 快手SDK](#33--%E5%BF%AB%E6%89%8Bsdk)
  * [3.3.1 添加依赖](#331-%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)
  * [3.3.2 添加权限](#332-%E6%B7%BB%E5%8A%A0%E6%9D%83%E9%99%90)
  * [3.3.3 添加组件](#333-%E6%B7%BB%E5%8A%A0%E7%BB%84%E4%BB%B6)
  * [3.3.4 添加混淆](#334-%E6%B7%BB%E5%8A%A0%E6%B7%B7%E6%B7%86)
  * [3.3.5 快手so架构问题](#335-%E5%BF%AB%E6%89%8Bso%E6%9E%B6%E6%9E%84%E9%97%AE%E9%A2%98)
  * [3.4 优量汇SDK](#34--%E4%BC%98%E9%87%8F%E6%B1%87sdk)
  * [3.4.1 添加依赖](#341-%E6%B7%BB%E5%8A%A0%E4%BE%9D%E8%B5%96)
  * [3.4.2 添加权限](#342-%E6%B7%BB%E5%8A%A0%E6%9D%83%E9%99%90)
  * [3.4.3 添加组件](#343-%E6%B7%BB%E5%8A%A0%E7%BB%84%E4%BB%B6)
  * [3.4.4 添加混淆](#344-%E6%B7%BB%E5%8A%A0%E6%B7%B7%E6%B7%86)
- [4. 加载广告](#4-%E5%8A%A0%E8%BD%BD%E5%B9%BF%E5%91%8A)
  * [4.1 Banner位（Banner和信息流）](#41-banner%E4%BD%8Dbanner%E5%92%8C%E4%BF%A1%E6%81%AF%E6%B5%81)
  * [4.2 插屏](#42-%E6%8F%92%E5%B1%8F)
  * [4.3 激励视频](#43-%E6%BF%80%E5%8A%B1%E8%A7%86%E9%A2%91)
  * [4.4 开屏](#44-%E5%BC%80%E5%B1%8F)
  * [4.5 模板信息流](#45-%E6%A8%A1%E6%9D%BF%E4%BF%A1%E6%81%AF%E6%B5%81)
  * [4.6 全屏视频](#46-%E5%85%A8%E5%B1%8F%E8%A7%86%E9%A2%91)
  * [4.7 draw信息流](#47-draw%E4%BF%A1%E6%81%AF%E6%B5%81)
- [5. Glide图片加载框架](#5-glide%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E6%A1%86%E6%9E%B6)
  * [5.1 导入依赖](#51-%E5%AF%BC%E5%85%A5%E4%BE%9D%E8%B5%96)
  * [5.2 权限](#52-%E6%9D%83%E9%99%90)
  * [5.3 使用](#53-%E4%BD%BF%E7%94%A8)
- [6. 使用注意事项](#6-%E4%BD%BF%E7%94%A8%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
  * [6.1 常见问题](#61-%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)

<!-- tocstop -->

# 1. 支持的SDK平台及广告位

| SDK平台 | 开屏  | 激励视频 | 横幅  | 插屏(弹窗) | 模板信息流 | 全屏视频 | draw信息流 |
|-------|-----|------|-----|--------|-------|------|---------| 
| 穿山甲   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ✅       |
| 优量汇   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ❌       |
| 百青藤   | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ❌       |
| 快手    | ✅   | ✅    | ✅   | ✅      | ✅     | ✅    | ✅       |

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

因为亿帆SDK还没有使用AndroidX库，为了避免应用程序崩溃，请在项目的<font color="#00BFFF" size="3">
gradle.properties</font>中添加<br />
<font color="#00BFFF" size="3">android.enableJetifier=true</font>和<font color="#00BFFF" size="3">
android.useAndroidX=true</font>来打开Jetifier。

```
    dependencies {
        ...
        // yfsdk start
        implementation (name: 'yfsdk', ext: 'aar')
        implementation 'com.google.code.gson:gson:2.8.9'
        implementation 'com.github.bumptech.glide:glide:4.8.0'
        // yfsdk end
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
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />    <!--注意: 该权限不强制获取，即使没有获取也能正常运行；获取该权限将帮助优化投放广告精准度。-->
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

-keep class com.fc.ads.** { *; }
-keep class com.google.** { *; }
-dontwarn okio.**
-dontwarn com.ak.**
-dontwarn android.webkit.**
-dontwarn com.bytedance.sdk.**
-keep class android.** { *; }
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
        YFAdsConfig fcAdsConfig = new YFAdsConfig.FCAdsConfigBuilder(GlobalConst.APP_ID, "v1.0.0", "yfdemo")
                .setDebug(true)
                .builder();
        YFAdsManager.getInstance().init(this, fcAdsConfig);
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
| init(Application application, FCAdsConfig fcAdsConfig) | 初始化SDK                      |
| boolean isInit()                                       | 是否初始化                       |
| FCAdsConfig getFcAdsConfig()                           | 获取亿帆SDK的配置,详细参考FCAdsConfig类 |

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
| public YFAdsConfigBuilder setCanUseWriteExternal(boolean canUseWriteExternal)	 | 设置是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限。true可以使用，false禁止使用。默认为true                         |	
| public YFAdsConfigBuilder setCanUseOaid(boolean canUseOaid)	                   | 设置是否允许SDK主动获取OAID。true可以使用，false禁止使用。默认为true                                             |
| public YFAdsConfigBuilder setDevOaid(String devOaid)                           | 	传入OAID，当canUseOaid=false时，亿帆sdk使用您传入的OAID                                               |
| public YFAdsConfigBuilder setCanUseAppList(boolean canUseAppList)              | 设置是否允许SDK主动获取应用列表，判定广告对应的应用是否在用户的app上安装，避免投放错误的广告，以此提高用户的广告体验。true可以使用，false禁止使用。默认为true |
| public YFAdsConfigBuilder setCanUseAndroidId(boolean canUseAndroidId)          | 设置是否允许SDK主动使用AndroidId。true可以使用，false禁止使用。默认为true                                        |
| public YFAdsConfig builder()                                                   | 创建配置对象实例                                                                                 |

## 2.5 隐私开关

* 目前SDK已支持工信部隐私敏感权限要求</br>
* 敏感权限，用户同意则使用，若用户拒绝则不再获取</br>
* 申请权限界面由应用依据自身情况、用户真实选择，使用如下API更新隐私信息权限开关</br>
  <font color="red">注意</font>：目前OAID不属于个人信息保护法律范畴，建议提供获取的能力。</br>

**主要API**

**YFAdsConfig**

| 方法名                                                              | 方法介绍                                                                                     |
|:-----------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| public void setCanUseLocation(boolean canUseLocation)            | 设置是否允许SDK主动使用地理位置信息。true可以获取，false禁止获取。默认为true                                           |
| public void setYFLocation(YFLocation yfLocation)                 | 传入地理位置信息, 当canUseLocation=false时，亿帆sdk使用您传入的地理位置信息                                       |         
| public void setCanUsePhoneState(boolean canUsePhoneState)        | 设置是否允许SDK主动使用手机硬件参数，如：imei。true可以使用，false禁止使用。默认为true                                    |
| public void setDevImei(String devImei)                           | 可传入imei信息，当canUsePhoneState=false时，亿帆sdk使用您传入的imei信息                                     |
| public void setCanUseWifiState(boolean canUseWifiState)          | 设置是否允许SDK主动使用ACCESS_WIFI_STATE权限获取MAC信息。true可以使用，false禁止使用。默认为true                       |
| public void setCanUseWriteExternal(boolean canUseWriteExternal)	 | 设置是否允许SDK主动使用WRITE_EXTERNAL_STORAGE权限。true可以使用，false禁止使用。默认为true                         |	
| public void setCanUseOaid(boolean canUseOaid)	                   | 设置是否允许SDK主动获取OAID。true可以使用，false禁止使用。默认为true                                             |
| public void setDevOaid(String devOaid)                           | 	传入OAID，当canUseOaid=false时，亿帆sdk使用您传入的OAID                                               |
| public void setCanUseAppList(boolean canUseAppList)              | 设置是否允许SDK主动获取应用列表，判定广告对应的应用是否在用户的app上安装，避免投放错误的广告，以此提高用户的广告体验。true可以使用，false禁止使用。默认为true |
| public void setCanUseAndroidId(boolean canUseAndroidId)          | 设置是否允许SDK主动使用AndroidId。true可以使用，false禁止使用。默认为true                                        |

## 2.6 合规三步走

* 您需要确保App有[《隐私政策》]()并且在用户首次启动App时就弹出[《隐私政策》]()取得用户同意</br>
  </br>
* 您务必告知用户，您选择亿帆SDK服务；请在[《隐私政策》]()
  中增加如下参考条款：我们的产品集成亿帆SDK，亿帆SDK需要收集您的设备Mac地址、唯一设备识别码(
  IMEI/android ID)、SIM卡IMSI信息、地理位置信息以提供个性化推荐内容，提升用户体验。</br>
  </br>
* 您务必确保用户同意[《隐私政策》]()之后，再初始化亿帆SDK。</br>

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
  implementation(name: "open_ad_sdk_5512", ext: "aar")
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

亿帆SDK已经添加混淆，如有需要可单独接入

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
  implementation(name: "Baidu_MobAds_SDK-release_v9314", ext: "aar")
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

亿帆SDK已经添加混淆，如有需要可单独接入

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
  implementation(name: "kssdk-ad-33512", ext: "aar")
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

亿帆SDK已经添加混淆，如有需要可单独接入

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
  implementation(name: "GDTSDK_4540", ext: "aar")
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

亿帆SDK已经添加混淆，如有需要可单独接入

```
# GDT sdk start
-dontwarn com.qq.e.**
-keep class com.qq.e.** {*;}
-dontpreverify
# GDT sdk end
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
| void onAdFailed(FCAdError fcAdError); | 广告加载失败 |

**FCAdError**

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

具体可参见demo中FCBannerActivity

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
        public void onAdFailed(FCAdError fcAdError) {
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
        }
    };
}
    
public void loadAndShowAd(View view) {
    final YFAdBanner showAdBanner = new YFAdBanner(this, adView, listener);
    logAndToast("广告请求中");
    showAdBanner.toGetData(potId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            //如果集成穿山甲，这里必须配置，建议尺寸要和穿山甲后台中的"代码位尺寸"宽高比例一致，值单位为dp，这里示例使用的广告位宽高比为640：100。
            int adWidth = ScreenUtil.px2dip(AppUtils.getContext(), ScreenUtil.getScreenWidth(AppUtils.getContext()));
            int adHeight = (int) (((double) adWidth / (double) 640) * 100);
            //如果高度传入0代表自适应高度
            showADBanner.setViewAcceptedSize(adWidth, adHeight);
            Log.d("TEST", "设置数据");
            showADBanner.setData(jsonString);
            Log.d("TEST", "开始加载");
            showADBanner.loadAndShow();
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
| public void setData(String strategyJson)                                                     | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。                         |
| public void loadAndShow()                                                                    | 展示当前广告                                                             |

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
            easyInterstitial.setData(jsonString);
            easyInterstitial.loadAndShow();
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
        public void onAdFailed(FCAdError fcAdError) {
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
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
| public void setData(String strategyJson)                                      | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。                                                        |
| public void loadAndShow()                                                     | 展示当前广告                                                                                            |

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
        public void onAdFailed(FCAdError fcAdError) {
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
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
            logAndToast("onRewardServerInf" + inf);
        }
    };
    //初始化，注意需要时再初始化，不要复用。
    final YFAdRewardAds easyRewardVideo = new YFAdRewardAds(this, listener);
    easyRewardVideo.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyRewardVideo.setData(jsonString);
            easyRewardVideo.loadAndShow();
        }

        @Override
        public void onFailed(int errorCode, String message) {
        }
    });
}

```

**主要API**

**YFAdRewardAds**

| 方法名                                                                           | 方法介绍                                       |
|:------------------------------------------------------------------------------|:-------------------------------------------|
| public YFAdRewardAds(Activity activity, YFRewardVideoListener baseAdListener) | 激励视频广告位构造方法                                |
| public void toGetData(String adId, OnResultListener onResultListener)         | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。     |
| public void setData(String strategyJson)                                      | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。 |
| public void loadAndShow()                                                     | 展示当前广告                                     |

**YFRewardVideoListener**

| 方法名                                                    | 方法介绍                                                   |
|:-------------------------------------------------------|:-------------------------------------------------------|
| void onVideoCached();                                  | 广告缓存成功                                                 |
| void onVideoComplete();                                | 视频播放完毕                                                 |
| void onVideoSkip();                                    | 跳过视频                                                   |
| void onAdReward();                                     | 激励发放                                                   |
| void onRewardServerInf(YFRewardServerCallBackInf inf); | 激励视频返回的服务器回调信息，穿山甲一直支持，优量汇自v4.330.1200 开始支持,百度9.13开始支持 |

## 4.4 开屏

具体可参见demo中SplashActivity

**注意：** </br>
**1. 展示开屏广告的容器状态必须为可见，否则广告通不返回广告。**</br>
**2. 开屏广告容器高度必须大于等于屏幕高度的75%，否则广告通广告会出现一闪而过现象。**

<font color="red">
重要1：开屏页请设置为全屏样式，并隐藏虚拟按键和状态栏，防止跳过按钮和广告标识被遮挡</font>

```
private void setFullScreen() {
  if (Build.VERSION.SDK_INT >= 30) {
      //Android11 适配
      /**
       * 如果编译的sdk是30以上，直接使用如下代码。
       */
//            getWindow().getDecorView().getWindowInsetsController().hide(
//                    WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());

      /**
       * 如果编译的sdk小于30，使用如下反射的方式。
       *
       * 由于Android版本限制，可能会遇到错误(建议还是使用大于30的sdk编译)：
       * Error: Reflective access to getWindowInsetsController is forbidden when targeting API 29 and above [BlockedPrivateApi]
       *  Explanation for issues of type "BlockedPrivateApi":
       *    Usage of restricted non-SDK interface is forbidden for this targetSDK.
       *    Accessing non-SDK methods or fields through reflection has a high
       *    likelihood to break your app between versions, and is being restricted to
       *    facilitate future app compatibility.
       */
      try {
          View decorView = getWindow().getDecorView();
          Method getWindowInsetsController = View.class.getDeclaredMethod("getWindowInsetsController");
          getWindowInsetsController.setAccessible(true);
          Object insetsController = getWindowInsetsController.invoke(decorView);
          Class<?> windowInsetsController = Class.forName("android.view.WindowInsetsController");
          Method hide = windowInsetsController.getMethod("hide", int.class);
          hide.setAccessible(true);
          hide.invoke(insetsController, 3);
      } catch (Throwable e) {
          e.printStackTrace();
      }
  } else {
      // Note that some of these constants are new as of API 16 (Jelly Bean)
      // and API 19 (KitKat). It is safe to use them, as they are inlined
      // at compile-time and do nothing on earlier devices.
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
              | View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
  }
}
```

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
    setFullScreen();
    type = getIntent().getIntExtra("type", GlobalConst.ERROR_NUM);
    String potId = getIntent().getStringExtra("potId");

    adContainer = findViewById(R.id.splash_container);
    adLogoLly = findViewById(R.id.ll_logo);

    if (!TextUtils.isEmpty(potId)) {
        loadSplash(adContainer, potId);
    }
}

/* *
 * 加载开屏广告，开屏推荐使用加载并展示开屏广告方式，所有的广告均支持请求和展示分离，如有必要，可分别调用加载广告和展示广告，可参考"插屏广告"中的处理示例。
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
    fcAdSplash.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            //必须：设置策略信息
            fcAdSplash.setData(jsonString);
            //必须：请求并展示广告
            fcAdSplash.loadAndShow();
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
        public void onAdFailed(FCAdError fcAdError) {
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
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

| 方法名                                                                                             | 方法介绍                                       |
|:------------------------------------------------------------------------------------------------|:-------------------------------------------|
| public YFAdSplashAds(Activity activity, ViewGroup adContainer, YFSplashListener baseAdListener) | 开屏广告位构造方法                                  |
| public void toGetData(String adId, OnResultListener onResultListener)                           | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。     |
| public void setData(String strategyJson)                                                        | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。 |
| public void loadAndShow()                                                                       | 展示当前广告                                     |

**YFSplashListener** 同 **BaseAdListener**回调监听

## 4.5 模板信息流

具体可参见demo中NativeExpressRecyclerViewActivity

**接入代码示例**

```
private void loadNativeExpress(String adId, ViewGroup adContainer) {

    if (hasNativeShow) {//同一位置广告，已展示过不再重复发起请求
        Log.d("TEST", "loadNativeExpress hasNativeShow");
        return;
    }

    if (isNativeLoading) {//同一位置广告，正在请求中，不再重复请求
        Log.d("TEST", "loadNativeExpress isNativeLoading");
        return;
    }
    isNativeLoading = true;

    if (adContainer.getChildCount() > 0) {
        adContainer.removeAllViews();
    }


    //推荐：核心事件监听回调
    YFNativeExpressListener listener = new YFNativeExpressListener() {

        @Override
        public void onAdRenderSuccess() {
            logAndToast("广告渲染成功");

        }

        @Override
        public void onAdSuccess() {
            logAndToast("广告加载成功");
        }

        @Override
        public void onAdExposure() {
            hasNativeShow = true;
            isNativeLoading = false;
            logAndToast("广告展示");
        }

        @Override
        public void onAdRenderFailed() {
            isNativeLoading = false;
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
        public void onAdFailed(FCAdError fcAdError) {
            isNativeLoading = false;
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
        }

    };
    //初始化
    final YFAdNativeExpressAds easyNativeExpress = new YFAdNativeExpressAds(mActivity, listener);
    easyNativeExpress.setAdContainer(adContainer);
    logAndToast("广告请求中");
    //必须：设置策略信息
    easyNativeExpress.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyNativeExpress.setData(jsonString);
            easyNativeExpress.loadAndShow();
        }

        @Override
        public void onFailed(int errorCode, String message) {

        }
    });
}

```

**主要API**

**YFAdNativeExpressAds**

| 方法名                                                                              | 方法介绍                                       |
|:---------------------------------------------------------------------------------|:-------------------------------------------|
| public YFAdNativeExpressAds(Activity activity, YFNativeExpressListener listener) | 广告位构造方法                                    |
| public void toGetData(String adId, OnResultListener onResultListener)            | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。     |
| public void setData(String strategyJson)                                         | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。 |
| public void setAdContainer(ViewGroup container)                                  | 设置用来展示广告的父布局                               |
| public void loadAndShow()                                                        | 展示当前广告                                     |
| public void destroy()                                                            | 资源释放                                       |

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
        public void onAdFailed(FCAdError fcAdError) {
            logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
        }
    };
    //初始化
    YFAdFullScreenVideoAds easyFullScreenVideo = new YFAdFullScreenVideoAds(this, listener);
    easyFullScreenVideo.toGetData(adId, new OnResultListener() {
        @Override
        public void onSuccess(String jsonString) {
            easyFullScreenVideo.setData(jsonString);
            easyFullScreenVideo.loadAndShow();
        }

        @Override
        public void onFailed(int errorCode, String message) {
        }
    });
}

```

**主要API**

**YFAdFullScreenVideoAds**

| 方法名                                                                                        | 方法介绍                                       |
|:-------------------------------------------------------------------------------------------|:-------------------------------------------|
| public YFAdFullScreenVideoAds(Activity activity, YFFullScreenVideoListener baseAdListener) | 广告位构造方法                                    |
| public void toGetData(String adId, OnResultListener onResultListener)                      | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。     |
| public void setData(String strategyJson)                                                   | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。 |
| public void loadAndShow()                                                                  | 展示当前广告                                     |

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
    public void onAdFailed(FCAdError fcAdError) {
        logAndToast("广告加载失败 code=" + fcAdError.code + " msg=" + fcAdError.msg);
    }


};
YFAdDrawAds easyAdDraw = new YFAdDrawAds(mContext, listener);
easyAdDraw.setAdContainer(adContainer);
logAndToast("广告请求中");
//必须：设置策略信息
easyAdDraw.toGetData(adId, new OnResultListener() {
    @Override
    public void onSuccess(String jsonString) {
        easyAdDraw.setData(jsonString);
        //必须：请求并展示广告
        easyAdDraw.loadAndShow();
    }

    @Override
    public void onFailed(int errorCode, String message) {

    }
});
}

```

**主要API**

**YFAdDrawAds**

| 方法名                                                                   | 方法介绍                                       |
|:----------------------------------------------------------------------|:-------------------------------------------|
| public YFAdDrawAds(Activity activity, YFDrawListener listener)        | 广告位构造方法                                    |
| public void setAdContainer(final ViewGroup adContainer)               | 设置展示的布局                                    |
| public void toGetData(String adId, OnResultListener onResultListener) | 配置信息从缓存中读取，如果存在未过期，则缓存返回，否者请求网络，成功后回调。     |
| public void setData(String strategyJson)                              | 必须！！设置策略信息，注意json格式一定要正确，否则有可能解析策略失败导致无广告。 |
| public void loadAndShow()                                             | 展示当前广告                                     |

**YFDrawListener** 同 **BaseAdListener**

# 5. Glide图片加载框架

如果接入方没有图片加载框架，亿帆自渲染图片加载，推荐继承Glide，具体接入如下：

## 5.1 导入依赖

```
dependencies {
  implementation 'com.github.bumptech.glide:glide:4.8.0'
}
```

## 5.2 权限

```
<uses-permission android:name="android.permission.INTERNET" />
```

## 5.3 使用

其中with后面的上下文可以是：Activity、View Context、Application，为了确保内存性能，不推荐使用Application

```
Glide.with(Activity).load(url).into(view);
```

详细的使用参考：[Glide官网](https://muyangmin.github.io/glide-docs-cn/)

# 6. 使用注意事项

## 6.1 常见问题

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