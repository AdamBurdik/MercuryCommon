plugins {
    id("java")
}

group = "me.adamix.mercury.common"
version = "0.1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("com.github.AdamBurdik:MercuryAPI:178f248fd2")
    implementation("org.tomlj:tomlj:1.1.1")

    implementation("org.jetbrains:annotations:26.0.2")
}