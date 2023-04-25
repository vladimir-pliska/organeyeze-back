package com.organeyeze.backend.graphql

import com.organeyeze.backend.dto.ContainerDTO
import com.organeyeze.backend.dto.UpsertContainerInput
import com.organeyeze.backend.service.ContainerService
import org.bson.types.ObjectId
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ContainerController(
    private val containerService: ContainerService
) {

    @QueryMapping
    fun getContainers(): List<ContainerDTO> = containerService.findAll().map(ContainerDTO::from)

    @QueryMapping
    fun getContainer(
        @Argument id: ObjectId
    ): ContainerDTO = ContainerDTO.from(containerService.findById(id))

    @MutationMapping
    fun upsertContainer(
        @Argument input: UpsertContainerInput
    ): ContainerDTO = ContainerDTO.from(containerService.upsertContainer(input))
}
