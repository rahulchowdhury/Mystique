package co.upcurve.mystiquesample.items

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.upcurve.mystique.MystiqueItemPresenter
import co.upcurve.mystiquesample.R
import co.upcurve.mystiquesample.models.BannerModel

/**
 * Created by rahulchowdhury on 5/29/17.
 */
class BannerItem(var bannerModel: BannerModel = BannerModel()) : MystiqueItemPresenter() {

    @BindView(R.id.heading_banner)
    lateinit var bannerHeading: TextView

    override fun loadModel(model: Any) {
        bannerModel = model as BannerModel
    }

    override fun getModel() = bannerModel

    override fun setListener(listener: Any?) {

    }

    override fun getLayout() = R.layout.view_item_banner

    override fun displayView(itemView: View) {
        ButterKnife.bind(this, itemView)

        bannerHeading.text = bannerModel.heading
    }

    override fun handleClickEvents(itemView: View) {

    }
}