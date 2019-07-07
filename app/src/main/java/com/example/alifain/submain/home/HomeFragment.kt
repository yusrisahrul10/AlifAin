package com.example.alifain.submain.home


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.alifain.detailBarang.ProductDetailActivity

import com.example.alifain.R
import com.example.alifain.adapter.AllProductAdapter
import com.example.alifain.item.BannerCarouselItem
import com.example.alifain.item.BannerListener
import com.example.alifain.model.ImageSlideModel
import com.example.alifain.model.barang.Data
import com.example.alifain.rest.ApiRepository
import com.mlsdev.animatedrv.AnimatedRecyclerView
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
    private var filteredItems: MutableList<Data> = mutableListOf()
    private lateinit var list: AnimatedRecyclerView
    private lateinit var presenter: HomePresenter
    private var groupAdapter = GroupAdapter<ViewHolder>()

    private lateinit var search : SearchView

    private lateinit var progressBar : ProgressBar
    private lateinit var tvKosong : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        list = view.findViewById(R.id.all_product_list)

        progressBar =view.findViewById(R.id.progress_bar_home)
        tvKosong = view.findViewById(R.id.tv_kosong_home)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()

        search = view.findViewById(R.id.search_product)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                searchProduk(s)
                return true
            }

            override fun onQueryTextChange(s: String?): Boolean {
                searchProduk(s)
                return true
            }

        })
        presenter = HomePresenter(this,apiRepository)
        presenter.getBarangList()
    }

    fun searchProduk(keyword : String?) {
         val filteredList : MutableList<Data> = mutableListOf()
         for (s in filteredItems) {
            if (s.nama_barang.toLowerCase().contains(keyword!!.toLowerCase())) {
                filteredList.add(s)
            }
         }
         if (filteredList.size == 0) {

         } else {
            list.visibility = View.VISIBLE
            val adapter = AllProductAdapter(context, filteredList, { itemMatch: Data -> itemClick(itemMatch) })
            list.adapter = adapter
            rvMain.visibility = View.GONE

         }

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
        filteredItems.addAll(data)
        list.adapter = AllProductAdapter(context, items, { itemMatch: Data -> itemClick(itemMatch) })
        list.layoutManager = GridLayoutManager(context, 2)
        (list.adapter as AllProductAdapter).notifyDataSetChanged()
        list.scheduleLayoutAnimation()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        tvKosong.visibility = View.GONE
        list.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun itemClick(item : Data){
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("id_barang",item.id_barang)
        startActivityForResult(intent, 101)
    }

    override fun showBarangFailed(message: String) {
        val message = "Tidak dapat memproses permintaan Anda karena kesalahan koneksi atau data kosong. Silakan coba lagi"
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        progressBar.visibility = View.GONE
        tvKosong.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

}
