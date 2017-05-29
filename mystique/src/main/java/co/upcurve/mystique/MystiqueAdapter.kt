package co.upcurve.mystique

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * A generic RecyclerView adapter which can inflate items of different layouts
 * in the same RecyclerView without much effort
 *
 * @param T An item class which extends [MystiqueItemPresenter]
 */
class MystiqueAdapter<T : MystiqueItemPresenter> : RecyclerView.Adapter<MystiqueViewHolder>() {

    /**
     * A [MutableList] of items which extends [MystiqueItemPresenter], ready to be
     * inflated to the RecyclerView by this adapter
     */
    val mystiqueItems: MutableList<T> = mutableListOf()

    /**
     * The Adapter's normal onCreateViewHolder function with the only difference
     * being that the view type here directly supplies the resource ID of the layout
     * to be inflated instead of an integer identifier to map to the specific layout
     *
     * @param parent The ViewGroup where the new layout is to be inflated
     * @param viewTypeLayoutRes The resource ID of the layout to be inflated
     * @return A [MystiqueViewHolder] object with the inflated view
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewTypeLayoutRes: Int) =
            MystiqueViewHolder(parent?.inflate(viewTypeLayoutRes))

    /**
     * The Adapter's normal onBindViewHolder function which delegates the
     * binding to the appropriate [MystiqueItemPresenter] implementation instead of
     * trying to put in any specific sort of implementation in here
     *
     * @see MystiqueItem.displayView        Views should be populated in this method
     * @see MystiqueItem.handleClickEvents  Click listeners should be attached here
     */
    override fun onBindViewHolder(holder: MystiqueViewHolder?, position: Int) {
        val item: T? = getItem(position)

        if (holder != null && item != null) {
            /**
             * Using Kotlin's Smart cast feature we can now make a safe call
             * to the methods of [MystiqueViewHolder] without specifying an
             * explicit safe call indicator
             */
            holder.handleItem(item)
            holder.attachClickListener(item)
        }
    }

    /**
     * This function returns the specific layout to be inflated for an item
     * and if no layouts are found or no items are found then returns an
     * empty layout as a fail-safe technique
     *
     * @param position The index of the item whose layout needs to be returned
     * @return A layout resource ID for inflation
     */
    override fun getItemViewType(position: Int) = getItem(position)?.getLayout() ?: R.layout.default_layout

    /**
     * Returns the size of the list of items to be populated in the
     * RecyclerView
     *
     * @return The size of the list of items in this adapter
     */
    override fun getItemCount() = mystiqueItems.size

    /**
     * Returns an item for the given index (if present) otherwise returns null
     *
     * @return A [T] type object based on the specified index
     */
    private fun getItem(position: Int) = if (position < mystiqueItems.size) mystiqueItems[position] else null

    /**
     * Initializes the adapter with a given list of [T] items
     *
     * This function should be called when the adapter is initialized
     * with a set of items to be populated
     *
     * @param items A list of [T] items to be added to inflated in the RecyclerView
     */
    fun setItems(items: MutableList<T>) {
        items.isNotEmpty().let {
            mystiqueItems.clear()
            mystiqueItems.addAll(items)
            notifyDataSetChanged()
        }
    }
}