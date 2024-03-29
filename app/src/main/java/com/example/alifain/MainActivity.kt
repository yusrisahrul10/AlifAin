package com.example.alifain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.alifain.submain.CartFragment
import com.example.alifain.submain.CategoryFragment
import com.example.alifain.submain.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnv_home -> {
                    loadHomeFragment(savedInstanceState)
                }

                R.id.bnv_category -> {
                    loadCategoryFragment(savedInstanceState)
                }

                R.id.bnv_shopping_cart -> {
                    loadCartFragment(savedInstanceState)
                }
            }

            true
        }

        bottom_navigation.selectedItemId = R.id.bnv_home
    }

    private fun loadHomeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    HomeFragment(), HomeFragment::class.java.simpleName)
                .commit()

        }
    }

    private fun loadCategoryFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    CategoryFragment(), CategoryFragment::class.java.simpleName)
                .commit()

        }
    }

    private fun loadCartFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_layout,
                    CartFragment(), CartFragment::class.java.simpleName)
                .commit()

        }
    }

}
