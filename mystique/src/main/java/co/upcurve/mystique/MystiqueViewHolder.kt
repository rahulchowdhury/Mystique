package co.upcurve.mystique

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * A generic view holder to with the purpose of delegating the view and click binding
 * calls to the respective [MystiqueItemPresenter] with their inflated views
 */
class MystiqueViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    /**
     * This function is called by the adapter from onBindViewHolder() function for
     * each row of the list.
     *
     * This method just calls the supplied [MystiqueItemPresenter]'s display function
     * with the current inflated view as supplied by the [MystiqueAdapter]
     */
    fun handleItem(mystiqueItem: MystiqueItem) = mystiqueItem.displayView(itemView)

    /**
     * This function is called by the adapter from onBindViewHolder(0 function for
     * each row of the list
     *
     * This method just calls the supplied [MystiqueItemPresenter]'s handle click events
     * function with the current inflated view as supplied by the [MystiqueAdapter]
     */
    fun attachClickListener(mystiqueItem: MystiqueItem) = mystiqueItem.handleClickEvents(itemView)
}