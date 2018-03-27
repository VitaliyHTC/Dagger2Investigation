package com.vitaliyhtc.dagger2investigation.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import android.arch.persistence.db.SupportSQLiteDatabase



@Database(entities = [Product::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `products` ADD `is_favorite` BOOLEAN")
            }
        }
    }

    abstract fun productDao(): ProductDao
}