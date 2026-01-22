package core

import engine.Task
import engine.TaskId

interface TaskRepository {
    fun save(task: Task)
    fun byId(id: TaskId): Task?
}