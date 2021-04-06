package br.com.firstsoft.historiconotificacoes.data.src.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE notification ADD COLUMN extras TEXT")
        }
    }
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `package` (`packageName` TEXT NOT NULL, `appName` TEXT, `notificationCount` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, PRIMARY KEY(`packageName`))")
        }
    }
}
