package kr.s10th24b.app.mylearningmate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.play.core.internal.t
import com.trello.rxlifecycle4.kotlin.bindUntilEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableCompletableObserver
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.s10th24b.app.mylearningmate.model.DataRepository
import kr.s10th24b.app.mylearningmate.model.Task
import kr.s10th24b.app.mylearningmate.model.User
import kr.s10th24b.app.mylearningmate.utilities.DevTool.logD
import splitties.toast.toast
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class LearningMateViewModel @Inject internal constructor(
    private val dataRepository: DataRepository
) : ViewModel() {
    val taskList = MutableLiveData<List<Task>>()
    val selectedTask = MutableLiveData<Task>()
    private val mCompositeDatabase by lazy { CompositeDisposable() }

    init {
        getAllTask()
    }

    fun insertTask(task: Task) {
        dataRepository.getTaskRepository().insertTask(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    logD( "insertTask() onSubscribe $d")
                }

                override fun onComplete() {
                    logD( "insertTask() onComplete ${task.id}")
                }

                override fun onError(e: Throwable?) {
                    logD( "insertTask() onError $e")
                }
            })
    }

    fun deleteTask(task: Task) {
        dataRepository.getTaskRepository().deleteTask(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                    logD("deleteTask() onComplete")

                }

                override fun onError(e: Throwable?) {
                    error("Error in deleteTask()")
                }
            })
    }

    fun deleteTaskById(id: Long) {
        dataRepository.getTaskRepository().deleteTaskById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onComplete() {
                    logD("deleteTaskById() onComplete")
                }

                override fun onError(e: Throwable?) {
                    error("Error in deleteTaskById(id)")
                }
            })
    }

    fun updateTask(task: Task) {
        dataRepository.getTaskRepository().updateTask(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    logD("updateTask() onSubscribe")
                }

                override fun onComplete() {
                    logD("updateTask() onComplete")
                }

                override fun onError(e: Throwable?) {
                    error("Error in updateTask()")
                }
            })
    }


    private fun getAllTask() {
        dataRepository.getTaskRepository().getAllTask()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : Observer<List<Task>> {
                override fun onSubscribe(d: Disposable?) {
                    logD( "getAllTask() onSubscribe $d")
                }

                override fun onNext(t: List<Task>?) {
                    t?.let {
                        taskList.value = it.toList()
                    }
                    logD( "getAllTask() onNext $t")
                }

                override fun onError(e: Throwable?) {
                    logD( "getAllTask() onError $e")
                }

                override fun onComplete() {
                    logD( "getAllTask() onComplete")
                }
            })
    }

    fun getTask(id: Long) {
        dataRepository.getTaskRepository().getTask(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Task>() {
                override fun onSuccess(t: Task?) {
                    selectedTask.value = t as Task
                }

                override fun onError(e: Throwable?) {
                    error(e?.printStackTrace().toString())
                }
            })
    }

    fun insertRandomTask() {
        val task = Task()
        task.subject = "토익"
        task.problemCount = Random.nextInt(100)
        task.time = "02:10"
        insertTask(task)
    }
}