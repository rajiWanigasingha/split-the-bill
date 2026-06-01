plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.system"
version = "1.0.0"
application {
    mainClass = "com.system.ApplicationKt"
}

dependencies {
    api(projects.core)

    implementation(platform(libs.koin.bom))

    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation("io.ktor:ktor-server-resources:3.5.0")
    implementation("io.ktor:ktor-server-content-negotiation:3.5.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.5.0")
    implementation("com.resend:resend-java:v4.14.1")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)

    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}