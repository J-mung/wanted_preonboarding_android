package com.example.wanted_preonboarding_android.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanted_preonboarding_android.R
import com.example.wanted_preonboarding_android.adapter.CategoryAdapter
import com.example.wanted_preonboarding_android.communicator.Communicator
import com.example.wanted_preonboarding_android.models.CategorySource
import com.example.wanted_preonboarding_android.ui.MainActivity
import org.w3c.dom.Text

class CategoryListFragment : Fragment(R.layout.fragment_category_list){

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var rvCategoryList: RecyclerView
    lateinit var mContext: Context

    val TAG = "CategoryListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCategoryList = view.findViewById(R.id.rvCategoryList)
        setUpRecyclerView()

        categoryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("category_txt", mContext.resources.getString(it.category))
            }
            findNavController().navigate(
                R.id.action_categoryListFragment_to_categoryNewsFragment,
                bundle
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun setUpRecyclerView() {
        val dataset = CategorySource().loadCategories()
        categoryAdapter = CategoryAdapter(mContext, dataset)
        rvCategoryList.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(mContext, 3)
        }
    }
}