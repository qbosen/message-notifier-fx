package top.abosen.toys.notifierfx.model

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.util.*

/**
 * @author qiubaisen
 * @date 2020/5/9
 */
class LimitObservableList<T>(private val maxSize: Int, private val list: LinkedList<T>,
                             private val observable: ObservableList<T> = FXCollections.observableList(list))
    : ObservableList<T> by observable {
    override fun add(element: T): Boolean {
        if (list.size == maxSize) {
            list.removeAt(0)
        }
        observable.add(element)
        return true
    }
}
