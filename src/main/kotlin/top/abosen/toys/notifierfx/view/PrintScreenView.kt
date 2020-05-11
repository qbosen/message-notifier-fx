package top.abosen.toys.notifierfx.view

import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.Background
import javafx.scene.paint.Paint
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

/**
 * @author qiubaisen
 * @date 2020-05-11
 */
object PrintScreenView {
    val stage = Stage()

    init {
        with(stage) {

            scene = Scene(anchorpane {
                prefHeight = Screen.getPrimary().bounds.maxY
                prefWidth = Screen.getPrimary().bounds.maxX
                style { backgroundColor = multi(c("#B5B5B522")) }
            }).apply {
                fill = c("#FFFFFF00")
                onKeyPressed = EventHandler {
                    if (it.code == KeyCode.ESCAPE) {
                        close()
                        FX.primaryStage.show()
                    }
                }
            }
            isFullScreen = true
            fullScreenExitHint = "ESC取消,双击确认"
            initStyle(StageStyle.TRANSPARENT)
        }
    }

    fun show() = stage.show()


}
