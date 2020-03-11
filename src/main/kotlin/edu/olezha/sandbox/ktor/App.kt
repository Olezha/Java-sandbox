package edu.olezha.sandbox.ktor

import edu.olezha.sandbox.ktor.service.DatabaseFactory
import edu.olezha.sandbox.ktor.service.UserService
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    DatabaseFactory.init()
    val userService = UserService()

    embeddedServer(Netty, 8080) {
        install(ContentNegotiation) {
            jackson {
            }
        }
        routing {
            route("/user/") {
                get {
                    call.respond(userService.getAllUsers())
                }
                get("{id}") {
                    val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
                    val user = userService.getUser(id)
                    if (user == null) call.respond(HttpStatusCode.NotFound)
                    else call.respond(user)
                }
            }
        }
    }.start(true)
}
