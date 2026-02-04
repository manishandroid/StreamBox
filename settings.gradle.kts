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

rootProject.name = "StreamBox"

include(":app")
include(":core:architecture")
include(":core:designsystem")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":features:home")
include(":features:home:snapshot-test")
