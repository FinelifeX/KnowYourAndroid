package itis.kpfu.ru.knowyourandroid.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmList
import io.realm.RealmObject

open class Lesson(var name: String = "LessonName", var content: String = "LessonText",
        var imgReferences: RealmList<String> = RealmList()) : RealmObject(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()) {
        val realmList: RealmList<String> = RealmList()
        for (imgRef in parcel.createStringArrayList()) {
            realmList.add(imgRef)
        }
        imgReferences = realmList
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
