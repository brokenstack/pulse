package pw.alk.pulse.network

import android.util.Log
import java.io.IOException

class CustomError(message: String) : Exception(message)

suspend fun getPosts(page: Int, pageSize: Int): Result<List<Post>> {
    try {
        val res = RetrofitBuilder.apiService.getPosts(page, pageSize)
        if (res.isSuccessful) {
            return Result.success(res.body()!!)
        }
    } catch (e: IOException) {
        e.localizedMessage?.let { Log.e("ERR", it) }
        return Result.failure(CustomError("Please check the internet or try again later"))
    } catch (e: Exception) {
        return Result.failure(CustomError(e.localizedMessage!!.toString()))
    }

    return Result.success(emptyList())
}