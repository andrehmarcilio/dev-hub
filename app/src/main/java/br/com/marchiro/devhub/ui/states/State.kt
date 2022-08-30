package br.com.marchiro.devhub.ui.states

sealed class State<out T> {
    object InitialState : State<Nothing>()
    object LoadingState : State<Nothing>()
    data class ErrorState(var exception: String) : State<Nothing>()
    data class DataState<T>(var data: T) : State<T>()
}
