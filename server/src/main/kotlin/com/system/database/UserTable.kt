package com.system.database

// Create UserTableDTO data class file and refactor this into it. rename User to UserTableDTO
data class User(val id: Int, var firstName: String, var lastName: String,var userName: String, var email: String, var password: String, var phoneNumber: String)

// Put these in UserTableError file
class UserCreatingException(val id: Int, message: String) : Exception(message)
class UserNotFoundException(val id: Int, message: String) : Exception(message)

// Keep this in here
class UserTable {
    private val users = mutableListOf<User>()

    fun add(user: User) {
        if (users.any { it.id == user.id }) {
            throw UserCreatingException(user.id, "User with id '${user.id}' already exists")
        }
        users.add(user)
    }

    fun get(id: Int): User? {
        return users.find { it.id == id }
    }

    fun getAll(): List<User> {
        return users.toList()
    }

    fun update(id: Int, newUsername: String) {
        val user = users.find { it.id == id }
            ?: throw UserNotFoundException(id,"User with id '$id' does not exist")
        user.userName = newUsername
    }

    fun delete(id: Int) {
        if (!users.removeIf { it.id == id }) {
            throw UserNotFoundException(id, "User with id '${id}' not exists")
        }
        users.removeIf { it.id == id }
    }

}
