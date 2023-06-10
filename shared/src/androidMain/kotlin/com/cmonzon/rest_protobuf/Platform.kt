package com.cmonzon.rest_protobuf

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getEmulatorLocalHost(): String = "http://10.0.2.2:8080/"