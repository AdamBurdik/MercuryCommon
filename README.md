# MercuryCommon
**MercuryCommon** is a lightweight library with useful implementations for [MercuryAPI](https://github.com/AdamBurdik/MercuryAPI/).

# Installation
### Gradle (Kotlin DSL)
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.AdamBurdik.MercuryCommon:<commit>")
    // Example: implementation("com.github.AdamBurdik.MercuryCommon:-SNAPSHOT") = Newest commit
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.AdamBurdik</groupId>
        <artifactId>MercuryCommon</artifactId>
        <version>-SNAPSHOT</version>
    </dependency>
</dependencies>
```
- [Or check it out on jitpack](https://jitpack.io/#AdamBurdik/MercuryAPI)