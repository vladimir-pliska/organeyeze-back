package com.organeyeze.backend.dto

import com.organeyeze.backend.entity.Container
import org.bson.types.ObjectId

data class ContainerDTO(
    val id: ObjectId,
    val userId: ObjectId,
    val name: String,
    val description: String?,
    val content: List<String>
) {
    companion object {
        fun from(container: Container) = ContainerDTO(
            id = container._id,
            userId = container.userId,
            name = container.name,
            description = container.description,
            content = container.content
        )
    }
}
