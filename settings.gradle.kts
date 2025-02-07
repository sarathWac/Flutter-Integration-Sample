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
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven("https://storage.googleapis.com/download.flutter.io")
    }
}

rootProject.name = "Flutter Module Integration"
include(":app")

//include flutter module
//replace 'flutter_module' with name of the module we ant to integrate
val moduleDir = settingsDir.parentFile.listFiles()?.firstOrNull { it.isDirectory }
val filePath = "${moduleDir?.absolutePath}/flutter_module/.android/include_flutter.groovy"

val file = File(filePath)
apply(from = file)