package rockets

import com.google.gson.Gson

data class Rocket(val id: Long, val rocket_id: String, val rocket_name: String) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Rocket {
            println("Parsing: [$json]")
            return Gson().fromJson(json, Rocket::class.java)
        }
    }
}