package itis.kpfu.ru.knowyourandroid.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class Lesson(val name : String = "LessonName", var content : String = "LessonText", val
imgReferences :
List<String> = ArrayList<String>()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(content)
        parcel.writeStringList(imgReferences)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lesson> {
        override fun createFromParcel(parcel: Parcel): Lesson {
            return Lesson(parcel)
        }

        override fun newArray(size: Int): Array<Lesson?> {
            return arrayOfNulls(size)
        }
    }
}

