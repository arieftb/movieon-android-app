package id.my.arieftb.movieon.utils

object FileReadHelper {
    fun readTestResourceFile(fileName: String?): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }
}