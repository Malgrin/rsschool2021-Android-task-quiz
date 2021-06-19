package com.rsschool.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val questionList = QuestionList()
    private var idAnswer: Int? = null



    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Установка темы
        //setTheme(R.style.??)

        super.onViewCreated(view, savedInstanceState)

        val numberQuestion = arguments?.getInt(NUMBER_QUESTION) ?: 0
        val value = arguments?.getInt(ID_ANSWER, -1)
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
        }
    }



    interface TransitQuizFragment {
        fun openCastQuizFragment(numberQuestion: Int, idAnswer: Int)
    }

    interface TransitResultFragment {
        fun openCastResultFragment(numberQuestion: Int, idAnswer: Int)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        idAnswer?.let { outState.putInt(ID_ANSWER, it) }

        super.onSaveInstanceState(outState)
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