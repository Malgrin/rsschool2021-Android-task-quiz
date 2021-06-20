package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding
import kotlin.system.exitProcess


class QuizFragment : Fragment(), BackPressed {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var number = 0

    private val questionList = QuestionList()
    private var idAnswer: Int? = null

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val numberTheme = arguments?.getInt(NUMBER_QUESTION) ?: 0

        val currentTheme = setTheme(numberTheme)
        val contextThemeWrapper = ContextThemeWrapper(activity, currentTheme)
        val localInflater = inflater.cloneInContext(contextThemeWrapper)
        _binding = FragmentQuizBinding.inflate(localInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numberQuestion = arguments?.getInt(NUMBER_QUESTION) ?: 0
        val value = arguments?.getInt(ID_ANSWER, -1)
        number = numberQuestion

        idAnswer = if (value == -1) null else value
        val str = "Submit"

        with(binding) {
            toolbar.title = "Question " + (numberQuestion + 1)
            question.text = questionList.getQuestion(0)[numberQuestion]
            optionOne.text = questionList.getAnswer(numberQuestion)[0]
            optionTwo.text = questionList.getAnswer(numberQuestion)[1]
            optionThree.text = questionList.getAnswer(numberQuestion)[2]
            optionFour.text = questionList.getAnswer(numberQuestion)[3]
            optionFive.text = questionList.getAnswer(numberQuestion)[4]

            nextButton.isEnabled = idAnswer != null
            previousButton.isEnabled = numberQuestion != 0
            if (numberQuestion != 0) {
                toolbar.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
            }
            //app:navigationIcon="@drawable/ic_baseline_chevron_left_24"
            setRadioButton()

            radioGroup.setOnCheckedChangeListener { radioButton, checkedId ->
                // get the radio group checked radio button
                when (checkedId) {
                    optionOne.id -> {
                        idAnswer = 1
                    }
                    optionTwo.id -> {
                        idAnswer = 2
                    }
                    optionThree.id -> {
                        idAnswer = 3
                    }
                    optionFour.id -> {
                        idAnswer = 4
                    }
                    optionFive.id -> {
                        idAnswer = 5
                    }
                }
                //previousButton.text = idAnswer.toString()
                nextButton.isEnabled = true
            }
            if (numberQuestion == questionList.getSize() - 1) {
                nextButton.text = str
            }


            nextButton.setOnClickListener {
                //nextButton.isEnabled Кнопка неактивна

                if (questionList.isLastQuestion(numberQuestion)) {
                    //if (questionList.getCorrectAnswer(0)[numberQuestion] == idAnswer) {
                        idAnswer?.let {(activity as? TransitQuizFragment)?.openCastQuizFragment(
                            numberQuestion + 1,
                            it
                        )}
                    //}
                } else {
                    idAnswer?.let {(activity as? TransitResultFragment)?.openCastResultFragment(
                        numberQuestion + 1,
                        it
                    )}
                }
            }

            previousButton.setOnClickListener {
                if (numberQuestion > 0) {
                    (activity as? TransitQuizFragment)?.openCastQuizFragment(numberQuestion - 1, 0)
                }
            }

            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    interface TransitQuizFragment {
        fun openCastQuizFragment(numberQuestion: Int, idAnswer: Int)
    }

    interface TransitResultFragment {
        fun openCastResultFragment(numberQuestion: Int, idAnswer: Int)
    }

    private fun setRadioButton() {
        when (idAnswer) {
            1 -> {
                binding.optionOne.isChecked = true
            }
            2 -> {
                binding.optionTwo.isChecked = true
            }
            3 -> {
                binding.optionThree.isChecked = true
            }
            4 -> {
                binding.optionFour.isChecked = true
            }
            5 -> {
                binding.optionFive.isChecked = true
            }
        }
    }

    private fun setTheme(numberQuestion: Int): Int {
        var x = 0
        when (numberQuestion) {
            0 -> {
                x = R.style.Theme_Quiz_First
            }
            1 -> {
                x = R.style.Theme_Quiz_Second
            }
            2 -> {
                x = R.style.Theme_Quiz_Third
            }
            3 -> {
                x = R.style.Theme_Quiz_Fourth
            }
            4 -> {
                x = R.style.Theme_Quiz_Fifth
            }
        }
        return x
    }

    override fun onBackPressed() {
        if (number > 0) {
            (activity as? TransitQuizFragment)?.openCastQuizFragment(number - 1, 0)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(numberQuestion: Int, idAnswer: Int?): QuizFragment {
            val fragment = QuizFragment()
            val args = Bundle()
            args.putInt(NUMBER_QUESTION, numberQuestion)
            idAnswer?.let {args.putInt(ID_ANSWER, it)}
            fragment.arguments = args
            return fragment
        }

        private const val NUMBER_QUESTION = "NUMBER_QUESTION"
        private const val ID_ANSWER = "ID_ANSWER"
    }
}


