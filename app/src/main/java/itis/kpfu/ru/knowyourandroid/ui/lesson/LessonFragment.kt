package itis.kpfu.ru.knowyourandroid.ui.lesson

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Lesson
import kotlinx.android.synthetic.main.activity_drawer.toolbar
import kotlinx.android.synthetic.main.fragment_lesson.iv_lesson
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_content

class LessonFragment: MvpAppCompatFragment(), LessonView {

    private lateinit var lessonName: String
    private lateinit var themeName: String

    @InjectPresenter
    lateinit var presenter : LessonPresenter

    companion object {

        const val LESSON_NAME_TAG = "LESSON_NAME"
        const val THEME_NAME_TAG = "THEME_NAME"

        fun newInstance(lessonName: String, themeName: String): LessonFragment {
            Log.d("LESSON", "newInstance")
            val fragment = LessonFragment()
            val bundle = Bundle()
            bundle.putString(LESSON_NAME_TAG, lessonName)
            bundle.putString(THEME_NAME_TAG, themeName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        Log.d("LESSON", "VIEW CREATED")
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        lessonName = arguments?.getString(LESSON_NAME_TAG) ?: LESSON_NAME_TAG
        themeName = arguments?.getString(THEME_NAME_TAG) ?: THEME_NAME_TAG
        this.activity?.toolbar?.title = lessonName
        return view
    }

    override fun initView(lesson: Lesson) {
        Log.d("LESSON", "got info prom presenter")
        tv_lesson_content.text = lesson.text
        //TODO: работа с несколькими картинками
        if (lesson.imgReferences.isNotEmpty()){
            if (VERSION.SDK_INT >= VERSION_CODES.M) {
                this.context?.let {
                    Glide.with(it)
                            .load(lesson.imgReferences[0])
                            .into(iv_lesson)
                }
            }
            else {
                Glide.with(this)
                        .load(lesson.imgReferences[0])
                        .into(iv_lesson)
            }
        }
    }

    override fun lessonData() {
        presenter.getLessonInfo(themeName, lessonName)
    }
}