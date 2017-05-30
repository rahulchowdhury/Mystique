package co.upcurve.mystique

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass

/**
 * Extension functions for both platform API and the library API
 */

/**
 * Read the an annotation named [Presenter] if present on the given class
 * and return it's value
 *
 * @param clazz The class to be read
 * @return The value of the [Presenter] annotation, i.e., the supplied class
 */
private fun getPresenterClassName(clazz: KClass<*>): KClass<*>? {
    val presenter = clazz.annotations.find { it is Presenter } as? Presenter
    return presenter?.presenterClass
}

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
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.addItems(items: List<T>) {
    items.isNotEmpty().let {
        mystiqueItems.addAll(items)
        notifyDataSetChanged()
    }
}

/**
 * An extension function to [MystiqueAdapter] to remove a list of items
 * from the adapter and notifying the adapter of the same
 *
 * @param items A list of item models (not presenters) to be removed
 */
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.removeItems(items: List<Any>) {
    items.isNotEmpty().let {
        items.forEach {
            val itemModel = it
            var itemToRemove: MystiqueItemPresenter? = null

            mystiqueItems.forEach innerLoop@ {
                val mystiqueItemModel = it.getModel()

                if (mystiqueItemModel != null && itemModel == mystiqueItemModel) {
                    itemToRemove = it
                    return@innerLoop
                }
            }

            itemToRemove?.let {
                mystiqueItems.remove(it)
            }
        }
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
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.addItem(item: T, index: Int = mystiqueItems.size) {
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
fun <T : MystiqueItemPresenter> MystiqueAdapter<T>.removeItem(index: Int = mystiqueItems.size - 1) {
    mystiqueItems.removeAt(index)
    notifyItemRemoved(index)
}

/**
 * This function is the core of mystifying a model. It takes in a
 * regular model and magically turns it into a [MystiqueItemPresenter]
 *
 * Cutting the crap talk and getting technical, this function takes a
 * regular model of [Any] type, reads the [Presenter] annotation from
 * its class definition and wraps this model into the appropriate
 * presenter which is then returned to the caller
 *
 * This function also takes in an optional listener parameter to attach
 * it to the supplied model. The listener can be anything which
 * listens to the click events on the presenter, for example, an activity
 * or fragment or view.
 *
 * Use this function when you need to convert a single model into a
 * [MystiqueItemPresenter]
 *
 * @param model A model of [Any] type to be converted
 * @param listener An optional listener to attach to the model
 * @return A [MystiqueItemPresenter] initialized with the params
 */
fun mystify(model: Any, listener: Any? = null): MystiqueItemPresenter? {
    val mystiqueItemPresenter = getPresenterClassName(model::class)?.java?.newInstance() as? MystiqueItemPresenter
    mystiqueItemPresenter?.let {
        mystiqueItemPresenter.loadModel(model)
        mystiqueItemPresenter.setListener(listener)
        return it
    }
    return null
}

/**
 * This is where all the mystic magic happens. When used properly, can save
 * lots of effort and time, so use it whenever you are initializing a [MystiqueAdapter]
 * with a list of data models, so that each model gets mapped to their respective
 * presenters or item holders, automatically
 *
 * Inside the hood, this function takes in a list of data models and maps
 * to their respective presenters or item holders as specified by the
 * annotation [Presenter] on each model class.
 *
 * Also, this function takes in an optional parameter to attach a click
 * listener to the presenters for each model in the list which can be a
 * reference to an Activity, Fragment, View, etc which handles the click
 * for the whole row of inflated data or individual items as might be
 * defined tailored to the purpose
 *
 * @param models A list of data models of [Any] type
 * @param listener An optional listener to attach to each model
 * @return A mutable list of [MystiqueItemPresenter] to be consumed by a [MystiqueAdapter]
 */
fun mystifyList(models: List<Any>, listener: Any? = null): MutableList<MystiqueItemPresenter> {
    val mystiqueItemPresenterList = mutableListOf<MystiqueItemPresenter>()

    models.forEach {
        val mystiqueItemPresenter = mystify(it, listener)
        mystiqueItemPresenter?.let {
            mystiqueItemPresenterList.add(mystiqueItemPresenter)
        }
    }

    return mystiqueItemPresenterList
}

/**
 * An extension function on platform API of [Any] to add the feature of
 * converting a regular [Any] object to a mystified item, which is nothing
 * but a [MystiqueItemPresenter]
 *
 * Use this function when converting a regular model to a mystified item
 * for use with the [MystiqueAdapter] functions
 *
 * @param listener The listener (if any) to add to the item presenter
 * @return A [MystiqueItemPresenter] object initialized with supplied params
 */
fun Any.toMystifiedItem(listener: Any? = null) = mystify(this, listener)

/**
 * An extension function on platform API of [List] to add the feature of
 * converting a regular [List] or [MutableList] to a mystified list, which
 * is nothing but a mutable list of [MystiqueItemPresenter]
 *
 * Use this function when initializing a [MystiqueAdapter] with a list of
 * data models
 *
 * @param listener The listener (if any) to add to each item presenter in the list
 * @return A mutable list of [MystiqueItemPresenter] objects
 */
fun List<Any>.toMystifiedList(listener: Any? = null) = mystifyList(this, listener)