package rockets

import com.google.gson.JsonSyntaxException
import io.lettuce.core.api.StatefulRedisConnection
import javax.inject.Singleton

@Singleton
class RocketRepository(private val rocketClient: SpaceXRocketClient, private val redis : StatefulRedisConnection<String, String>) {

    internal fun getRocketFromCache(id: String): Rocket? {
        val json: String? = redis.sync().get(id)

        if(json != null) {
            try {
                return Rocket.fromJson(json)
            } catch (ex: JsonSyntaxException) {
                println("Invalid data in redis, flushing...")
                redis.sync().flushall()
            }

        }

        return null
    }

    internal fun setRocketInCache(id: String, rocket: Rocket) {
        redis.sync().set(id, rocket.toJson())
    }

    fun GetRocketById(id: String): Rocket? {

        println("Checking Redis...")

        var rocket: Rocket? = getRocketFromCache(id)

        if(rocket == null) {
            println("Getting it from SpaceX")
            rocket = rocketClient.getRocketById(id).blockingGet()
            setRocketInCache(id, rocket)
        } else {
            println("Got it from Redis")
        }

        return rocket
    }
}