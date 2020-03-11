package edu.olezha.sandbox.ktor.service

import edu.olezha.sandbox.ktor.model.User
import edu.olezha.sandbox.ktor.model.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class UserService {

    suspend fun getAllUsers(): List<User> = DatabaseFactory.dbQuery {
        Users.selectAll().map { toUser(it) }
    }

    suspend fun getUser(id: Int): User? = DatabaseFactory.dbQuery {
        Users.select {
                    (Users.id eq id)
                }.mapNotNull { toUser(it) }
                .singleOrNull()
    }


    private fun toUser(row: ResultRow): User =
            User(id = row[Users.id], name = row[Users.name])
}
