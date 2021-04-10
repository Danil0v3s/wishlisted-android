package app.wishlisted.android.data.src.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import app.wishlisted.android.data.src.model.GameDTO
import app.wishlisted.android.data.src.model.StatusGameCrossRef

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStatusGameCrossRef(games: List<StatusGameCrossRef>)

    @Transaction
    @Query(
        """
        SELECT * FROM tb_games game WHERE game.productId IN (
            SELECT productId FROM StatusGameCrossRef ref WHERE ref.statusId = :statusId
        )
    """
    )
    fun gamesByStatus(statusId: Int): PagingSource<Int, GameDTO>
}
