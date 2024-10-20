/*
package AAA.BBB.CCC
*/
import android.content.Context
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class TinyConf(val context: Context, val filename: String) {
    private val confMap = mutableMapOf<String,String>()

    fun loadConf() {
        try {
            context.openFileInput(filename).use { fis ->
                val br = BufferedReader(InputStreamReader(fis))
                br.forEachLine {
                    if (it.contains('\t')) {
                        val line = it.split('\t')
                        confMap[line[0]] = line[1]
                    }
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveConf() {
        val sb = StringBuilder()
        confMap.forEach {
            sb.append("${it.key}\t${it.value}\n")
        }
        try {
            context.openFileOutput(filename, Context.MODE_PRIVATE).use {fos->
                BufferedWriter(OutputStreamWriter(fos)).use {
                    it.write(sb.toString())
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun r(key: String, default: String = ""): String = confMap[key] ?: default

    fun w(key: String, value: String) {
        confMap[key] = value
        saveConf()
    }
}