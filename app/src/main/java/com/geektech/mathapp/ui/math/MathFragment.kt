package com.geektech.mathapp.ui.math

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektech.mathapp.databinding.FragmentMathBinding
import com.geektech.mathapp.ui.math.adapter.AnswersAdapter
import kotlin.random.Random

class MathFragment : Fragment() {
    private lateinit var binding: FragmentMathBinding
    private lateinit var adapter: AnswersAdapter
    private var data = hashSetOf<Int>()
    private lateinit var operation: String
    private var firstNum = 0
    private var secondNum = 0
    private var trueAnswer = 0
    private var num = 0
    private var progress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMathBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {
        createTask()
        createAnswers()
        initAnswers()
        check()
    }

    private fun initAnswers() {
        adapter = AnswersAdapter { answer ->
            binding.tvAnswer.text = answer.toString()
        }
        adapter.addData(data)
        binding.rvAnswers.adapter = adapter
        binding.rvAnswers.isNestedScrollingEnabled = false
    }


    private fun createTask() {
        when (Random.nextInt(2)) {
            0 -> {
                operation = "+"
                num = Random.nextInt(50)
                firstNum = Random.nextInt(num)
                secondNum = Random.nextInt(num)
                trueAnswer = firstNum + secondNum
            }
            1 -> {
                operation = "-"
                num = Random.nextInt(50)
                firstNum = Random.nextInt(num)
                secondNum = Random.nextInt(num)
                trueAnswer = firstNum - secondNum
            }
            2 -> {
                operation = "*"
                num = Random.nextInt(10)
                firstNum = Random.nextInt(num)
                secondNum = Random.nextInt(num)
                trueAnswer = firstNum * secondNum
            }
            //            3 -> {
            //                operation = ":"
            //                num = Random.nextInt(9) + 1
            //                firstNum = Random.nextInt(num)
            //                secondNum = Random.nextInt(num)
            //                trueAnswer = 777
            ////                trueAnswer = firstNum / secondNum
            //            }
        }
        binding.tvOperation.text = operation
        binding.tvFirstNum.text = firstNum.toString()
        binding.tvSecondNum.text = secondNum.toString()
    }

    private fun createAnswers() {
        data.clear()
        data.add(trueAnswer)

        if (operation == "+" || operation == "-") {
            while (data.size != 5) {
                data.add((Random.nextInt(20) + (trueAnswer - 10)))
            }
        }
        Log.e("aga", data.toString())
    }


    private fun check() {
        binding.btnCheck.setOnClickListener {
            progress += 100
            binding.progressBar.progress = progress

            binding.tvAnswer.text = ""
            createTask()
            val secondThread = Thread {
                kotlin.run {
                    createAnswers()
                    adapter.addData(data)
                }
            }
            secondThread.start()
        }
    }
}
