package org.sakura.project.logic.model

import com.google.gson.annotations.SerializedName

data class KTransResult(
    @SerializedName("log_id") val logId: Long = 0,
    val result: Result = Result()
) {
    data class Result(
        @SerializedName("trans_result") val transResult: List<TransResult> = emptyList(),
        val from: String = "",
        val to: String = ""
    )

    data class TransResult(
        val dst: String = "",
        val src: String = ""
    )
}