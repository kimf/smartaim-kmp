package appDatabase

import androidx.room.Room

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DBFactory {
    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(documentDirectory?.path)
    }

    actual fun createDatabase(): AppDatabase {
        val dbFilePath = documentDirectory() + dbFileName
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath
        ).addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
    }
}
