package se.fransman.smartaim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import Scorecard
import ScorecardDao
import ScorecardWithCourse

class ScorecardListViewModel(
    private val scorecardsDao: ScorecardDao
) : ViewModel() {

    private val _scorecards = MutableStateFlow(ListUiState())
    val scorecards = _scorecards.asStateFlow()

    fun insertScorecard(scorecard: Scorecard) {
        viewModelScope.launch {
            scorecardsDao.insertScorecard(scorecard)
        }
    }

    init {
        viewModelScope.launch {
            _scorecards.value = ListUiState(isLoading = true)
            scorecardsDao.getAllScorecardsWithCourse().collect {
                _scorecards.value = ListUiState(
                    data = it,
                    isLoading = false
                )
            }
        }
    }
}

data class ListUiState(
    val data: List<ScorecardWithCourse>? = null,
    val isLoading: Boolean? = null,
    val error: String? = null,
)