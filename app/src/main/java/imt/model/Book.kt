package imt.model

import android.os.Parcel
import android.os.Parcelable

data class Book(val isn: String, val titre: String, val prix: String, val cov: String, val synopsis: Array<String>?) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArray()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isn)
        parcel.writeString(titre)
        parcel.writeString(prix)
        parcel.writeString(cov)
        parcel.writeStringArray(synopsis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(taille: Int): Array<Book?> {
            return arrayOfNulls(taille)
        }
    }
}