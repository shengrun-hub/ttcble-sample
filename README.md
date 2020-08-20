# ttcble-sample

本示例展示了通过添加Maven依赖，使用内置的UI，快速的实现BLE扫描连接，并与我司蓝牙透传模块交互数据，更多更详细的功能可参考[TTC_BLE_DEMO-Kotlin](https://github.com/shengrun-hub/TTC_BLE_DEMO-Kotlin)。

系统要求：不低于Android5.0。

# Android Studio添加依赖：

1. 工程根目录下的build.gradle中添加

```
allprojects {
    repositories {
        ...
        maven { url 'https://dl.bintray.com/android-ttcble/maven' }
    }
}
```

2. 在引用的module的build.gradle中添加

```
dependencies {
    ...
    implementation 'com.ttcble.android:blebase:1.1.7'
}
```
