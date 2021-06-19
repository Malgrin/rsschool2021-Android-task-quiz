package com.rsschool.quiz

class QuestionList() {

    private val answers = listOf(
        listOf(
            "правильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
        ),
        listOf(
            "неправильный ответ",
            "правильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
        ),
        listOf(
            "неправильный ответ",
            "неправильный ответ",
            "правильный ответ",
            "неправильный ответ",
            "неправильный ответ",
        ),
        listOf(
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "правильный ответ",
            "неправильный ответ",
        ),
        listOf(
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "неправильный ответ",
            "правильный ответ",
        )
    )

    private val questions = listOf(
        listOf(
            "Вопрос 1",
            "Вопрос 2",
            "Вопрос 3",
            "Вопрос 4",
            "Вопрос 5",
        )
    )

    private val correctAnswer = listOf(
        listOf(
            1,
            2,
            3,
            4,
            5
        )
    )

    fun getAnswer(number: Int): List<String> = answers[number]
    fun getQuestion(number: Int): List<String> = questions[number]
    fun getCorrectAnswer(number: Int): List<Int> = correctAnswer[number]
    fun isLastQuestion(number: Int): Boolean = number < questions[0].size - 1
    fun getSize(): Int = questions[0].size
}