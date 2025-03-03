package org.sakura.project.logic.network

import org.sakura.project.logic.model.AccessTokenResponse
import org.sakura.project.logic.model.KTransRequest
import org.sakura.project.logic.model.KTransResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AppNetwork {
    private val kTransService: KTransService by lazy {
        KServiceCreator.create()
    }

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    response.body()?.let {
                        continuation.resume(it)
                    } ?: continuation.resumeWithException(
                        RuntimeException("Response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }

    suspend fun translate(request: KTransRequest, accessToken: String): KTransResult {
        return kTransService.translate(request, accessToken).await()
    }

    suspend fun getAccessToken(grantType: String, clientId: String, clientSecret: String): AccessTokenResponse {
        return kTransService.getAccessToken(grantType, clientId, clientSecret).await()
    }
}