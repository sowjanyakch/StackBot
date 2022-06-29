package com.example.stackoverflowjetpackcompose.model.Answers

data class Item(
    val answer_id: Int,
    val body: String,
    val body_markdown: String,
    val comment_count: Int,
    val comments: List<Comment>,
    val content_license: String,
    val creation_date: Int,
    val is_accepted: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val owner: Owner,
    val question_id: Int,
    val score: Int
)