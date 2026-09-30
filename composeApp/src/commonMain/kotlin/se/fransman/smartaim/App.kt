package se.fransman.smartaim

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import AppDatabase
import ScorecardWithCourse
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val scorecardsDao = db.scorecardDao()
        val viewModel = ScorecardListViewModel(scorecardsDao)

        val items = viewModel.scorecards.collectAsState()

        NavHost(navController, startDestination = "list") {

            composable("list") {
                ScorecardList(state = items) { item ->
                    navController.navigate("scorecards/${item.scorecard.id}")
                }
            }

            composable("scorecards/{id}") { it ->
                val id = it.arguments?.toString()
                val item = items.value.data?.first { it.scorecard.id == id?.toInt() }
                DetailScreen(item!!) {
                    navController.navigateUp()
                }
            }
        }
    }
}


@Composable
fun ScorecardList(
    state: State<ListUiState>,
    onItemClick: (ScorecardWithCourse) -> Unit
) {
    if (state.value.isLoading == true) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.size(30.dp).align(Alignment.Center))
        }
    } else if (state.value.data != null) {
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    "SCORECARDS",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            items(state.value.data!!.count()) { index ->
                val item = state.value.data!![index]
                Card(
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
                        },
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Column {
                            Text(
                                text = item.scorecard.toPar.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Scorecard ID: ${item.scorecard.id}")
                            Text(text = "Course: ${item.course.name}")
                            Text(text = "Strokes: ${item.scorecard.strokes}")
                            Text(text = "To Par: ${item.scorecard.toPar}")
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(item: ScorecardWithCourse, goBack: () -> Unit) {

    // Detail
    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets(0.dp)),
                title = {
                    Text(
                        item.course.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    Button(onClick = goBack) {
                        Text("Go Back")
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)
        ) {


            Text(
                item.scorecard.toPar.toString(),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}