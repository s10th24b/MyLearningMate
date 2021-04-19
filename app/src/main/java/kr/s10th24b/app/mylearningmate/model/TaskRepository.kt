package kr.s10th24b.app.mylearningmate.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject internal constructor(
    private val taskDao: TaskDao
) {
    fun getTask(id: String): Single<Task> = taskDao.getTask(id)
    fun getAllTask(): Observable<List<Task>> = taskDao.getAll()
    fun insertTask(task: Task) = taskDao.insert(task)
    fun updateTask(task: Task) = taskDao.update(task)
    fun deleteTask(task: Task) = taskDao.delete(task)
}
