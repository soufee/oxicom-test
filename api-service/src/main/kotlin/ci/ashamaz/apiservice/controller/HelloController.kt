package ci.ashamaz.apiservice.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
/**
 * Контроллер для навигации между страницами на фронте
 * */
@Controller
class HelloController {

    @GetMapping("/")
    fun hello(model: Model): String?{
        return "index"
    }
    @GetMapping("/application")
    fun application(model: Model): String?{
        return "application"
    }
    @GetMapping("/check")
    fun check(model: Model): String?{
        return "check"
    }
}