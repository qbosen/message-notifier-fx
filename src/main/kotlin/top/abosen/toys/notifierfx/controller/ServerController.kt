package top.abosen.toys.notifierfx.controller

import javafx.beans.property.SimpleBooleanProperty
import top.abosen.toys.notifierfx.model.ServerModel
import tornadofx.Controller

/**
 * @author qiubaisen
 * @date 2020/5/11
 */
class ServerController : Controller() {
    private val logController: LogController by inject()
    val model = ServerModel()
    val running = SimpleBooleanProperty(false)

    fun run() {
        logController.logServer("run ...")
        running.set(true)
    }

    fun stop() {
        logController.logServer("stop ...")
        running.set(false)

    }
}