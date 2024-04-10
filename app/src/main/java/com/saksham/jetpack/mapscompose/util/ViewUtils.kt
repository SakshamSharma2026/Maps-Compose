package com.saksham.jetpack.mapscompose.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.Locale


fun getAddressFromLatLng(context: Context, latitude: Double?, longitude: Double?): String {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses: List<Address>? = geocoder.getFromLocation(latitude ?: 0.0, longitude ?: 0.0, 1)
    return addresses?.firstOrNull()?.getAddressLine(0) ?: ""
}