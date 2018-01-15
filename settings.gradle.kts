pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.spring.io/snapshot")
        maven("https://repo.spring.io/milestone")
        maven("https://jcenter.bintray.com/")
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "org.junit.platform.gradle.plugin") {
                useModule("org.junit.platform:junit-platform-gradle-plugin:${requested.version}")
            }
        }
    }
}

include("shared", "word-count", "product", "raw-word-count", "classifier")
