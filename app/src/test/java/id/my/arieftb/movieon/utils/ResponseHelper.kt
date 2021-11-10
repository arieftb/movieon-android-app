package id.my.arieftb.movieon.utils

import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object ResponseHelper {
    fun <R : Any?> createDummySuccessResponse(
        responseFileName: String?,
        classOf: Class<out R>
    ): Response<R> {
        val dummyResponse = if (responseFileName != null) {
            Gson().fromJson<R>(FileReadHelper.readTestResourceFile(responseFileName), classOf)
        } else {
            null
        }

        println("MeowViewTag : response -> ${Gson().toJson(dummyResponse)}")
        return Response.success(dummyResponse)
    }

    fun <R : Any?> createDummyErrorResponse(
        responseFileName: String?, statusCode: Int = 500,
        classOf: Class<out R>
    ): Response<R> {

        val dummyResponse = if (responseFileName != null) {
            Gson().fromJson<R>(FileReadHelper.readTestResourceFile(responseFileName), classOf)
        } else {
            null
        }

        if (responseFileName.isNullOrEmpty()) {
            return Response.error(
                statusCode,
                "Test Server Error".toResponseBody("text/plain".toMediaTypeOrNull())
            )
        }

        //Init Response
        return Response.error(
            statusCode, Gson().toJson(dummyResponse)
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
    }
}