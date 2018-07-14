package itis.kpfu.ru.knowyourandroid.ui.lesson

import android.app.Fragment
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import itis.kpfu.ru.knowyourandroid.R
import kotlinx.android.synthetic.main.activity_drawer.toolbar
import kotlinx.android.synthetic.main.fragment_lesson.iv_lesson
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_content

class LessonFragment: Fragment(), LessonView {

    @InjectPresenter
    lateinit var presenter : LessonPresenter

    companion object {

        const val LESSON_NAME_TAG = "LESSON_NAME"

        fun newInstance(name: String): LessonFragment {
            val fragment = LessonFragment()
            val bundle = Bundle()
            bundle.putString(LESSON_NAME_TAG, name)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        this.activity.toolbar.title = arguments.getString(LESSON_NAME_TAG)
        return view
    }

    override fun initView(text: String, imgReference: List<String>) {
        tv_lesson_content.text = text
        //TODO: работа с несколькими картинками
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            Glide.with(this.context)
                    .load(imgReference[0])
                    .into(iv_lesson)
        }
        else {
            Glide.with(this)
                    .load(imgReference[0])
                    .into(iv_lesson)
        }
    }
}