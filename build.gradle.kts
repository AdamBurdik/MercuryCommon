plugins {
    id("java")
}

group = "me.adamix.mercury.common"
version = "0.1.1"

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
    compileOnly("com.github.AdamBurdik:MercuryAPI:e176dd7602")
    implementation("org.tomlj:tomlj:1.1.1")

    implementation("org.jetbrains:annotations:26.0.2")
}