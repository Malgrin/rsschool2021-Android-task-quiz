package com.rsschool.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding
import kotlin.system.exitProcess


class ResultFragment : Fragment() {
    private var _bindingRes: FragmentResultBinding? = null
    private val bindingRes get() = _bindingRes!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bindingRes = FragmentResultBinding.inflate(inflater, container, false)
        return bindingRes.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingRes = null
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt(NUMBER_QUESTION) ?: 0
        val strRes = result.toString()
        var str = ""

        val questionList = QuestionList()



        with(bindingRes) {
            when (strRes.length) {
                6 -> {
                    resultOut.text = strRes[5].toString()
                    str = strRes[5].toString()
                }
                7 -> {
                    resultOut.text = strRes[5].toString() + strRes[6].toString()
                    str = strRes[5].toString() + strRes[6].toString()
                }
                8 -> {
                    resultOut.text = strRes[5].toString() + strRes[6].toString() + strRes[7].toString()
                    str = strRes[5].toString() + strRes[6].toString() + strRes[7].toString()
                }

            }

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, "Your result: " + str + "\n" +
                            "${questionList.getQuestion(0)[0]}: " + questionList.getAnswer(0)[strRes[0].toInt() - 49] + " \n" +
                            "${questionList.getQuestion(0)[1]}: " + questionList.getAnswer(1)[strRes[1].toInt() - 49] + " \n" +
                            "${questionList.getQuestion(0)[2]}: " + questionList.getAnswer(2)[strRes[2].toInt() - 49] + " \n" +
                            "${questionList.getQuestion(0)[3]}: " + questionList.getAnswer(3)[strRes[3].toInt() - 49] + " \n" +
                            "${questionList.getQuestion(0)[4]}: " + questionList.getAnswer(4)[strRes[4].toInt() - 49] + " \n")
                type = "text/plain"
            }

            back.setOnClickListener() {
                (activity as MainActivity).openCastQuizFragment(0, -1)
            }

            exit.setOnClickListener() {
                exitProcess(-1)
            }

            share.setOnClickListener() {
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(numberQuestion: Int): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putInt(NUMBER_QUESTION, numberQuestion)
            fragment.arguments = args
            return fragment
        }

        private const val NUMBER_QUESTION = "NUMBER_QUESTION"
        //private const val ID_ANSWER = "ID_ANSWER"
    }
}