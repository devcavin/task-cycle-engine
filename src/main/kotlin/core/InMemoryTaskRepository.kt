package core

import engine.Task
import engine.TaskId

class InMemoryTaskRepository: TaskRepository {
    private val storage = mutableMapOf<TaskId, Task>()

    override fun save(task: Task) {
        storage[task.id] = task
    }

    override fun byId(id: TaskId): Task? = storage[id]

}