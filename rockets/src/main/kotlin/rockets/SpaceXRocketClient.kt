package rockets

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe

@Client("https://api.spacexdata.com/v3")
interface SpaceXRocketClient {

    @Get("/rockets/{rocketId}")
    fun getRocketById(rocketId: String): Maybe<Rocket>
}