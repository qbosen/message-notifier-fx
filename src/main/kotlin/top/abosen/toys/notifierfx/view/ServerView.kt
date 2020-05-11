package top.abosen.toys.notifierfx.view


import top.abosen.toys.notifierfx.controller.LogController
import top.abosen.toys.notifierfx.controller.ServerController
import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020/5/8
 */
class ServerView : View("服务端") {
    private val logController: LogController by inject()
    private val serverController: ServerController by inject()

    private val model = serverController.model


    override val root = form {
        fieldset {
            field("port:") {
                tooltip("服务端开放端口以接受消息")
                textfield(model.port).stripNonInteger()
            }
            field("interval:") {
                tooltip("服务端提示消息的最小时间间隔（秒）")
                textfield(model.interval).stripNonInteger()
            }
            field("title:") {
                tooltip("发送系统通知的标题")
                textfield(model.title).trimWhitespace()
            }
            field("content:") {
                tooltip("发送系统通知的内容")
                textfield(model.content).trimWhitespace()
            }

            buttonbar {
                button("Reset").also { tooltip("撤销修改") }.action {
                    model.rollback()
                    logController.logServer("Cancel modification!")
                }
                button("Save").also { tooltip("保存修改") }.action {
                    model.commit {
                        logController.logServer("Save modification!")
                    }
                }
            }
        }

        buttonbar {
            button("Run") {
                disableWhen(serverController.running)
                action {
                    serverController.run()
                }
            }

            button("Stop") {
                disableWhen(!serverController.running)
                action {
                    serverController.stop()
                }
            }
        }
    }
}
