package com.example.stackoverflowjetpackcompose.model.Search

data class Search(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)
