package com.rsschool.quiz

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt(NUMBER_QUESTION) ?: 0
        with(bindingRes) {
            resultOut.text = result.toString()

            back.setOnClickListener() {
                (activity as MainActivity).openCastQuizFragment(0, -1)
            }

            exit.setOnClickListener() {
                exitProcess(-1)
            }

            share.setOnClickListener() {

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