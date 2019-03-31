package com.treefuerza.pruebas.vertx

import com.treefuerza.pruebas.vertx.models.Whisky
import com.treefuerza.pruebas.vertx.routers.Api
import com.treefuerza.pruebas.vertx.utils.coroutineHandler
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.core.http.listenAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import kotlinx.coroutines.delay

class App : CoroutineVerticle() {

    private fun createSomeData(): Map<Long, Whisky>{
        val products: MutableMap<Long, Whisky> = hashMapOf()
        val bowmore = Whisky(name = "Bowmore 15 Years Laimrig", origin = "Scotland, Islay")
        println(bowmore)
        products.put(bowmore.id, bowmore)
        val talisker = Whisky(name = "Talisker 57° North", origin = "Scotland, Island")
        products.put(talisker.id, talisker)
        println(talisker)
        return products
    }

    override suspend fun start() {
        val router = Router.router(vertx)
        val apiRouter = Api(vertx, this).router
        router.mountSubRouter("/api", apiRouter)
        router.route().handler(BodyHandler.create())
        router.get("/products").coroutineHandler(this, this::handleGetProducts)
        vertx.createHttpServer().requestHandler(router)
            .listenAwait(config.getInteger("http.port", 8080))

    }



    private suspend fun handleGetProducts(routingContext: RoutingContext) {
        delay(2000)
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(createSomeData().values))
    }

}


