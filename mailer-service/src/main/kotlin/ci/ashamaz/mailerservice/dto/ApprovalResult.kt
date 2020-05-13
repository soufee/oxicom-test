package ci.ashamaz.mailerservice.dto

import java.io.Serializable

data class ApprovalResult (
        val user: User,
        val approved: Boolean
): Serializable