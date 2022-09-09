package com.example.integradorandroidbored.Model

/**
 * Represents a recreational activity with number of participants, activity name, price & category
 * @author Aponte, Pineda & Tolaba
 */
data class BoredResponse(
    val activity: String = "",
    val type: String = "",
    val participants: Int = 0,
    val price: String = ""
){
}