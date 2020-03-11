package edu.olezha.sandbox.ktor

import edu.olezha.sandbox.ktor.model.User
import edu.olezha.sandbox.ktor.service.DatabaseFactory
import edu.olezha.sandbox.ktor.service.UserService
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
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
                post {
                    val user = call.receive<User>()
                    call.respond(HttpStatusCode.Created, userService.addUser(user))
                }
                put {
                    val user = call.receive<User>()
                    val updated = userService.updateUser(user)
                    if (updated == null) call.respond(HttpStatusCode.NotFound)
                    else call.respond(HttpStatusCode.OK, updated)
                }
                delete("{id}") {
                    val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
                    val removed = userService.deleteUser(id)
                    if (removed) call.respond(HttpStatusCode.OK)
                    else call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }.start(true)
}
