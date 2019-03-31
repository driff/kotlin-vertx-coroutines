package com.treefuerza.pruebas.vertx.routers

import com.treefuerza.pruebas.vertx.controllers.UserController
import com.treefuerza.pruebas.vertx.utils.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import kotlinx.coroutines.CoroutineScope

class Api(val vertx: Vertx, scope: CoroutineScope){

    val router:Router by lazy { Router.router(vertx) }
    val userController: UserController by lazy { UserController() }
    init {
        router.get("/users").coroutineHandler(scope, userController::getAllUsers)
    }

}