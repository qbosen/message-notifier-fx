package top.abosen.toys.notifierfx.view

import top.abosen.toys.notifierfx.controller.ClientController
import top.abosen.toys.notifierfx.model.ServerModel
import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020/5/8
 */
class ServerView : View("服务端") {
    val clientController: ClientController by inject()
    private val model = ServerModel()


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
                textfield(model.title).required()
            }
            field("content:") {
                tooltip("发送系统通知的内容")
                textfield(model.content).required()
            }

            buttonbar {
                button("Reset").also { tooltip("撤销修改") }.action {
                    model.rollback()
                }
                button("Save").also { tooltip("保存修改") }.action {
                    model.commit {

                    }
                }
            }
        }
    }
}
