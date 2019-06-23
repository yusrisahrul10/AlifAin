package com.example.alifain.detailtransaksi

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.example.alifain.MainActivity
import com.example.alifain.R
import com.example.alifain.adapter.DetailTransaksiAdapter
import com.example.alifain.model.detailtransaksi.Data
import com.example.alifain.model.detailtransaksi.Total
import com.example.alifain.preference.MyPreference
import com.example.alifain.rest.ApiRepository
import com.example.alifain.transaksilist.TransaksiListActivity

class DetailTransaksiActivity : AppCompatActivity(), DetailTransaksiView {

    private lateinit var tvHargaBarang : TextView
    private lateinit var tvStatusPesanan : TextView
    private lateinit var tvNoResi : TextView
    private lateinit var tvAlamatPengiriman : TextView
    private lateinit var tvTotalHargaBarang : TextView
    private lateinit var tvTotalOngkir: TextView
    private lateinit var tvTotalBayar: TextView

    private lateinit var edtTxtNama : EditText
    private lateinit var edtTxtBank : EditText
    private lateinit var edtTxtTransfer : EditText
    private lateinit var edtTxtTgl : EditText

    private lateinit var btnSubmitPengiriman : Button
    private lateinit var btnDiterima : Button

    private var items: MutableList<Data> = mutableListOf()
    private lateinit var list: RecyclerView
    private lateinit var myPreference: MyPreference
    private lateinit var presenter: DetailTransaksiPresenter
//    private lateinit var total: Total

    lateinit var id_transaksi: String

    private lateinit var cvBayar : CardView
    private lateinit var llBukti : LinearLayout

    var status = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_transaksi)

        tvHargaBarang = findViewById(R.id.tvHargaBarang)
        tvStatusPesanan = findViewById(R.id.tvStatusPesanan)
        tvNoResi = findViewById(R.id.tvNoResi)
        tvAlamatPengiriman = findViewById(R.id.tvAlamatPengiriman)
        tvTotalHargaBarang = findViewById(R.id.tvTotalHargaBarang)
        tvTotalOngkir = findViewById(R.id.tvTotalOngkir)
        tvTotalBayar = findViewById(R.id.tvTotalBayar)

        edtTxtNama = findViewById(R.id.edtTxtNama)
        edtTxtBank = findViewById(R.id.edtTxtBank)
        edtTxtTransfer = findViewById(R.id.edtTxtTransfer)
        edtTxtTgl = findViewById(R.id.edtTxtTgl)

        btnSubmitPengiriman = findViewById(R.id.btnSubmitPengiriman)
        btnDiterima = findViewById(R.id.btnDiterima)

        cvBayar = findViewById(R.id.cv_bayar)
        llBukti = findViewById(R.id.ll_bukti_pengiriman)

        id_transaksi = intent.getStringExtra("id_transaksi")

        list = findViewById(R.id.rvDetailTransaksi)
        val apiRepository = ApiRepository()

        myPreference = MyPreference(this)

        presenter = DetailTransaksiPresenter(this, apiRepository)
        presenter.DetailTransaksiPresenter(this)
        presenter.getDetailTransaksi(myPreference.getIdUser(), id_transaksi)

        btnDiterima.setOnClickListener {
            presenter.updateTransaksi(id_transaksi, "3")
            status = "Pesanan Diterima"
            btnDiterima.visibility = View.GONE
        }

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun getDetailBarang(data: List<Data>) {
        items.addAll(data)
        list.adapter = DetailTransaksiAdapter(this, items)
        list.layoutManager = LinearLayoutManager(this)
        (list.adapter as DetailTransaksiAdapter).notifyDataSetChanged()

        val resi = ""

        if (data.get(0).konfirmasi == "0") {
            status = "Belum Dibayar"
            cvBayar.visibility = View.VISIBLE
            llBukti.visibility = View.VISIBLE
            btnSubmitPengiriman.visibility = View.VISIBLE
            btnDiterima.visibility = View.GONE
            btnSubmitPengiriman.setOnClickListener {
                if (edtTxtNama.text.isEmpty() || edtTxtBank.text.isEmpty() || edtTxtTransfer.text.isEmpty() ||
                    edtTxtTgl.text.isEmpty()) {
                    Toast.makeText(this, "SILAHKAN ISI FIELD YANG KOSONG", Toast.LENGTH_SHORT).show()
                } else {
                    presenter.postTransaksi(edtTxtNama.text.toString(),
                        edtTxtBank.text.toString(),
                        edtTxtTransfer.text.toString(),
                        edtTxtTgl.text.toString(),
                        id_transaksi,
                        "1")
                }
            }
        }

        if (data.get(0).konfirmasi == "1") {
            status = "Sedang Dikonfirmasi"
            cvBayar.visibility = View.GONE
            llBukti.visibility = View.GONE
            btnSubmitPengiriman.visibility = View.GONE
            btnDiterima.visibility = View.GONE
        }

        if (data.get(0).konfirmasi == "2") {
            status = "Sedang Dikirim"
            cvBayar.visibility = View.GONE
            llBukti.visibility = View.GONE
            btnSubmitPengiriman.visibility = View.GONE
            btnDiterima.visibility = View.VISIBLE
        }



        if (data.get(0).no_resi == null) {
            tvNoResi.text = resi
        } else{
            tvNoResi.text = data.get(0).no_resi.toString()
        }
        tvHargaBarang.text = data.get(0).total_harga
        tvStatusPesanan.text = status
        tvAlamatPengiriman.text = data.get(0).alamat_penerima
        tvTotalHargaBarang.text = data.get(0).total_harga

    }


    override fun getTotalPembayaran(total: Total) {
        tvTotalOngkir.text = total.ongkir
        tvTotalBayar.text = total.total_pembayaran
    }

    override fun moveIntent() {
        val intent = Intent(this, MainActivity::class.java)
//        intent.putExtra("submit_true", "true")
        startActivity(intent)
    }

}
