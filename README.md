# MultiVersionApp
Build multi-version App with Gradle

## Configure Build Types

```
android {

    ...

    signingConfigs {
        debug {
            // default
        }
        release {
            storeFile file("xx/yyyy.keystore")
            storePassword "password"
            keyAlias "alias"
            keyPassword "password"
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix ".debug"
            debuggable true
            //signingConfig signingConfigs.debug
            resValue "string", "runMode", "Test"
            buildConfigField "boolean", "LOG_DEBUG", "true"

            manifestPlaceholders = [
                CUSTOM_META_DATA_VALUE: "custom_value_debug"
            ]
        }

        release {
            minifyEnabled true
            signingConfig signingConfigs.release
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            shrinkResources true
            resValue "string", "runMode", "PRODUCTION"
            buildConfigField "boolean", "LOG_DEBUG", "false"

            manifestPlaceholders = [
                CUSTOM_META_DATA_VALUE: "custom_value_release"
            ]
        }
    }

    ...

}
```

## Configure productFlavors
```
    productFlavors{
        Test{
            versionName "$defaultConfig.versionName" + ".test"
            resValue "string", "app_name", "App-Test"
            manifestPlaceholders = [
                    appIcon: "@mipmap/ic_launcher_test"
            ]
        }

        Product{
            resValue "string", "app_name", "App"
            manifestPlaceholders = [
                    appIcon: "@mipmap/ic_launcher"
            ]
        }
    }
```


## BuildConfig.java
```java
  public static final boolean DEBUG = Boolean.parseBoolean("true");
  public static final String APPLICATION_ID = "com.github.captain_miao.multiversionapp.debug";
  public static final String BUILD_TYPE = "debug";
  public static final String FLAVOR = "ForTest";
  public static final int VERSION_CODE = 120;
  public static final String VERSION_NAME = "1.2.0";
  // Fields from build type: debug
  public static final boolean isPrintLog = true;
```

## Build only a flavour(assemble + productFlavors + buildTypes)
```
./gradlew assembleProductDebug
```
## Build all apk
```
./gradlew assemble
//or 
./gradlew build
```