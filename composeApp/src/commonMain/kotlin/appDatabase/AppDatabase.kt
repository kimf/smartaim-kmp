package appDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Course::class, Hole::class, Scorecard::class, HoleScore::class], version = 1)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun courseDao(): CourseDao
    abstract fun holeDao(): HoleDao
    abstract fun scorecardDao(): ScorecardDao
    abstract fun holeScoreDao(): HoleScoreDao

    override fun clearAllTables() {
        super.clearAllTables()
    }
}

internal const val dbFileName = "smartaim.db"

interface DB {
    fun clearAllTables() {}
}