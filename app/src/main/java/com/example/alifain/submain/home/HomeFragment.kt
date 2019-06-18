package com.example.alifain.submain.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alifain.detailBarang.ProductDetailActivity

import com.example.alifain.R
import com.example.alifain.adapter.NewProductAdapter
import com.example.alifain.item.BannerCarouselItem
import com.example.alifain.item.BannerListener
import com.example.alifain.model.ImageSlideModel
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiRepository
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), BannerListener, HomeView {


    override fun onSeeAllImageClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onImageClick(image: ImageSlideModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var items: MutableList<Data> = mutableListOf()
    private lateinit var list: RecyclerView
    private lateinit var presenter: HomePresenter
    private var groupAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        list = view.findViewById<RecyclerView>(R.id.new_product_list)
        val apiRepository = ApiRepository()
        presenter = HomePresenter(this,apiRepository)
        presenter.getBarangList()
//        initData()
//        productData()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val images = listOf(
            ImageSlideModel(
                name = "Image 1",
                image = R.drawable.img_1
            ),
            ImageSlideModel(
                name = "Image 2",
                image = R.drawable.img_2
            ),
            ImageSlideModel(
                name = "Image 3",
                image = R.drawable.img_3
            )

        )

        rvMain.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
            isNestedScrollingEnabled = false
        }

        val bannerCarouselItem = BannerCarouselItem(images, childFragmentManager, this)
        groupAdapter.add(bannerCarouselItem)

    }

    override fun showBarang(data: List<Data>) {
        items.addAll(data)
//        list.adapter = NewProductAdapter(context,{ itemBarang : Data -> itemClick(itemBarang)})
        list.adapter = NewProductAdapter(context, items, { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = GridLayoutManager(context, 2)
        (list.adapter as NewProductAdapter).notifyDataSetChanged()


    }

    override fun showLoading() {

    }

    override fun viewLoading() {

    }

    private fun itemClick(item : Data){
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("id_barang",item.id_barang)
        startActivity(intent)
    }
    private fun productData() {
    }

//    private fun initData() {
//        val name = resources.getStringArray(R.array.name_new_product)
//        val image = resources.obtainTypedArray(R.array.image_new_product)
//        val price = resources.getIntArray(R.array.price_new_product)
//
//        items.clear()
//        for (i in name.indices) {
//            items.add(
//                ProductModel(
//                    name[i],
//                    image.getResourceId(i, 0),
//                    price[i]
//                )
//            )
//        }
//
//        //Recycle the typed array
//        image.recycle()
//    }


}
