package ci.ashamaz.approvalservice.dto

import java.io.Serializable

data class ApprovalResult (
        val user: User,
        val approved: Boolean
): Serializable