package com.example.stackoverflowjetpackcompose.model.QuestionId

data class QuestionItem(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)