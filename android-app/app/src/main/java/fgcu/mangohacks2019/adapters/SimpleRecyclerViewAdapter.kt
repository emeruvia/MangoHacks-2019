package fgcu.mangohacks2019.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class SimpleRecyclerViewAdapter(
  private val items: List<Any>,
  private val layoutId: Int,
  recyclerViewOnClick: RecyclerViewOnClick
) : MyBaseAdapter() {

  init {
    setRecyclerViewOnClick(recyclerViewOnClick)
  }

  override fun getObjForPosition(position: Int): Any {
    return items[position]
  }

  override fun getLayoutIdForPosition(position: Int): Int {
    return layoutId
  }

  override fun getItemCount(): Int {
    return  items.size
  }
}

open abstract class MyBaseAdapter : RecyclerView.Adapter<MyBaseAdapter.MyViewHolder>() {

    private var recyclerViewOnClick: RecyclerViewOnClick? = null

    override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
    ): MyViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = DataBindingUtil.inflate<ViewDataBinding>(
          layoutInflater, viewType, parent, false)
      return MyViewHolder(binding)

    }

    override fun onBindViewHolder(
      holder: MyViewHolder,
      position: Int
    ) {
      val obj = getObjForPosition(position)
      holder.bind(obj)
      holder.itemView.setOnClickListener { recyclerViewOnClick!!.rowSelected(obj) }

      holder.itemView.setOnLongClickListener { v ->
        v.setBackgroundColor(Color.parseColor("#6bdfd3"))
        recyclerViewOnClick!!.deleteSelectedRow(obj)
        true
      }
    }

    override fun getItemViewType(position: Int): Int {
      return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    fun setRecyclerViewOnClick(recyclerViewOnClick: RecyclerViewOnClick) {
      this.recyclerViewOnClick = recyclerViewOnClick
    }

    inner class MyViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(
        binding.root) {

      fun bind(obj: Any) {
        binding.setVariable(fgcu.mangohacks2019.BR.obj, obj)
        binding.executePendingBindings()
      }
    }

  }


