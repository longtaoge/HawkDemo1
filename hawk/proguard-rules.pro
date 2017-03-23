# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/longtaoge/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-ignorewarnings
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontpreverify
-verbose
-dontnote
-dontskipnonpubliclibraryclasses

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize

-dontpreverify
-dontwarn

-libraryjars <java.home>/lib/rt.jar
-libraryjars  /Users/longtaoge/Library/Android/sdk/platforms/android-22/android.jar

-keepclasseswithmembers class com.orhanobut.hawk.AesEncryption{*;}
-keepclasseswithmembers class com.orhanobut.hawk.Base64Encryption{*;}
-keepclasseswithmembers class com.orhanobut.hawk.DataHelper{*;}
-keepclasseswithmembers class com.orhanobut.hawk.DataInfo{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.DataType{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.Encoder{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.Encryption{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.GsonParser
-keepclasseswithmembers class com.orhanobut.hawk.Hawk{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.HawkBuilder{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.HawkEncoder{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.HawkUtils{ *; }
-keepclasseswithmembers class  com.orhanobut.hawk.JsonEncryption{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.LogLevel{ *; }
#-keepclasseswithmembers class com.orhanobut.hawk.Logger{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.OnDataChangedListener{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.Parser{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.SharedPreferencesStorage{ *; }
-keepclasseswithmembers class  com.orhanobut.hawk.SqliteStorage{ *; }
-keepclasseswithmembers class com.orhanobut.hawk.Storage{ *; }
