package com.vitaliyhtc.dagger2investigation.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import io.reactivex.Single

@Dao
interface ProductDao {

    @Query("SELECT * from products")
    fun getAll(): Single<List<Product>>

    @Query("SELECT * from products")
    fun getAllListener(): LiveData<List<Product>>

    @Query("SELECT * from products where id = :id")
    fun getProductById(id: Int): Single<Product>

    @Insert(onConflict = REPLACE)
    fun insertAll(products: List<Product>)

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("DELETE from products")
    fun deleteAll()


}