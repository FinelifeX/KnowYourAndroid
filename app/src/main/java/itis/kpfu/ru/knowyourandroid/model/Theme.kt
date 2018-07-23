package itis.kpfu.ru.knowyourandroid.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.PropertyName

data class Theme(val name : String = "", val lessons : List<Lesson> = emptyList()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArrayList(Lesson)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(lessons)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Theme> {
        override fun createFromParcel(parcel: Parcel): Theme {
            return Theme(parcel)
        }

        override fun newArray(size: Int): Array<Theme?> {
            return arrayOfNulls(size)
        }
    }
}