package br.com.firstsoft.historiconotificacoes.data.src.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.firstsoft.historiconotificacoes.data.src.models.AppNotificationDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    // @Query("SELECT * from notification GROUP BY packageName")
    // fun fetchAllByPackages(): DataSource.Factory<Int, AppNotificationDTO>
    //
    // @Query("SELECT COUNT(*) FROM notification n WHERE packageName = :packageName")
    // fun countNotificationsByPackage(packageName: String): Int
    //
    @Query("SELECT * from notification ORDER BY createdTime DESC")
    fun fetchAll(): PagingSource<Int, AppNotificationDTO>
    //
    // @Query("SELECT * FROM notification WHERE packageName = :packageName ORDER BY createdTime DESC")
    // fun fetchNotifications(packageName: String): DataSource.Factory<Int, AppNotificationDTO>
    //
    // @Query("SELECT * FROM notification WHERE createdTime >= :startTime AND createdTime <= :endTime AND packageName = :packageName ORDER BY createdTime DESC")
    // fun fetchNotificationsPackageAndDate(packageName: String, startTime: String, endTime: String): DataSource.Factory<Int, AppNotificationDTO>

    @Query("SELECT * FROM notification ORDER BY createdTime DESC LIMIT 20")
    fun fetchRecentNotifications(): Flow<List<AppNotificationDTO>>
    //
    // @Query("SELECT * FROM notification WHERE createdTime >= :startTime AND createdTime <= :endTime ORDER BY createdTime DESC")
    // fun fetchTodayNotifications(startTime: String, endTime: String): DataSource.Factory<Int, AppNotificationDTO>
    //
    // @Query("SELECT * FROM notification WHERE createdTime >= :startTime AND createdTime <= :endTime GROUP BY packageName ORDER BY createdTime DESC")
    // fun fetchTodayNotificationsByPackages(startTime: String, endTime: String): DataSource.Factory<Int, AppNotificationDTO>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOne(notification: AppNotificationDTO)

    @Delete
    suspend fun delete(notification: AppNotificationDTO)

    @Query("DELETE FROM notification WHERE packageName = :packageName")
    suspend fun deleteFromPackage(packageName: String)

    @Query("DELETE FROM notification")
    fun clear()
}
