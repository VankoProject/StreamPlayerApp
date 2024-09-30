plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(libs.junit)
    testImplementation (libs.kotlinx.coroutines.test)

    //Javax annotation
    implementation(group = "javax.inject", name = "javax.inject", version = "1")
}