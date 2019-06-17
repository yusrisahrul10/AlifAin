package com.example.alifain


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_banner.*


class BannerFragment : Fragment() {

    companion object {
        /**
         * new instance pattern for fragment
         */
        @JvmStatic
        fun newInstance(url: Int): BannerFragment {
            val bannerFragment = BannerFragment()
            val args = Bundle()
            args.putInt("img", url)
            bannerFragment.arguments = args
            return bannerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_banner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments!!.getInt("img")

        url.let {
            Picasso.get().load(url).into(imgBanner)
        }
    }
}
