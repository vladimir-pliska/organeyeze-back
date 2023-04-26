package com.organeyeze.backend.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Document("container")
data class Container(
    val _id: ObjectId,
    val name: String,
    val description: String? = null,
    val content: List<String> = emptyList()
)

@Repository
interface ContainerRepository : MongoRepository<Container, ObjectId>
