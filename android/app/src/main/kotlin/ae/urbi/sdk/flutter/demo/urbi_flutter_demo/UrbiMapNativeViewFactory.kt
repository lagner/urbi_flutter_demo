package ae.urbi.sdk.flutter.demo.urbi_flutter_demo

import android.content.Context
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class UrbiMapNativeViewFactory(
    private val sdkContext: ru.dgis.sdk.Context,
    private val methodChannel: MethodChannel
): PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        return UrbiMapNativeView(context, viewId, creationParams).also {
            methodChannel.setMethodCallHandler(it)
        }
    }
}