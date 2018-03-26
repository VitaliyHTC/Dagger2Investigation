package com.vitaliyhtc.dagger2investigation.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single

@Dao
interface ProductDao {

    @Query("SELECT * from products")
    fun getAll(): Single<List<Product>>

    @Query("SELECT * from products where id = :id")
    fun getProductById(id: Int): Single<Product>

    @Insert(onConflict = REPLACE)
    fun insertAll(products: List<Product>)

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("DELETE from products")
    fun deleteAll()


}