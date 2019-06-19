package com.example.alifain.submain


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alifain.R
import com.example.alifain.adapter.CategoryAdapter
import com.example.alifain.category.DetailCategoryActivity
import com.example.alifain.model.CategoryModel

class CategoryFragment : Fragment() {

    private var items: MutableList<CategoryModel> = mutableListOf()


    companion object {
        var PUTEXTRA_CLUB = "putextra_club"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        val list = view.findViewById<RecyclerView>(R.id.category_list)
        initData()

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = CategoryAdapter(context, items) {
            val intent = Intent(context, DetailCategoryActivity::class.java)
            intent.putExtra("id", CategoryModel(it.name,it.image))
            startActivity(intent)
        }

        return view
    }


    private fun initData(){
        val name = resources.getStringArray(R.array.category_name)
        val image = resources.obtainTypedArray(R.array.category_image)
        items.clear()
        for (i in name.indices) {
            items.add(
                CategoryModel(name[i],
                image.getResourceId(i, 0))
            )
        }

        //Recycle the typed array
        image.recycle()
    }

}
