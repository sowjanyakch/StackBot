package com.example.stackoverflowjetpackcompose.model.Search

data class Item(
    val accepted_answer_id: Int,
    val answer_count: Int,
    val body: String,
    val body_markdown: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val protected_date: Int,
    val question_id: Int,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val view_count: Int
)