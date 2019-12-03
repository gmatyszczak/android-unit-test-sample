package pl.gmat.news.common.feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.gmat.news.common.SingleLiveEvent

abstract class BaseViewModel<State, Effect> : ViewModel() {

    protected abstract val initialState: State

    val state = MutableLiveData<State>()
    val effect = SingleLiveEvent<Effect>()

    protected val currentState
        get() = state.value ?: initialState
}
