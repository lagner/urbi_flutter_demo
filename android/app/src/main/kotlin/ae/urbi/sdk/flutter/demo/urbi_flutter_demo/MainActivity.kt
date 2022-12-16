package ae.urbi.sdk.flutter.demo.urbi_flutter_demo

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import ru.dgis.sdk.Context
import ru.dgis.sdk.DGis

class MainActivity: FlutterActivity() {

    lateinit var channel: MethodChannel

    private val sdkContext: Context by lazy {
        DGis.initialize(appContext = context.applicationContext)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        channel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "ae.urbi.sdk.flutter.map-channel")

        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory("ae.urbi.sdk.flutter.map", UrbiMapNativeViewFactory(sdkContext, channel))

    }
}
