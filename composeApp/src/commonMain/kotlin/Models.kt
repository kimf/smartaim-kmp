import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import androidx.room.Relation

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val par: Int
)

@Entity(tableName = "holes")
data class Hole(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseId: Int,
    val hcp: Int,
    val par: Int,
    val number: Int
)

@Entity(tableName = "scorecards")
data class Scorecard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val courseId: Int,
    val toPar: Int,
    val strokes: Int,
    val playedHoles: Int,
    val putts: Int,
    val points: Int,
    val givenStrokes: Int
)

@Entity(tableName = "hole_scores",
    foreignKeys = [androidx.room.ForeignKey(
        entity = Scorecard::class,
        parentColumns = ["id"],
        childColumns = ["scorecardId"],
        onDelete = androidx.room.ForeignKey.CASCADE
    )],
    indices = [androidx.room.Index(value = ["scorecardId"])]
)
data class HoleScore(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val scorecardId: Int,
    val holeId: Int,
    val strokes: Int,
    val points: Int,
    val putts: Int,
    val fir: Boolean,
    val gir: Boolean,
    val givenStrokes: Int,
    val toPar: Int
)

data class CourseWithHoles(
    @Embedded val course: Course,
    @Relation(
        parentColumn = "id",
        entityColumn = "courseId"
    )
    val holes: List<Hole>
)

data class ScorecardWithCourse(
    @Embedded val scorecard: Scorecard,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "id"
    )
    val course: Course
)

data class ScorecardWithHoleScores(
    @Embedded val scorecard: Scorecard,
    @Relation(
        parentColumn = "id",
        entityColumn = "holeId", // assuming HoleScore has a scorecardId, adjust if needed
        entity = HoleScore::class // Specify the related entity
    )
    val holeScores: List<HoleScore>
)

data class HoleScoreWithHole(
    @Embedded val holeScore: HoleScore,
    @Relation(
        parentColumn = "holeId",
        entityColumn = "id"
    )
    val hole: Hole
)

data class ScorecardWithCourseAndHoleScores(
    @Embedded val scorecard: Scorecard,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "id"
    )
    val course: Course,
    @Relation(
        parentColumn = "id",
        entityColumn = "scorecardId",
        entity = HoleScore::class
    )
    val holeScores: List<HoleScore>
)