package ulht.cm.acalculator.ui.login

class User {

    var token: String = ""

    companion object {

        private var instance: User? = null

        fun getInstance(): User {
            if (instance == null) {
                instance = User()
            }
            return instance as User
        }
    }
}