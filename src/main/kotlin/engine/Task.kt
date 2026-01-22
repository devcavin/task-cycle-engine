package engine

import java.util.UUID

@JvmInline
value class TaskId(val value: UUID)

@JvmInline
value class TaskName(val value: String)

@JvmInline
value class TaskDescription(val value: String)

data class Task(
    val id: TaskId,
    val name: TaskName,
    val description: TaskDescription,
    val status: TaskStatus
)


fun Task.start(): Result<Task> {
    return when (status) {
        TaskStatus.Created -> Result.success(copy(status = TaskStatus.InProgress))
        TaskStatus.InProgress -> Result.failure(TaskTransitionException.AlreadyStarted)
        TaskStatus.Completed -> Result.failure(TaskTransitionException.AlreadyCompleted)
    }
}

fun Task.complete(): Result<Task> {
    return when (status) {
        TaskStatus.Created -> Result.failure(TaskTransitionException.NotStarted)
        TaskStatus.InProgress -> Result.success(copy(status = TaskStatus.Completed))
        TaskStatus.Completed -> Result.failure(TaskTransitionException.AlreadyCompleted)
    }
}