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
import fgcu.mangohacks2019.models.User
import fgcu.mangohacks2019.utils.EightBaseApolloClient
import kotlinx.android.synthetic.main.activity_detailed_coordinator.recyclerView
import java.util.Date

class MyEventsFragment : Fragment() {

  private lateinit var list: ArrayList<Event>
  private lateinit var recyclerView: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val rootView = inflater.inflate(R.layout.fragment_my_event, container, false)
    list = if(arguments?.get("list") == null) ArrayList() else arguments?.get("list") as ArrayList<Event>
    recyclerView = rootView.findViewById(R.id.recycler_view)

    recyclerView.layoutManager = (LinearLayoutManager(activity))
    recyclerView.itemAnimator = (DefaultItemAnimator())
    recyclerView.isNestedScrollingEnabled = false
Log.d("list", "List size is:" + list.size)
//    getEventList()

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
        .enqueue(object : ApolloCall.Callback<GetListOfEventQuery.Data>() {
          override fun onFailure(e: ApolloException) {
            Log.d("onFailure", e.cause.toString())
          }

          override fun onResponse(response: Response<Data>) {
            Log.d("onReponse", response.data().toString())
            val count: Int = response.data()!!.eventsList()
                .count()
            for (i in 0 until count) {
              var title = response.data()!!.eventsList().items()[i].titleOfEvent()
              var description = response.data()!!.eventsList().items()[i].description()
              var address = response.data()!!.eventsList().items()[i].address()
              var date = response.data()!!.eventsList().items()[i].date()
              var price = response.data()!!.eventsList().items()[i].price()
              var username = response.data()!!.eventsList().items()[i].clients()!!.firstName()
              var baseCity = response.data()!!.eventsList().items()[i].clients()!!.baseCity()
              var email = response.data()!!.eventsList().items()[i].clients()!!.baseCity()
              var phoneNumber = response.data()!!.eventsList().items()[i].clients()!!.phoneNumber()
              list.add(
                  Event(
                      title!!, address!!, User(username!!, baseCity!!, email!!, phoneNumber!!), "",
                      description!!, date!!, price!!
                  )
              )
            }

            println(list.size)

          }
        })
  }
}