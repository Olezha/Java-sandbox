package edu.olezha.sandbox.ktor.model

import org.jetbrains.exposed.sql.Table

data class User(val id: Int?, val name: String)

object Users : Table() {
    val id = Users.integer("id").primaryKey().autoIncrement()
    val name = Users.varchar("name", 255)
}
