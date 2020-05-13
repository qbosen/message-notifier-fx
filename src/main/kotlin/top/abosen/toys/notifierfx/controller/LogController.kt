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
private fun now() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"))

class LogController : Controller(), Logger {
    private val logList = LimitObservableList<String>(config.int("log.max_size", 2), LinkedList())
    val logRecordList = SimpleListProperty(logList)
    val logRecordConverter = object : StringConverter<ObservableList<String>>() {
        override fun toString(list: ObservableList<String>?): String = list?.joinToString("\n") ?: ""
        override fun fromString(string: String?): ObservableList<String> = throw UnsupportedOperationException()
    }

    init {
        LogAdapter.logger(this)
    }

    override fun logServer(msg: String) {
        log("${now()} [SERVER]: $msg")
    }

    override fun logClient(msg: String) {
        log("${now()} [CLIENT]: $msg")
    }

    fun clear() {
        logRecordList.clear()
    }

    override fun log(msg: String) {
        logRecordList.add(msg)
    }
}

interface Logger {
    fun logServer(msg: String)
    fun logClient(msg: String)
    fun log(msg: String)
}
object ConsoleLogger : Logger {
    override fun logServer(msg: String) = println("${now()} [SERVER]: $msg")

    override fun logClient(msg: String) = println("${now()} [CLIENT]: $msg")

    override fun log(msg: String) = println("${now()}: $msg")
}

object LogAdapter : Logger {
    private var logger: Logger = ConsoleLogger

    override fun logServer(msg: String) = logger.logServer(msg)

    override fun logClient(msg: String) = logger.logClient(msg)

    override fun log(msg: String) = logger.log(msg)

    fun logger(logger:Logger){
        this.logger = logger
    }
}