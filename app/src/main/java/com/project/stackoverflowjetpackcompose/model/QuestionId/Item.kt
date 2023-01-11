package com.project.stackoverflowjetpackcompose.model.QuestionId

data class Item(
    val answer_count: Int,
    val body: String,
    val body_markdown: String,
    val creation_date: Int,
    val is_answered: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val last_editor: LastEditor,
    val link: String,
    val owner: Owner,
    val question_id: Int,
    val score: Int,
    val share_link: String,
    val tags: List<String>,
    val title: String,
    val view_count: Int
)