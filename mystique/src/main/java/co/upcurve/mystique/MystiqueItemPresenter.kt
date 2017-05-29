package co.upcurve.mystique

/**
 * Created by rahulchowdhury on 5/29/17.
 */
abstract class MystiqueItemPresenter : MystiqueItem {

    abstract fun loadModel(model: Any)

    abstract fun setListener(listener: Any?)
}