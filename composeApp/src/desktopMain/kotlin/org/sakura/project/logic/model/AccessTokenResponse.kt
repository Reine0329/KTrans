package org.sakura.project.logic.model

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token") val accessToken: String = "",
    @SerializedName("expires_in") val expiresIn: Long = 0,
    @SerializedName("refresh_token") val refreshToken: String = "",
    val scope: String = "",
    @SerializedName("session_key") val sessionKey: String = "",
    @SerializedName("session_secret") val sessionSecret: String = ""
)