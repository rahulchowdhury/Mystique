package co.upcurve.mystiquesample

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import co.upcurve.mystique.MystiqueAdapter
import co.upcurve.mystique.MystiqueItemPresenter
import co.upcurve.mystique.removeItems
import co.upcurve.mystique.toMystifiedList
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

        val heterogeneousModelList = mutableListOf<Any>()
        heterogeneousModelList.add(PostModel(title = "Hello",
                imageUrl = "http://www.idolator.com/wp-content/uploads/sites/10/2015/10/adele-hello.jpg"))
        heterogeneousModelList.add(PostModel(title = "Uptown Funk",
                imageUrl = "http://www.robotbutt.com/wp-content/uploads/2015/01/Uptown-Funk.jpg"))
        heterogeneousModelList.add(BannerModel("Kotlin"))
        heterogeneousModelList.add(PostModel(title = "Cold Water",
                imageUrl = "http://www.idolator.com/wp-content/uploads/sites/10/2016/07/major-lazer-cold-water-620x413.jpg"))

        val mystiqueAdapter = MystiqueAdapter<MystiqueItemPresenter>()
        mystiqueAdapter.setItems(heterogeneousModelList.toMystifiedList(this))

        val newList = mutableListOf<Any>()
        newList.add(PostModel(title = "Uptown Funk",
                imageUrl = "http://www.robotbutt.com/wp-content/uploads/2015/01/Uptown-Funk.jpg"))
        newList.add(PostModel(title = "Cold Water",
                imageUrl = "http://www.idolator.com/wp-content/uploads/sites/10/2016/07/major-lazer-cold-water-620x413.jpg"))

        recyclerView.adapter = mystiqueAdapter

        Handler().postDelayed({
            mystiqueAdapter.removeItems(newList)
        }, 3000)
    }
}
