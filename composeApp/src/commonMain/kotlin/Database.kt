import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [Course::class, Hole::class, Scorecard::class, HoleScore::class],
    version = 1,
    exportSchema = true
)

@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun courseDao(): CourseDao
    abstract fun holeDao(): HoleDao
    abstract fun scorecardDao(): ScorecardDao
    abstract fun holeScoreDao(): HoleScoreDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}