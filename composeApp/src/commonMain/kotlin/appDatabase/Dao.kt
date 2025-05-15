package appDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(course: Course)

    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseById(courseId: Int): Flow<Course?>

    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseWithHolesById(courseId: Int): Flow<CourseWithHoles?>

    @Query("SELECT * FROM courses")
    fun getAllCoursesWithHoles(): Flow<List<CourseWithHoles>>
}

@Dao
interface HoleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHole(hole: Hole)

    @Query("SELECT * FROM holes WHERE courseId = :courseId ORDER BY number ASC")
    fun getHolesForCourse(courseId: Int): Flow<List<Hole>>
}

@Dao
interface ScorecardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScorecard(scorecard: Scorecard): Long // Return the inserted row ID

    @Query("SELECT * FROM scorecards WHERE id = :scorecardId")
    fun getScorecardById(scorecardId: Int): Flow<Scorecard?>

    @Query("SELECT * FROM scorecards ORDER BY id DESC") // Order by latest scorecards first
    fun getAllScorecards(): Flow<List<Scorecard>>

    @Query("SELECT * FROM scorecards WHERE id = :scorecardId")
    fun getScorecardWithCourseById(scorecardId: Int): Flow<ScorecardWithCourse?>

    @Query("SELECT * FROM scorecards WHERE id = :scorecardId")
    fun getScorecardWithHoleScoresById(scorecardId: Int): Flow<ScorecardWithHoleScores?>

    @Query("SELECT * FROM scorecards")
    fun getAllScorecardsWithCourse(): Flow<List<ScorecardWithCourse>>

    // You might also want a query to get scorecards with both course and hole scores
    @androidx.room.Transaction // Use @Transaction for queries that involve multiple tables
    @Query("SELECT * FROM scorecards")
    fun getAllScorecardsWithCourseAndHoleScores(): Flow<List<ScorecardWithCourseAndHoleScores>>
}

@Dao
interface HoleScoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHoleScore(holeScore: HoleScore)

    @Query("SELECT * FROM hole_scores WHERE holeId = :holeId")
    fun getHoleScoresForHole(holeId: Int): Flow<List<HoleScore>> // Maybe get one specific hole score for a scorecard?

    @Query("SELECT * FROM hole_scores WHERE id = :holeScoreId")
    fun getHoleScoreWithHoleById(holeScoreId: Int): Flow<HoleScoreWithHole?>

    @Query("SELECT * FROM hole_scores WHERE scorecardId = :scorecardId")
    fun getHoleScoresWithHoleForScorecard(scorecardId: Int): Flow<List<HoleScoreWithHole>>
}


