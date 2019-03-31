package com.treefuerza.pruebas.vertx.utils

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

fun Route.coroutineHandler(scope: CoroutineScope, fn: suspend (RoutingContext) -> Unit) {
    handler { ctx ->
        scope.async(ctx.vertx().dispatcher()) {
            try {
                fn(ctx)
            } catch (e: Exception) {
                ctx.fail(e)
            }
        }
    }
}