package top.abosen.toys.notifierfx.view


import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020/5/8
 */

class ConfigScreen : View() {
    val host = SimpleStringProperty(this, "host", config.string("host"))
    val port = SimpleIntegerProperty(this, "port", config.int("port", 13379))

    override val root = form {
        fieldset("客户端配置") {
            field("服务器ip") { textfield(host) }
            field("服务器端口") { textfield(port) }

            buttonbar {
                button("保存").action {
                    with(config){
                        set("host" to host.value)
                        set("port" to port.value)
                    }
                }
            }
        }

    }
}

