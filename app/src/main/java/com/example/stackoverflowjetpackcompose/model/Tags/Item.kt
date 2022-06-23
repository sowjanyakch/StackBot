package com.example.stackoverflowjetpackcompose.model.Tags

data class Item(
    val count: Int,
    val has_synonyms: Boolean,
    val is_moderator_only: Boolean,
    val is_required: Boolean,
    val name: String
)