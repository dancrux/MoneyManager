package com.cruxrepublic.moneymanager.ui.income

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cruxrepublic.moneymanager.R

//// TODO: Customize parameter argument names
//const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    AddIncomeDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class AddIncomeDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_income_dialog, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        activity?.findViewById<RecyclerView>(R.id.list)?.layoutManager =
//            LinearLayoutManager(context)
//        activity?.findViewById<RecyclerView>(R.id.list)?.adapter =
//            arguments?.getInt(ARG_ITEM_COUNT)?.let { ItemAdapter(it) }
//    }
//
//    private inner class ViewHolder internal constructor(
//        inflater: LayoutInflater,
//        parent: ViewGroup
//    ) : RecyclerView.ViewHolder(
//        inflater.inflate(
//            R.layout.fragment_item_list_dialog_list_dialog_item,
//            parent,
//            false
//        )
//    ) {
//
//        internal val text: TextView = itemView.findViewById(R.id.text)
//    }
//
//    private inner class ItemAdapter internal constructor(private val mItemCount: Int) :
//        RecyclerView.Adapter<ViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            return ViewHolder(LayoutInflater.from(parent.context), parent)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.text.text = position.toString()
//        }
//
//        override fun getItemCount(): Int {
//            return mItemCount
//        }
//    }
//
//    companion object {
//
//        // TODO: Customize parameters
//        fun newInstance(itemCount: Int): AddIncomeDialogFragment =
//            AddIncomeDialogFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_ITEM_COUNT, itemCount)
//                }
//            }
//
//    }
}