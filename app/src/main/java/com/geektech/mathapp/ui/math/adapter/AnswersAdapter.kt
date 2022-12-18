package com.geektech.mathapp.ui.math.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.mathapp.databinding.ItemAnswerBinding

class AnswersAdapter(private val onAnswerClick: (answer: Int) -> Unit) :
    RecyclerView.Adapter<AnswersAdapter.AnswersViewHolder>() {

    private val data = arrayListOf<Int>()

    fun addData(newData: HashSet<Int>) {
        data.clear()
        data.addAll(newData)
        data.shuffle()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        return AnswersViewHolder(
            ItemAnswerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
    inner class AnswersViewHolder(private val binding: ItemAnswerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: Int) {
            binding.tvPickAnswer.text = number.toString()

            itemView.setOnClickListener {
                onAnswerClick(number)
            }
        }
    }
}