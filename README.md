# Jetpack Compose Utils

This library contains views that are missing from the core [Jetpack Compose][1] libraries.

The views available in this library are as follows:

|                   |                                                  |
|-------------------|--------------------------------------------------|
| [BulletedList][2] | Displays an array of Strings as a bulleted list. |

## Setup

To use the above views within your app,
simply add the following repository and dependency declaration in the `build.gradle` file of your Android project:

```groovy
repositories {
    mavenCentral()
}
dependencies {
    implementation 'com.tazkiyatech:jetpack-compose-utils:0.1.0'
}
```

[1]: https://developer.android.com/jetpack/compose
[2]: library/src/main/java/com/tazkiyatech/compose/utils/BulletedList.kt
