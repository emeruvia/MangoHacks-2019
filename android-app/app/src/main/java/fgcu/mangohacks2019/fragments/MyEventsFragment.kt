package fgcu.mangohacks2019.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fgcu.mangohacks2019.R
import android.support.v4.view.ViewCompat.setNestedScrollingEnabled
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.SimpleAdapter
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.sample.GetListOfEventQuery
import com.apollographql.apollo.sample.GetListOfEventQuery.Data
import fgcu.mangohacks2019.HomePageActivity
import fgcu.mangohacks2019.adapters.RecyclerViewOnClick
import fgcu.mangohacks2019.adapters.SimpleRecyclerViewAdapter
import fgcu.mangohacks2019.databinding.EventItemBinding
import fgcu.mangohacks2019.models.Coordinator
import fgcu.mangohacks2019.models.Event
import fgcu.mangohacks2019.utils.EightBaseApolloClient
import java.util.Date

class MyEventsFragment : Fragment() {

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
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )
    list.add(
        Event(
            "Event", "12345 street florida, city state",
            Coordinator("James Jamie", "image", "City", "j@emai.com", "123455", "123-123-1234"),
            "image", "some place where an event happens", Date(2312321), 23.1
        )
    )

    recyclerView.adapter =
        SimpleRecyclerViewAdapter(list, R.layout.event_item, activity as RecyclerViewOnClick)
    return rootView
  }

  private fun getEventList() {
    val client: ApolloClient = EightBaseApolloClient().getEightBaseApolloClient()
    client.query(
        GetListOfEventQuery.builder()
            .build()
    )
        .enqueue(object: ApolloCall.Callback<GetListOfEventQuery.Data>(){
          override fun onFailure(e: ApolloException) {
            Log.d("onFailure", e.localizedMessage)
          }

          override fun onResponse(response: Response<Data>) {

          }

        })
  }

}