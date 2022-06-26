package com.example.stackoverflowjetpackcompose.model

data class Questions(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int,
    val backoff:Int
)