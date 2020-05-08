package top.abosen.toys.notifierfx.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

/**
 * @author qiubaisen
 * @date 2020/5/9
 */

data class ServerConfig(
        val port: Int, val notifyInterval: Int,
        val content: String, val title: String)

data class ClientConfig(
        val host: String, val port: Int,
        val x: Int, val y: Int,
        val width: Int, val height: Int,
        val thresholdR: Int, val thresholdG: Int, val thresholdB: Int,
        val scanPeriod: Int, val samplePeriod: Int, val sampleTimes: Int
)


class ServerModel : ItemViewModel<ServerConfig>() {
    private val KEY_PORT = "server.port"
    private val KEY_INTERVAL = "server.interval"
    private val KEY_CONTENT = "server.content"
    private val KEY_TITLE = "server.title"

    val port = bind { SimpleIntegerProperty(item?.port, "", config.int(KEY_PORT, 13379)) }
    val interval = bind { SimpleIntegerProperty(item?.notifyInterval, "", config.int(KEY_INTERVAL, 10)) }
    val content = bind { SimpleStringProperty(item?.content, "", config.string(KEY_CONTENT, "收到一条消息!!!")) }
    val title = bind { SimpleStringProperty(item?.title, "", config.string(KEY_TITLE, "消息通知")) }


    override fun onCommit() {
        with(config) {
            set(KEY_PORT to port.value)
            set(KEY_INTERVAL to interval.value)
            set(KEY_CONTENT to content.value)
            set(KEY_TITLE to title.value)
            save()
        }
    }
}

