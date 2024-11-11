package com.abc.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "item")
data class Item(
    // We assign a default value of 0 and autoGenerate = true to autogenerate a primary key.
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
