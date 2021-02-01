package com.example.productdetail.product.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productdetail.R
import com.example.productdetail.customView.ToolbarClickListener
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.viewModel.ProductDetailViewModel
import com.example.productdetail.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.ctv_toolbar
import java.math.RoundingMode
import java.text.DecimalFormat

class ProductDetailFragment : Fragment() {

    private var id: Int? = null
    private lateinit var viewModel: ProductDetailViewModel


    companion object {
        fun newInstance(id: Int) =
                ProductDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", id)
                    }
                }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        initArgument()
        initToolbar()
        observeViewModel()
        viewModel.getProduct(id ?: 0)
    }

    private fun initArgument() {
        arguments?.let {
            id = it.getInt("id")
        }
    }

    private fun initToolbar() {
        ctv_toolbar.setTextTitle(getString(R.string.txt_products))
        ctv_toolbar.visibleBackButton(true)
        ctv_toolbar?.backButtonClickListener = object : ToolbarClickListener {
            override fun onLHSClickListener(view: View) {
                requireActivity()?.finish()
            }
        }
    }

    fun observeViewModel() {
        viewModel.let { event ->
            event.productSelected.observe(viewLifecycleOwner, Observer {
                setVisibleProgressBar(false)
                setVisibleCtlContainer(true)
                setViewProductDetail(it)
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

    fun setVisibleCtlContainer(isShow: Boolean) {
        ctl_container.visibility = if(isShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setViewProductDetail(product: ProductDetailModel) {
        product.let { item ->
            tv_product_name.text = item.title ?: ""

            if (!item.image.isNullOrEmpty()) {
                ImageUtil.loadImage(
                        context =imv_product.context,
                        imageView = imv_product,
                        path = item.image ?: ""
                )
            }

            tv_new.visibility = if(item.isNewProduct) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            tv_product_price.text = df.format(item.price ?: 0.0)
            tv_content.text = item.content
        }
    }

}