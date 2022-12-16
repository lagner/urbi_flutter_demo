package ae.urbi.sdk.flutter.demo.urbi_flutter_demo

import android.content.Context
import android.util.Log
import android.view.View
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import ru.dgis.sdk.Duration
import ru.dgis.sdk.LogLevel
import ru.dgis.sdk.coordinates.GeoPoint
import ru.dgis.sdk.map.*

internal class UrbiMapNativeView(
    context: Context,
    id: Int,
    creationParams: Map<String?, Any?>?
): PlatformView, MethodChannel.MethodCallHandler {
    private val mapView: MapView
    private var mapModel: ru.dgis.sdk.map.Map? = null

    override fun getView(): View {
        return mapView
    }

    override fun dispose() {
    }

    init {
        val options = MapOptions().apply {
            position = CameraPosition(
                point = GeoPoint(25.204575, 55.25939),
                zoom = Zoom(11.0f)
            )
        }

        mapView = MapView(context, options).apply {
            getMapAsync {
                UrbiMapNativeView@mapModel = it
            }
        }
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "move" -> {
                mapModel?.let {
                    val pos = it.camera.position
                    Log.i("kotlin", "change position from: lat ${pos.point.latitude}, lon ${pos.point.longitude}, zoom ${pos.zoom.value}, tilt ${pos.tilt.value}")

                    it.camera.move(
                        position = CameraPosition(
                            point = GeoPoint(   25.197401799995404, 55.274996906518936),
                            tilt = Tilt(60f),
                            zoom = Zoom(17.0f),
                        ),
                        time = Duration.ofSeconds(3)
                    )
                }
                result.success(true)
            }
        }
    }
}