package itis.kpfu.ru.knowyourandroid.ui.theme

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.utils.THEMES_REFERENCE
import kotlinx.android.synthetic.main.fragment_list.view.*

class ThemeListFragment : android.support.v4.app.Fragment() {

    lateinit var recyclerView: RecyclerView

    companion object {

        val database = FirebaseDatabase.getInstance()

        fun newInstance(): ThemeListFragment {
            return ThemeListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.rv_list
        val layoutManager = LinearLayoutManager(activity!!.applicationContext)
        recyclerView.layoutManager = layoutManager
        val themesList = mutableListOf<ThemeGroup>()

        val databaseThemesReference = database.reference.child(THEMES_REFERENCE)
        databaseThemesReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.d("ERROR", "ERROR IN $THEMES_REFERENCE")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var themeName: String
                for (theme in p0.children) {
                    themeName = theme.key.toString()
                    val lessonList = mutableListOf<Lesson>()
                    for (lesson in theme.children) {
                        lessonList.add(Lesson(lesson.key.toString()))
                    }
                    themesList.add(ThemeGroup(themeName, lessonList))
                    val adapter = ThemeAdapter(themesList, activity as FragmentActivity)
                    recyclerView.adapter = adapter
                }
            }
        })
        return view
    }
}