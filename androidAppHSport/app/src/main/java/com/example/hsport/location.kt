package com.example.hsport

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.kharidino.R
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay





@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun location(navController: NavHostController) {
    val context = LocalContext.current
    val mapView = MapView(context)

    Configuration.getInstance().userAgentValue = context.packageName
    mapView.setTileSource(TileSourceFactory.MAPNIK)
    mapView.isTilesScaledToDpi = true
    mapView.setMultiTouchControls(true)
    mapView.setBackgroundColor(context.resources.getColor(R.color.white))

    val azadiSquareLocation = GeoPoint(36.33893547006768, 59.48830915478454)
    mapView.controller.animateTo(azadiSquareLocation)
    mapView.controller.setZoom(16.0)

    // ایجاد یک Drawable برای نمایش ماشین و تغییر اندازه آن
    val carDrawable = context.resources.getDrawable(R.drawable.litelcar).mutate()
    carDrawable.setBounds(0, 0, 20, 20) // تغییر اندازه Drawable
    val carOverlayItem = OverlayItem("Car", "A car", azadiSquareLocation)
    carOverlayItem.setMarker(carDrawable)

    // اضافه کردن OverlayItem به نقشه
    val items = ArrayList<OverlayItem>()
    items.add(carOverlayItem)
    val itemizedOverlayWithFocus = ItemizedOverlayWithFocus<OverlayItem>(items, object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem> {
        override fun onItemSingleTapUp(index: Int, item: OverlayItem): Boolean {
            return true
        }

        override fun onItemLongPress(index: Int, item: OverlayItem): Boolean {
            return false
        }
    }, context)
    itemizedOverlayWithFocus.setFocusItemsOnTap(true)
    mapView.overlays.add(itemizedOverlayWithFocus)

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView({ mapView }) { mapView ->
            Configuration.getInstance().userAgentValue = context.packageName
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.invalidate()
        }
    }
}






