package com.example.alifain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.alifain.R
import com.example.alifain.model.cart.Data
import com.example.alifain.model.deletekeranjang.DeleteKeranjangResponse
import com.example.alifain.rest.ApiInterface
import com.example.alifain.rest.ApiRepository
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAdapter(private val context: Context?, private var items: MutableList<Data> = mutableListOf(),
                  private val listener: (Data) -> Unit, private var tvTotalHarga: TextView)
    : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    val apiRepository = ApiRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)

//        presenter = DeleteCartPresenter(context, apiRepository)

        holder.remove.setOnClickListener {
            val id_barang = items.get(position).id_barang
            val id_user = items.get(position).id_user

            Log.e("get id_barang", id_barang)
            Log.e("get id_user", id_user)

            deleteCart(id_user, id_barang)
            items.removeAt(position)
        }
//
//        btnCart.setOnClickListener {
////            if (items.size == 0) {
////                Toast.makeText(context, "Keranjang Anda kosong. Silakan pilih barang yang ingin dibeli", Toast.LENGTH_SHORT).show()
////            }
//        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.judulCart)
        private val image = view.findViewById<ImageView>(R.id.imgCart)
        private val price = view.findViewById<TextView>(R.id.hargaCart)
        private val desc = view.findViewById<TextView>(R.id.descCart)
        private val qty = view.findViewById<TextView>(R.id.qtyCart)
        val remove = view.findViewById<ImageButton>(R.id.btnRemove)

        fun bindItem(items : Data, listener: (Data) -> Unit) {
            name.text = items.nama_barang
            desc.text = items.deskripsi
            qty.text = "Jumlah " + items.qty.toString()
            items.nama_gambar?.let { Picasso.get().load("http://alifain.dscunikom.com/uploads/barang/"+it).into(image) }
            price.text = "Subtotal: Rp. "+ items.subtotal



            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    private fun deleteCart(id_user : String, id_barang: String) {

        val connect : ApiInterface = apiRepository.getUrl().create(ApiInterface::class.java)
        connect.deleteKeranjang(id_user, id_barang).enqueue(object : Callback<DeleteKeranjangResponse> {
            override fun onFailure(call: Call<DeleteKeranjangResponse>, t: Throwable) {
                Log.e("gagal", t.message)
            }

            override fun onResponse(call: Call<DeleteKeranjangResponse>, response: Response<DeleteKeranjangResponse>) {
                val push : String? = response.body()?.messaage
                val harga : Int? = response.body()?.total_harga
                tvTotalHarga.text = "Rp. " + harga

                notifyDataSetChanged()
                Log.e("size item", items.size.toString())
//                  og.e("DELETE", "RESPONE CART $push")
            }

        })
    }

}