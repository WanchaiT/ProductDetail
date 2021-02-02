package com.example.productdetail.product.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.productdetail.R
import com.example.productdetail.customView.ToolbarClickListener
import com.example.productdetail.product.model.ProductDetailModel
import com.example.productdetail.product.viewModel.ProductDetailViewModel
import com.example.productdetail.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_product_detail.*
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
        viewModel.context = requireContext()
        setVisibleCtlContainer(false)
        setVisibleShimmer(true)
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
        ctv_toolbar.setTextTitle(getString(R.string.txt_detail))
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
                setVisibleShimmer(false)
                setVisibleCtlContainer(true)
                setViewProductDetail(it)
            })
            event.onFailed.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            })
        }
    }

    fun setVisibleShimmer(isShow: Boolean) {
        if (isShow) {
            shimmer_product_detail.startShimmer()
            shimmer_product_detail.visibility = View.VISIBLE
        } else {
            shimmer_product_detail.stopShimmer()
            shimmer_product_detail.visibility = View.GONE
        }
    }

    fun setVisibleCtlContainer(isShow: Boolean) {
        ctl_container.visibility = if (isShow) {
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
                        context = imv_product.context,
                        imageView = imv_product,
                        path = item.image ?: "",
                        goneAble = true,
                        imageShadowView = imv_shadow
                )
            } else {
                imv_product.visibility = View.GONE
                imv_shadow.visibility = View.GONE
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