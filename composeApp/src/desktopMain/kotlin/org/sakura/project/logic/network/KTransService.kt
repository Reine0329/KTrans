package org.sakura.project.logic.network

import org.sakura.project.logic.model.AccessTokenResponse
import org.sakura.project.logic.model.KTransRequest
import org.sakura.project.logic.model.KTransResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface KTransService {
    @POST("oauth/2.0/token")
    fun getAccessToken(
        @Query("grant_type") grantType: String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String
    ): Call<AccessTokenResponse>

    @POST("rpc/2.0/mt/texttrans/v1")
    fun translate(
        @Body request: KTransRequest,
        @Query("access_token") accessToken: String
    ): Call<KTransResult>
}