package com.khalid.core_data.utils

sealed class NetworkError(open val code: Int, open val defaultMessage: String) {
    data object Network : NetworkError(1000, "Network is unreachable")
    data object Timeout : NetworkError(2000, "Request timed out")
    data object Cancelled : NetworkError(3000, "Request was cancelled")
    data class Http(
        override val code: Int,
        override val defaultMessage: String,
    ) : NetworkError(code, defaultMessage)

    data object Unknown : NetworkError(4000, "Unknown error") {
        init {
            reportAnIssue()
        }

        private fun reportAnIssue() {
            // Report an issue to firebase
        }
    }
}
