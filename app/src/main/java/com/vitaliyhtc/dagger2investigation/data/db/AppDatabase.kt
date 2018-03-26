package com.vitaliyhtc.dagger2investigation.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vitaliyhtc.dagger2investigation.domain.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}