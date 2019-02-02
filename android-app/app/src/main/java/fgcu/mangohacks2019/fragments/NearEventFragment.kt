package fgcu.mangohacks2019.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
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

class NearEventFragment : Fragment(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.fragment_my_event, container, false)
    val list = ArrayList<Event>()
    val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view)

    recyclerView.layoutManager = (LinearLayoutManager(activity))
    recyclerView.itemAnimator = (DefaultItemAnimator())
    recyclerView.isNestedScrollingEnabled = false

    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))
    list.add(
        Event("Event", "12345 street florida, city state", Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"), "image", "some place where an event happens", Date(2312321), 23.1))

    recyclerView.adapter = SimpleRecyclerViewAdapter(list,
        R.layout.event_item, activity as RecyclerViewOnClick)
    return rootView
  }
}