package co.upcurve.mystique

import android.support.annotation.LayoutRes
import android.view.View

/**
 * Every row should be modelled by implementing this interface and
 * using the supplied methods to perform view population, click
 * handling, etc
 *
 * By convention, it's recommended to use names for implementing
 * classes as ClassNameItem, like PostItem, NewsItem, etc
 */
interface MystiqueItem {

    /**
     * Should return the layout to the used by the item when it needs
     * to be inflated
     *
     * @return The layout resource ID
     */
    @LayoutRes
    fun getLayout(): Int

    /**
     * All the view bindings and data loading to the views should be
     * done in this function
     *
     * @param itemView The inflated view for a row in the list
     */
    fun displayView(itemView: View)

    /**
     * Handling of click events of whether the whole row or
     * individual elements within the inflated row should be
     * done using this function.
     *
     * Use the supplied view to access it's children in case of
     * setting click listeners for individual items within the row
     *
     * @param itemView The inflated view for a row in the list
     */
    fun handleClickEvents(itemView: View)
}