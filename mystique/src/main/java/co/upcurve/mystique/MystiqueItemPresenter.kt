package co.upcurve.mystique

/**
 * Every item or model presenter must extend this class and implement
 * the given functions for Mystique and [mystify] to work as intended
 */
abstract class MystiqueItemPresenter : MystiqueItem {

    /**
     * Loads a data model to the presenter or item. This function
     * has to be implemented and the supplied model has to be
     * stored in the class extending [MystiqueItemPresenter]
     *
     * @param model The incoming data model to be stored
     */
    abstract fun loadModel(model: Any)

    /**
     * Returns the loaded model (if any) from the item presenter.
     * This function is usually used in the [MystiqueAdapter.removeItems]
     * function to perform model wise comparison while removing an element
     *
     * @return The loaded model (if any)
     */
    abstract fun getModel(): Any?

    /**
     * Any click listener (if supplied) has to be stored in the
     * class extending [MystiqueItemPresenter] by implementing
     * this function
     *
     * @param listener The click listener supplied at initialization
     */
    abstract fun setListener(listener: Any?)
}