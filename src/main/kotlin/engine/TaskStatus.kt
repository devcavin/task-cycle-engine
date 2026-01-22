package engine

sealed interface TaskStatus {
    data object Created : TaskStatus
    data object InProgress : TaskStatus
    data object Completed : TaskStatus
}

sealed interface TaskTransitionError {
    data object AlreadyStarted : TaskTransitionError
    data object NotStarted : TaskTransitionError
    data object AlreadyCompleted: TaskTransitionError
}

sealed class TaskTransitionException(val error: TaskTransitionError): RuntimeException() {
    data object AlreadyStarted : TaskTransitionException(TaskTransitionError.AlreadyStarted)
    data object NotStarted : TaskTransitionException(TaskTransitionError.NotStarted)
    data object AlreadyCompleted : TaskTransitionException(TaskTransitionError.AlreadyCompleted)
}