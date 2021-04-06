package br.com.firstsoft.historiconotificacoes.data.src.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.firstsoft.historiconotificacoes.data.src.db.converters.MapTypeConverter
import br.com.firstsoft.historiconotificacoes.data.src.models.AppNotificationDTO

@Database(entities = [AppNotificationDTO::class], version = 3, exportSchema = true)
@TypeConverters(MapTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}
