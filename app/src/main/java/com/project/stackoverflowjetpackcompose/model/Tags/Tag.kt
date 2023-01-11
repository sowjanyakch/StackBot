package com.project.stackoverflowjetpackcompose.model.Tags

data class Tag(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)