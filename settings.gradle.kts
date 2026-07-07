pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Fovix"

include(
    ":app",
    ":core",
    ":data",
    ":domain",
    ":vpn",
    ":feature_home",
    ":feature_servers",
    ":feature_settings",
    ":feature_stats"
)

include(":core")
