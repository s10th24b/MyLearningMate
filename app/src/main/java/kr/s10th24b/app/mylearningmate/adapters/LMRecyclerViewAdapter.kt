package kr.s10th24b.app.mylearningmate.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kr.s10th24b.app.mylearningmate.R
import kr.s10th24b.app.mylearningmate.databinding.LearningMateCardViewBinding
import kr.s10th24b.app.mylearningmate.model.Task
import splitties.coroutines.repeatWhileActive
import splitties.toast.toast
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class LMRecyclerViewAdapter :
    ListAdapter<Task, LMRecyclerViewAdapter.LMRecyclerViewHolder>(TaskDiffUtilCallback()) {
    lateinit var listener: ListAdapterListener

    interface ListAdapterListener {
        fun onRemoveButtonClicked(task: Task)
        fun onMenuOpened(task: Task)
    }

    fun setListAdapterListener(listener: ListAdapterListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LMRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_mate_card_view, parent, false)
        return LMRecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LMRecyclerViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
        // remove button
        holder.binding.clearButton.setOnClickListener {
            holder.binding.clearButton.isClickable = false
            Log.d("KHJ", "currentList[$position]")
            // 삽질 엄청 했다. recyclerView에서 Item을 없앨 때는! 꼭! adapterPosition을 이용하자!
//            removeButtonObservable.onNext(getItem(position).id)
//            removeButtonObservable.onNext(getItem(holder.adapterPosition).id)
            listener.onRemoveButtonClicked(getItem(holder.adapterPosition))
        }
        holder.binding.lmcardView.setOnCreateContextMenuListener { menu, view, menuInfo ->
            val inflater = MenuInflater(view.context)
            Log.d("KHJ", "view: $view")
            listener.onMenuOpened(getItem(holder.adapterPosition))
            inflater.inflate(R.menu.lm_card_context_menu, menu)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        Log.d("KHJ", "onDetachedFromRecyclerView")
        super.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onViewDetachedFromWindow(holder: LMRecyclerViewHolder) {
//        Log.d("KHJ","onViewDetachedFromWindow")
        super.onViewDetachedFromWindow(holder)
    }

    class LMRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: LearningMateCardViewBinding by lazy {
            LearningMateCardViewBinding.bind(itemView)
        }

        fun bind(task: Task) {
            binding.probCountText.text = task.problemCount.toString()
            binding.subjectText.text = task.subject
            binding.timeText.text = task.time
        }
    }
}
class TaskDiffUtilCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}
