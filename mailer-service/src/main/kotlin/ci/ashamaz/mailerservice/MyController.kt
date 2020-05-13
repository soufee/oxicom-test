package ci.ashamaz.mailerservice

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {

    @GetMapping("/")
    fun hello(model: Model): String?{
        return "index"
    }
}