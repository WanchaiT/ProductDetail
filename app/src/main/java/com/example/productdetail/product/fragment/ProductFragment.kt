package com.example.productdetail.product.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexDirection
import com.example.productdetail.R
import com.example.productdetail.product.activity.ProductDetailActivity
import com.example.productdetail.product.adapter.ItemProductAdapter
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.viewModel.ProductViewModel
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_product.*

class ProductFragment : Fragment() {

    var productAdapter = ItemProductAdapter()
    private lateinit var viewModel: ProductViewModel

    companion object {
        fun newInstance() = ProductFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        initToolbar()
        observeViewModel()
        viewModel.getAllProduct()
    }


    private fun initToolbar() {
        ctv_toolbar.setTextTitle(getString(R.string.txt_products))
        ctv_toolbar.visibleBackButton(false)
    }

    fun observeViewModel() {
        viewModel.let { event ->
            event.product.observe(viewLifecycleOwner, Observer {
                setVisibleProgressBar(false)
                setVisibleLinContainer(true)
                setRcvProduct(it)
            })
        }
    }

    fun setVisibleProgressBar(isShow: Boolean) {
        progress_circular.visibility = if(isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun setVisibleLinContainer(isShow: Boolean) {
        lin_container.visibility = if(isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun invokeToProductSelected(id: Int) {
        Intent(requireContext(), ProductDetailActivity::class.java).apply {
            this.putExtra("id", id ?: 0)
        }.let {
            requireContext().startActivity(it)
        }
    }

    private fun setRcvProduct(listProduct: ArrayList<ProductDetailModel>) {
        productAdapter.onItemClickListener = { item ->
            item.id?.let {
                invokeToProductSelected(it)
            }
        }
        rcv_product.layoutManager = FlexboxLayoutManager(requireContext(), FlexDirection.ROW)
        rcv_product.adapter = productAdapter
        productAdapter.itemList = listProduct
        productAdapter.notifyDataSetChanged()
    }

}