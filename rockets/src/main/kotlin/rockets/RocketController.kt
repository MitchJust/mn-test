package rockets

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/rocket")
class RocketController(private val rocketRepository: RocketRepository) {

    @Get("/{id}")
    fun getRocketById(id: String): Rocket? {
        return rocketRepository.GetRocketById(id)
    }
}