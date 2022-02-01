package com.example.menu

import android.os.Parcel
import android.os.Parcelable

data class Item(val height: String?, val mass: String?, val bmi: String?, val bmiColor: Int?, val date: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            height = parcel.readString(),
            mass = parcel.readString(),
            bmi = parcel.readString(),
            bmiColor = parcel.readInt(),
            date = parcel.readString(),
    )

    override fun toString(): String {
        return ("$height $mass $bmi $bmiColor $date")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(height)
        parcel.writeString(mass)
        parcel.writeString(bmi)
        parcel.writeInt(bmiColor!!)
        parcel.writeString(date)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}