package ci.ashamaz.approvalservice.validate

import ci.ashamaz.approvalservice.dto.User
import org.springframework.stereotype.Component

@Component
class Validator {
    /**
     * Логика валидации рандомная. Это заглушка
     * */
    fun validate(user: User): Boolean {
        return user.hashCode() % 2 == 0
    }
}