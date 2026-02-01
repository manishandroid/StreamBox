// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kover) apply false
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }
    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = "org.jetbrains.kotlinx.kover")
    }
}

subprojects {
    plugins.withId("org.jetbrains.kotlinx.kover") {
        extensions.configure(kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension::class.java) {
            reports {
                filters {
                    excludes {
                        annotatedBy(
                            "*Generated*",
                            "*Module*",
                            "*DaggerGenerated*",
                            "*Composable",
                            "*Preview*",
                            "*IgnoreKover*",
                            "*Serializable*",
                            "*Type*"
                        )
                        classes(
                            "*/R.class",
                            "*/R$*.class",
                            "*BuildConfig*",
                            "*/Manifest*.*",
                            "hilt_aggregated_deps.*",
                            "dagger.hilt.*",
                            "*.Hilt_*",
                            "*_Factory",
                            "*_Factory$*",
                            "*_*Factory",
                            "*_*Factory$*",
                            "*_Impl",
                            "*_Impl$*",
                            "*ComposableSingletons*",
                            "*.databinding.*Binding"
                        )
                        packages(
                            "com.imandroid.streambox.features.home.data.di",
                            "com.imandroid.streambox.features.home.domain.di",
                            "com.imandroid.streambox.features.home.ui.di"
                        )
                    }
                }
            }
        }
    }
}
