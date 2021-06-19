package com.rsschool.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), QuizFragment.TransitQuizFragment,
    QuizFragment.TransitResultFragment {

    private lateinit var bindingMain: ActivityMainBinding
    private val answerList = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        openQuizFragment(0, 0)

    }

    private fun openQuizFragment(numberQuestion: Int, idAnswer: Int) {
        setAnswerList(numberQuestion, idAnswer)
        val quizFragment: Fragment =
            QuizFragment.newInstance(numberQuestion, answerList.getOrNull(numberQuestion))
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, quizFragment)
        transaction.commit()
    }


    private fun openResultFragment(numberQuestion: Int, idAnswer: Int) {
        var result = 0
        var k = 0
        setAnswerList(numberQuestion, idAnswer)
        for (i in correctAnswer.indices) {
            if (correctAnswer[i] == answerList[i]) {
                k += 1
                //result += 20

            }
        }
        result += ((k * 100) / (correctAnswer.size))

        val resultFragment: Fragment = ResultFragment.newInstance(result)
        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.container, resultFragment)
        transaction.commit()
    }

    override fun openCastQuizFragment(numberQuestion: Int, idAnswer: Int) {
        openQuizFragment(numberQuestion, idAnswer)
    }

    override fun openCastResultFragment(numberQuestion: Int, idAnswer: Int) {
        openResultFragment(numberQuestion, idAnswer)
    }

    private val correctAnswer = listOf(1, 2, 3, 4, 5)


    private fun setAnswerList(numberQuestion: Int, idAnswer: Int) {
        if (numberQuestion > 0 && idAnswer > 0) {

            //answerList.removeAt(numberQuestion)
            if (answerList.elementAtOrNull(numberQuestion - 1) == null) {
                answerList.add(numberQuestion - 1, idAnswer)
            } else {
                answerList[numberQuestion - 1] = idAnswer
            }

            Toast.makeText(getApplicationContext(), "$answerList", Toast.LENGTH_SHORT).show()
        } else if (numberQuestion == 0 && idAnswer == -1) {
            answerList.removeAt(4)
            answerList.removeAt(3)
            answerList.removeAt(2)
            answerList.removeAt(1)
            answerList.removeAt(0)
        }
    }

}