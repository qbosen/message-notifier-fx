package top.abosen.toys.notifierfx.controller

import javafx.beans.property.SimpleBooleanProperty
import top.abosen.toys.notifierfx.model.ServerModel
import tornadofx.Controller
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author qiubaisen
 * @date 2020/5/11
 */
class ServerController : Controller() {
    private val logController: LogController by inject()
    val model = ServerModel()
    val running = SimpleBooleanProperty(false)

    fun run() {
        logController.logServer("start ...")
        running.set(true)
    }

    fun stop() {
        logController.logServer("stop ...")
        running.set(false)

    }
}


class Server(var cmd: String, var title: String, var content: String, var port: Int) {

    private var running: Boolean = false
    private lateinit var serverSocket: ServerSocket
    private val log = { msg: String -> LogAdapter.logServer(msg) }
    private var worker: Thread = Thread {
        while (running) {
            val socket= serverSocket.accept()
            val br = BufferedReader(InputStreamReader(socket.getInputStream()))
            if (MSG == br.readLine()) {
                log("有消息来了")
                receiveMsg()
            }
        }
    }.apply { isDaemon = true }

    private fun receiveMsg() {
        val builder = ProcessBuilder()
        val timeInfo = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
        builder.command(cmd, content, title, timeInfo)
        builder.start()
    }

    fun update(cmd: String, title: String, content: String, port: Int) {
        this.cmd = cmd
        this.title = title
        this.content = content
        this.port = port
    }


    fun start() {
        if(running) return

        serverSocket = ServerSocket(port)
        log("服务器启动..")
    }

    fun stop() {
        if (!running) {
            return log("尚未启动了..")
        }
    }

    companion object {
        const val MSG = "0xCAFEBABE"
    }
}