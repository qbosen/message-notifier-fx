package top.abosen.toys.notifierfx.controller

import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import javafx.util.StringConverter
import top.abosen.toys.notifierfx.model.LimitObservableList
import tornadofx.Controller
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @author qiubaisen
 * @date 2020/5/9
 */

class LogController : Controller() {
    private val logList = LimitObservableList<String>(config.int("log.max_size", 2), LinkedList())
    val logRecordList = SimpleListProperty(logList)
    val logRecordConverter = object : StringConverter<ObservableList<String>>() {
        override fun toString(list: ObservableList<String>?): String = list?.joinToString("\n") ?: ""
        override fun fromString(string: String?): ObservableList<String> = throw UnsupportedOperationException()
    }

    private fun now() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"))
    fun logServer(msg: String) {
        log("${now()} [SERVER]: $msg")
    }

    fun logClient(msg: String) {
        log("${now()} [CLIENT]: $msg")
    }

    fun clear() {
        logRecordList.clear()
    }

    private fun log(msg: String) {
        logRecordList.add(msg)
    }
}