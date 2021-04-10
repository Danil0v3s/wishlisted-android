package app.wishlisted.android.data.src.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.wishlisted.android.data.src.model.GameStatusRemoteKey

@Dao
interface GameStatusRemoteKeyDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(remoteKey: GameStatusRemoteKey)

	@Query("SELECT * FROM GameStatusRemoteKey WHERE statusId = :statusId")
	suspend fun remoteKeyByStatus(statusId: Int): GameStatusRemoteKey

	@Query("DELETE FROM GameStatusRemoteKey WHERE statusId = :statusId")
	suspend fun deleteKeyByStatus(statusId: Int)
}