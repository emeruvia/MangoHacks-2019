package fgcu.mangohacks2019.models

import java.util.*

data class Event(
  var name: String,
  var address: String,
  var user: User,
  var image: String,
  var description: String,
  var date: Date,
  var price: Double
)