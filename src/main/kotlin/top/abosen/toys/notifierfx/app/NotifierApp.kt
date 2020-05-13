package top.abosen.toys.notifierfx.app

import top.abosen.toys.notifierfx.controller.LogAdapter
import top.abosen.toys.notifierfx.view.MainScreen
import tornadofx.App
import tornadofx.launch

/**
 * @author qiubaisen
 * @date 2020/5/8
 */

fun main(args: Array<String>) {
    launch<NotifierApp>(args)
}

class NotifierApp : App(MainScreen::class, Styles::class)