package ci.ashamaz.apiservice.model

import java.io.Serializable

data class ApprovalResult (
        val user: User,
        val approved: Boolean
): Serializable