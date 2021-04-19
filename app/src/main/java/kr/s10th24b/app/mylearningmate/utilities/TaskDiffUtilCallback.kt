package kr.s10th24b.app.mylearningmate.utilities

import androidx.recyclerview.widget.DiffUtil
import kr.s10th24b.app.mylearningmate.model.Task as Task

class TaskDiffUtilCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}