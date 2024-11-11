package com.abc.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    /*
     * Using the suspend keyword to let it run on a separate thread.
     * Room doesn't allow database access on the main thread.
     *
     * When inserting items into the database, conflicts can happen. For example, multiple places in
     * the code tries to update the entity with different, conflicting, values such as the same
     * primary key. In the Inventory app, we only insert the entity from one place that is the Add
     * Item screen so we are not expecting any conflicts and can set the conflict strategy to Ignore.
     *
     * The argument onConflict tells the Room what to do in case of a conflict.
     * The OnConflictStrategy.IGNORE strategy ignores a new item.
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)


    /*
     * It is recommended to use Flow in the persistence layer. With Flow as the return type, you
     * receive notification whenever the data in the database changes. The Room keeps this Flow
     * updated for you, which means you only need to explicitly get the data once.
     * This setup is helpful to update the inventory list. Because of the Flow return type, Room
     * also runs the query on the background thread. You don't need to explicitly make it a suspend
     * function and call it inside a coroutine scope.
     *
     * Flow in Room database can keep the data up-to-date by emitting a notification whenever the data
     * in the database changes. This allows you to observe the data and update your UI accordingly.
     */

    @Query("SELECT * FROM item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}