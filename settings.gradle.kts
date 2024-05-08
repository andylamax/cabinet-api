import java.io.File

pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
    "epsilon-api", "kollections", "koncurrent", "kommander",
    "cinematic", "epsilon-api", "epsilon-client", "klip", "bringer"
).forEach { includeBuild("../$it") }

rootProject.name = "cabinet-api"

includeSubs("cabinet-api", ".", "core", "fake", "presenters")
