package fgcu.mangohacks2019.models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import java.util.*

data class Event(
  var name: String,
  var address: String,
  var user: User,
  var image: String,
  var description: String,
  var date: String,
  var price: Double
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString() as String,
      parcel.readString() as String,
      parcel.readParcelable(User::class.java.classLoader) as User,
      parcel.readString() as String,
      parcel.readString() as String,
      parcel.readString() as String,
      parcel.readDouble()
  ) {
  }

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
    parcel.writeString(name)
    parcel.writeString(address)
    parcel.writeParcelable(user, flags)
    parcel.writeString(image)
    parcel.writeString(description)
    parcel.writeString(date)
    parcel.writeDouble(price)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<Event> {
    override fun createFromParcel(parcel: Parcel): Event {
      return Event(parcel)
    }

    override fun newArray(size: Int): Array<Event?> {
      return arrayOfNulls(size)
    }
  }
}