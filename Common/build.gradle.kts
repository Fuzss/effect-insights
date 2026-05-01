plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(sharedLibs.puzzleslib.common)
    modCompileOnlyApi(sharedLibs.tooltipinsights.common)
}

multiloader {
    mixins {
        clientMixin("BeaconScreen\$BeaconPowerButtonMixin")
    }
}
