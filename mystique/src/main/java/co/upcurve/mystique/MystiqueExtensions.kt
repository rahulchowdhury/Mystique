package co.upcurve.mystique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extension functions for both platform API and the library API
 */

/**
 * An extension function to the platform's [ViewGroup] to inflate a given
 * layout easily
 *
 * @param layoutId The layout to be inflated
 * @param attachToRoot Should attach the layout to it's root? Can be left blank
 * @return The inflated view
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

/**
 * An extension function to [MystiqueAdapter] to add a list of items
 * to the adapter and notifying the adapter of the same
 *
 * @param items A list of [T] items to be added
 */
fun <T : MystiqueItem> MystiqueAdapter<T>.addItems(items: List<T>) {
    items.isNotEmpty().let {
        mystiqueItems.addAll(items)
        notifyDataSetChanged()
    }
}

/**
 * An extension function to [MystiqueAdapter] to remove a list of items
 * from the adapter and notifying the adapter of the same
 *
 * @param items A list of [T] items to be removed
 */
fun <T : MystiqueItem> MystiqueAdapter<T>.removeItems(items: List<T>) {
    items.isNotEmpty().let {
        mystiqueItems.removeAll(items)
        notifyDataSetChanged()
    }
}

/**
 * An extension function to [MystiqueAdapter] to add a single item to
 * the adapter at a given position (if supplied) otherwise will add at
 * the end of the list and notify the adapter of the same
 *
 * @param item A [T] item to be added
 * @param index Position where the item is to be added (if required)
 */
fun <T : MystiqueItem> MystiqueAdapter<T>.addItem(item: T, index: Int = mystiqueItems.size) {
    mystiqueItems.add(index, item)
    notifyItemInserted(index)
}

/**
 * An extension function to [MystiqueAdapter] to remove a single item from
 * the adapter from a given position (if supplied) otherwise will remove from
 * the end of the list and notify the adapter of the same
 *
 * @param index Position from where the item is to be removed
 */
fun <T : MystiqueItem> MystiqueAdapter<T>.removeItem(index: Int = mystiqueItems.size - 1) {
    mystiqueItems.removeAt(index)
    notifyItemRemoved(index)
}

fun mystify(models: List<Any>, map: MutableMap<Any, Any>, listener: Any? = null): MutableList<MystiqueItemPresenter> {
    val mystiqueItemPresenterList = mutableListOf<MystiqueItemPresenter>()

    models.forEach {
        val mystiqueItemPresenter = map[it::class.java.canonicalName]!!::class.java.newInstance() as? MystiqueItemPresenter
        if (mystiqueItemPresenter != null) {
            mystiqueItemPresenter.loadModel(it)
            mystiqueItemPresenter.setListener(listener)
            mystiqueItemPresenterList.add(mystiqueItemPresenter)
        }
    }

    return mystiqueItemPresenterList
}