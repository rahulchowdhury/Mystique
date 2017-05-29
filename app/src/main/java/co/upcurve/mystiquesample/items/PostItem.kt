package co.upcurve.mystiquesample.items

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import co.upcurve.mystique.MystiqueItemPresenter
import co.upcurve.mystiquesample.R
import co.upcurve.mystiquesample.models.PostModel
import com.bumptech.glide.Glide

/**
 * Created by rahulchowdhury on 5/29/17.
 */
class PostItem(var postModel: PostModel = PostModel(), var clickListener: OnItemClickListener? = null) : MystiqueItemPresenter() {

    @BindView(R.id.cover_post)
    lateinit var postCover: ImageView
    @BindView(R.id.title_post)
    lateinit var postTitle: TextView

    override fun loadModel(model: Any) {
        postModel = model as PostModel
    }

    override fun setListener(listener: Any?) {
        clickListener = listener as OnItemClickListener
    }

    override fun getLayout() = R.layout.view_item_post

    override fun displayView(itemView: View) {
        ButterKnife.bind(this, itemView)

        Glide.with(itemView.context).load(postModel.imageUrl).into(postCover)
        postTitle.text = postModel.title
    }

    override fun handleClickEvents(itemView: View) {
        itemView.setOnClickListener {
            clickListener?.onItemClicked(postModel.title)
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(title: String)
    }
}