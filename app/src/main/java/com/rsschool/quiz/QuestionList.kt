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
            "Вопрос 1: укажи верный ответ",
            "Вопрос 2: укажи верный ответ",
            "Вопрос 3: укажи верный ответ",
            "Вопрос 4: укажи верный ответ",
            "Вопрос 5: укажи верный ответ",
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