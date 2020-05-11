package top.abosen.toys.notifierfx.view


import top.abosen.toys.notifierfx.controller.LogController

import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020/5/8
 */

class LogView : View() {
    private val logController: LogController by inject()

    override val root = vbox {
        label("logger")
        textarea(logController.logRecordList, logController.logRecordConverter) {
            isWrapText = false
            isEditable = false
        }

        button("append") {
            action { logController.logServer("click append!") }
        }
        button("clear") {
            action { logController.clear() }
        }
    }
}
