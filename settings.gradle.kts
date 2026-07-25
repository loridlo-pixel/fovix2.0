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
    ":diagnostics",
    ":feature_home",
    ":feature_servers",
    ":core-config",
    ":feature_settings",
    ":feature_stats"
)
