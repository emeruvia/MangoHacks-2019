package fgcu.mangohacks2019.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fgcu.mangohacks2019.R
import fgcu.mangohacks2019.adapters.RecyclerViewOnClick
import fgcu.mangohacks2019.adapters.SimpleRecyclerViewAdapter
import fgcu.mangohacks2019.models.Coordinator
import fgcu.mangohacks2019.models.Event
import java.util.Date

class SubscriptionsFragment : Fragment(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.fragment_my_event, container, false)
    val list = ArrayList<Coordinator>()
    val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view)

    recyclerView.layoutManager = (GridLayoutManager(activity,2))
    recyclerView.itemAnimator = (DefaultItemAnimator())
    recyclerView.isNestedScrollingEnabled = false

    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))
    list.add(Coordinator("Billy Bob", "image", "City", "j@gamil.com", "password", "234-234-4506"))


    recyclerView.adapter = SimpleRecyclerViewAdapter(list,
        R.layout.subscripiton_item, activity as RecyclerViewOnClick)
    return rootView
  }
}