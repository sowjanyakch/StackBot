package com.example.stackoverflowjetpackcompose.model.Answers

data class Answers(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)