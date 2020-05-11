package top.abosen.toys.notifierfx.view


import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.Modality
import javafx.stage.StageStyle

import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020/5/8
 */

class ClientView : View("客户端") {
    val host = SimpleStringProperty(this, "host", config.string("host"))
    val port = SimpleIntegerProperty(this, "port", config.int("port", 13379))

    override val root = form {
        fieldset("客户端配置") {
            field("服务器ip") { textfield(host) }
            field("服务器端口") { textfield(port) }

            buttonbar {
                button("保存").action {
                    with(config) {
                        set("host" to host.value)
                        set("port" to port.value)
                    }
                }
            }
        }
        buttonbar {
             button("截图"){
                action {
                    primaryStage.hide()
                    PrintScreenView.show()
                }

            }
        }
    }
}

