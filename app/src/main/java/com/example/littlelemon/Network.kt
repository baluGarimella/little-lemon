package com.example.littlelemon

import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData (

    val menu: List<MenuItemNetwork>
        )

@Serializable
data class MenuItemNetwork (
    val id : Int,
    val title : String,
    val description : String,
    val price : Double,
    val category : String

        )