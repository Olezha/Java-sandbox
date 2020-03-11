package edu.olezha.sandbox.ktor.service

import edu.olezha.sandbox.ktor.model.User
import edu.olezha.sandbox.ktor.model.Users
import org.jetbrains.exposed.sql.*

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

    suspend fun updateUser(user: User): User? {
        return if (user.id == null) {
            addUser(user)
        } else {
            DatabaseFactory.dbQuery {
                Users.update({ Users.id eq user.id }) {
                    it[name] = user.name
                }
            }
            getUser(user.id)
        }
    }

    suspend fun addUser(user: User): User {
        var id = 0
        DatabaseFactory.dbQuery {
            id = (Users.insert {
                it[name] = user.name
            } get Users.id)
        }
        return getUser(id)!!
    }

    suspend fun deleteUser(id: Int): Boolean {
        return DatabaseFactory.dbQuery {
            Users.deleteWhere { Users.id eq id } > 0
        }
    }

    private fun toUser(row: ResultRow): User =
            User(id = row[Users.id], name = row[Users.name])
}
