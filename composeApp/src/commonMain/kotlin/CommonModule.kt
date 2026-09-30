fun commonModule(): Module = module {
    single<ScorecardDao> { get<AppDatabase>().scorecardDao() }
    //    suspend fun insertCourse(course: Course)
    //    fun getCourseById(courseId: Int): Flow<Course?>
    //    fun getAllCourses(): Flow<List<Course>>
    //    fun getCourseWithHolesById(courseId: Int): Flow<CourseWithHoles?>
    //    fun getAllCoursesWithHoles(): Flow<List<CourseWithHoles>>
    //
    //    suspend fun insertHole(hole: Hole)
    //    fun getHolesForCourse(courseId: Int): Flow<List<Hole>>
    //    suspend fun insertScorecard(scorecard: Scorecard): Long // Return the inserted row ID
    //
    //    suspend fun insertCourse(course: Course)
    //    fun getCourseById(courseId: Int): Flow<Course?>
    //    fun getAllCourses(): Flow<List<Course>>
    //    fun getCourseWithHolesById(courseId: Int): Flow<CourseWithHoles?>
    //    fun getAllCoursesWithHoles(): Flow<List<CourseWithHoles>>
    //
    //    suspend fun insertHole(hole: Hole)
    //    fun getHolesForCourse(courseId: Int): Flow<List<Hole>>
    //    suspend fun insertScorecard(scorecard: Scorecard): Long // Return the inserted row ID
    //
    //    fun getScorecardById(scorecardId: Int): Flow<Scorecard?>
    //    fun getAllScorecards(): Flow<List<Scorecard>>
    //    fun getScorecardWithCourseById(scorecardId: Int): Flow<ScorecardWithCourse?>
    //    fun getScorecardWithHoleScoresById(scorecardId: Int): Flow<ScorecardWithHoleScores?>
    //    fun getAllScorecardsWithCourse(): Flow<List<ScorecardWithCourse>>
    //    fun getAllScorecardsWithCourseAndHoleScores(): Flow<List<ScorecardWithCourseAndHoleScores>>
    //
    //    suspend fun insertHoleScore(holeScore: HoleScore)
    //    fun getHoleScoresForHole(holeId: Int): Flow<List<HoleScore>>
    //    fun getHoleScoreWithHoleById(holeScoreId: Int): Flow<HoleScoreWithHole?>
    //    fun getHoleScoresWithHoleForScorecard(scorecardId: Int): Flow<List<HoleScoreWithHole>>
}