package top.abosen.toys.notifierfx.view

import javafx.scene.Parent
import javafx.scene.control.TabPane
import tornadofx.View
import tornadofx.borderpane
import tornadofx.tabpane

/**
 * @author qiubaisen
 * @date 2020/5/8
 */

class MainScreen : View("消息通知器") {

    override val root: Parent = borderpane {
        top = tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            tab<ServerView>()
            tab<ClientView>()
        }

        right<ToolsView>()

        bottom<LogView>()
    }


    init {
        with(primaryStage) {
            minWidth = 600.0
            minHeight = 300.0
        }


    }
}