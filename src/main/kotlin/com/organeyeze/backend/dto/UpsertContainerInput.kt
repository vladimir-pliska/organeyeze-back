package com.organeyeze.backend.dto

import org.bson.types.ObjectId

data class UpsertContainerInput(
    val _id: ObjectId?,
    val userId: ObjectId,
    val name: String,
    val description: String?,
    val content: List<String>
)
