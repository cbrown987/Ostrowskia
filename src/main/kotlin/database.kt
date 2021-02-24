import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection


// How to do this shit AGHHHHHh https://github.com/JetBrains/Exposed/wiki/Getting-Started
// i should use this tho https://cashapp.github.io/sqldelight/native_sqlite/
fun addToDatabase(hashedPassword: String,usrName: String, usrNumber: Int) {
    Database.connect("jdbc:sqlite:/Users/cooperbrown/Desktop/Projects/Ostrowskia/src/main/resources/data_db.sqlite", "org.sqlite.JDBC")
    TransactionManager.manager.defaultIsolationLevel =
        Connection.TRANSACTION_SERIALIZABLE
    transaction {

        val newUser = userDatabase.new {
            name = usrName
            sequelId = usrNumber
            usrPassword = hashedPassword
        }
    }

}

object usrDB : IntIdTable() {
    val sequelId: Column<Int> = integer("sequel_id").uniqueIndex()
    val name: Column<String> = varchar("name", 50)
    val usrPassword: Column<String> = varchar("director", 50)
}


class userDatabase(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<userDatabase>(usrDB)
    var sequelId by usrDB.sequelId
    var name     by usrDB.name
    var usrPassword by usrDB.usrPassword
}
