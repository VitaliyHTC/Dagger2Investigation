package com.vitaliyhtc.dagger2investigation.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "products")
data class Product(@PrimaryKey val id: Int,
                   @ColumnInfo(name = "name") val name: String,
                   @ColumnInfo(name = "tags") val tags: String,
                   @ColumnInfo(name = "price_in_cents") val price_in_cents: Int,
                   @ColumnInfo(name = "regular_price_in_cents") val regular_price_in_cents: Int,
                   @ColumnInfo(name = "description") val description: String?,
                   @ColumnInfo(name = "image_thumb_url") val image_thumb_url: String?,
                   @ColumnInfo(name = "image_url") val image_url: String?,
                   @ColumnInfo(name = "is_favorite") val is_favorite: Boolean)