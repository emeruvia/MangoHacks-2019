package fgcu.mangohacks2019.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import fgcu.mangohacks2019.HomePageActivity
import fgcu.mangohacks2019.R
import fgcu.mangohacks2019.models.Event

class EditProfileFragment : Fragment(){

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_edit_profile, container, false)
  }


}