plugins {
    `kotlin-dsl`
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("org.jetbrains.dokka") version "1.8.20" apply false
}

java {
    withSourcesJar()
    withJavadocJar()
}