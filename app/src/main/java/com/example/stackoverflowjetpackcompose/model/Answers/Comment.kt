package com.example.stackoverflowjetpackcompose.model.Answers

data class Comment(
    val comment_id: Int,
    val content_license: String,
    val creation_date: Int,
    val edited: Boolean,
    val owner: Owner,
    val post_id: Int,
    val reply_to_user: ReplyToUser,
    val score: Int
)