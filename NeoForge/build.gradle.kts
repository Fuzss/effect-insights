plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-neoforge")
}

dependencies {
    modApi(sharedLibs.puzzleslib.neoforge)
    modApi(sharedLibs.tooltipinsights.neoforge)
    include(sharedLibs.tooltipinsights.neoforge)
}
