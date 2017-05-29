package co.upcurve.mystiquesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.upcurve.mystique.MystiqueAdapter
import co.upcurve.mystique.MystiqueItemPresenter
import co.upcurve.mystique.mystify
import co.upcurve.mystiquesample.items.PostItem
import co.upcurve.mystiquesample.models.BannerModel
import co.upcurve.mystiquesample.models.PostModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PostItem.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    override fun onItemClicked(title: String) {
        println("Song Title: $title")
    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        val postModelList = mutableListOf<Any>()
        postModelList.add(PostModel(title = "Hello",
                imageUrl = "http://www.idolator.com/wp-content/uploads/sites/10/2015/10/adele-hello.jpg"))
        postModelList.add(PostModel(title = "Uptown Funk",
                imageUrl = "http://www.robotbutt.com/wp-content/uploads/2015/01/Uptown-Funk.jpg"))
        postModelList.add(BannerModel("Kotlin"))
        postModelList.add(PostModel(title = "Cold Water",
                imageUrl = "http://www.idolator.com/wp-content/uploads/sites/10/2016/07/major-lazer-cold-water-620x413.jpg"))

        val mystiqueAdapter = MystiqueAdapter<MystiqueItemPresenter>()
        mystiqueAdapter.setItems(mystify(postModelList, this))

        recyclerView.adapter = mystiqueAdapter
    }
}
