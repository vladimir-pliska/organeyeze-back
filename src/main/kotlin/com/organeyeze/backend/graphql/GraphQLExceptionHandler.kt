package com.organeyeze.backend.graphql

import com.organeyeze.backend.utility.logger
import graphql.ErrorType
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class GraphQLExceptionHandler : DataFetcherExceptionResolverAdapter() {

    private val log = logger<GraphQLExceptionHandler>()

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (ex) {
            is Exception -> injectError(ex)
            else -> super.resolveToSingleError(ex, env)
        }
    }

    private fun injectError(ex: Throwable): GraphQLError? {
        log.error("Exception while handling a request: ${ex.message}")
        return GraphqlErrorBuilder.newError().message(ex.message).errorType(ErrorType.DataFetchingException).build()
    }
}
