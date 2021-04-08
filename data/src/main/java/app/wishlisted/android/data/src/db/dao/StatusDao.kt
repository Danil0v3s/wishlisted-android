package app.wishlisted.android.data.src.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.wishlisted.android.data.src.model.StatusDTO

@Dao
interface StatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(status: List<StatusDTO>)

    @Query("SELECT * FROM tb_status")
    suspend fun fetchAll(): List<StatusDTO>
}
