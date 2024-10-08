plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    signing
}

android {
    compileSdk = 34
    namespace = "com.tazkiyatech.compose.utils"

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    lint {
        abortOnError = true
    }

    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {
    // NOTE: we're adding a test library as a non-test dependency because that's a requirement of the library
    debugImplementation(libs.compose.ui.testmanifest)
    debugImplementation(libs.compose.ui.tooling)

    implementation(platform(libs.compose.bom))

    implementation(libs.compose.material3)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.toolingpreview)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            groupId = properties["groupId"].toString()
            artifactId = properties["artifactId"].toString()
            version = properties["version"].toString()

            pom {
                name = project.properties["artifactId"].toString()
                description = "An Android library containing views for Jetpack Compose."
                url = "https://github.com/TazkiyaTech/jetpack-compose-utils"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                organization {
                    name = "Tazkiya Tech"
                    url = "http://tazkiyatech.com"
                }
                developers {
                    developer {
                        id = "adil-hussain-84"
                        name = "Adil Hussain"
                        email = "adilson05uk@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/TazkiyaTech/jetpack-compose-utils.git"
                    developerConnection = "scm:git:ssh://github.com:TazkiyaTech/jetpack-compose-utils.git"
                    url = "https://github.com/TazkiyaTech/jetpack-compose-utils"
                }
            }
        }
    }

    repositories {
        maven {
            name = "sonatype"
            credentials(PasswordCredentials::class)
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
        }
    }
}

signing {
    // the "signing.keyId", "signing.password" and "signing.secretKeyRingFile" properties required by this task are defined outside of this project in the "~/.gradle/gradle.properties" file
    sign(publishing.publications["release"])
}
