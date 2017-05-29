package co.upcurve.mystiquesample.models

import co.upcurve.mystique.Presenter
import co.upcurve.mystiquesample.items.PostItem

/**
 * Created by rahulchowdhury on 5/29/17.
 */
@Presenter(PostItem::class)
data class PostModel(var title: String = "", var description: String = "", var imageUrl: String = "")