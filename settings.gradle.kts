pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven(url="https://android-sdk.is.com")
        maven(url = "https://jitpack.io")


        gradlePluginPortal()

        google()
    }

}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
       jcenter()
        maven(url = "https://jitpack.io")
        maven(url="https://android-sdk.is.com")

    }



}
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//       // classpath ("com.google.gms:google-services:4.4.2")
//       // classpath("com.android.tools.build:gradle:8.4.2") // Replace with the correct version
//    }
//}

rootProject.name = "SuShiv"
include(":app")
