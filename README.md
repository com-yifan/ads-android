# 亿帆SDK-Android 快速指引

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

