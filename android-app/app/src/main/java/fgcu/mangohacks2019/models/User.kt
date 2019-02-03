package fgcu.mangohacks2019.models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator

data class User(
  var name: String,
  var baseCity: String,
  var email: String,
  var phoneNumber: String
) : Parcelable {
  constructor(parcel: Parcel) : this(
      parcel.readString() as String,
      parcel.readString() as String,
      parcel.readString() as String,
      parcel.readString() as String
  ) {
  }

  override fun writeToParcel(
    parcel: Parcel,
    flags: Int
  ) {
    parcel.writeString(name)
    parcel.writeString(baseCity)
    parcel.writeString(email)
    parcel.writeString(phoneNumber)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<User> {
    override fun createFromParcel(parcel: Parcel): User {
      return User(parcel)
    }

    override fun newArray(size: Int): Array<User?> {
      return arrayOfNulls(size)
    }
  }
}