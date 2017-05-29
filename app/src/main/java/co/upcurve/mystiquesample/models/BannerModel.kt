package co.upcurve.mystiquesample.models

import co.upcurve.mystique.Presenter
import co.upcurve.mystiquesample.items.BannerItem

/**
 * Created by rahulchowdhury on 5/29/17.
 */
@Presenter(BannerItem::class)
class BannerModel(var heading: String = "")