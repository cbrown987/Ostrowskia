import java.io.File

class fileManagement() {
    // Creates just a file with the username and the subsequent
    fun createFile(num: String, usrName: String): Boolean {
        val fileName = usrName + num
        var file = File(fileName)
        val isNewFileCreated: Boolean = file.createNewFile()
        return isNewFileCreated
    }
    fun createdir(){

    }

}





