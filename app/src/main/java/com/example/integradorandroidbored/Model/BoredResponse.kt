package com.example.integradorandroidbored.Model

import java.io.Serializable

data class BoredResponse(
    val activity: String?,
    val type: String?,
    val participants: Int?,
    val price: String?
): Serializable{
}