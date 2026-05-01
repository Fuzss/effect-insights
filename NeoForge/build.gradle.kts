plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modCompileOnly(sharedLibs.puzzleslib.common)
    modApi(sharedLibs.puzzleslib.neoforge)
    modCompileOnly(sharedLibs.tooltipinsights.common)
    modApi(sharedLibs.tooltipinsights.neoforge)
    include(sharedLibs.tooltipinsights.neoforge)
}
