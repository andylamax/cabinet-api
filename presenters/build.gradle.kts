plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

kotlin {
    jvm { library() }
    js(IR) { library() }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.cabinetApiCore)
                api(libs.cinematic.scene.core)
                api(libs.koncurrent.later.coroutines)?.because("We need to launch a coroutines scope and fetch an authenticated url")
                api(ktor.client.core)
                api(libs.epsilon.fields)
                api(libs.klip.api)
                api(libs.bringer.api)
            }
        }

        val commonTest by getting {
            dependencies {
                api(libs.kommander.coroutines)
            }
        }
    }
}
