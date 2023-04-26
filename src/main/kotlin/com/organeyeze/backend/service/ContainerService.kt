package com.organeyeze.backend.service

import com.organeyeze.backend.dto.UpsertContainerInput
import com.organeyeze.backend.entity.Container
import com.organeyeze.backend.entity.ContainerRepository
import com.organeyeze.backend.utility.logger
import graphql.GraphQLException
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Suppress("unused")
class ContainerService(
    private val containerRepository: ContainerRepository,
) {
    private val log = logger<ContainerService>()

    fun findAll(): List<Container> = containerRepository.findAll()

    fun findById(id: ObjectId): Container =
        containerRepository.findByIdOrNull(id) ?: throw GraphQLException(
            "Could not find a container with id=$id"
        )

    fun upsertContainer(
        input: UpsertContainerInput,
    ): Container = if (input._id == null) insertContainer(input) else updateContainer(input)

    private fun insertContainer(
        input: UpsertContainerInput,
    ): Container = containerRepository.save(
        Container(
            _id = ObjectId.get(),
            name = input.name,
            description = input.description,
            content = input.content,
        )
    )

    private fun updateContainer(
        input: UpsertContainerInput,
    ): Container {
        val containerId = input._id ?: throw GraphQLException(
            "No id supplied for container editing"
        )
        val container = containerRepository.findByIdOrNull(containerId) ?: throw GraphQLException(
            "Container with id=$containerId not found",
        ) // todo: figure out how a patch would work and fix

        return containerRepository.save(
            Container(
                _id = containerId,
                name = input.name,
                description = input.description,
                content = input.content,
            )
        )
    }
}
