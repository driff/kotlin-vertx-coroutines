package com.treefuerza.pruebas.vertx.controllers

import io.vertx.core.json.Json
import io.vertx.ext.web.RoutingContext
import kotlinx.coroutines.delay

class UserController {

    data class User(var name: String, var email: String)

    suspend fun getAllUsers(routingContext: RoutingContext){
        delay(1000)
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(listOf(User("Test", "This.is@test.com"))))
    }
}