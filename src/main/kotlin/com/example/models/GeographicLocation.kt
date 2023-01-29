package com.example.models

import kotlinx.serialization.Serializable

/**
 * A geographic location at latitude [lat] and longitude [lng]
 */
@Serializable
data class GeographicLocation(
    val lat: String? = null,
    val lng: String? = null,
)
