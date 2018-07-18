package itis.kpfu.ru.knowyourandroid.ui.lesson

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.ui.ThemeListFragment
import itis.kpfu.ru.knowyourandroid.utils.LESSON_NAME_TAG
import itis.kpfu.ru.knowyourandroid.utils.THEME_NAME_TAG
import kotlinx.android.synthetic.main.activity_drawer.toolbar
import kotlinx.android.synthetic.main.fragment_lesson.btn_back
import kotlinx.android.synthetic.main.fragment_lesson.iv_lesson
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_content

class LessonFragment: MvpAppCompatFragment(), LessonView {

    private lateinit var lessonName: String
    private lateinit var themeName: String

    @InjectPresenter
    lateinit var presenter : LessonPresenter

    companion object {

        fun newInstance(lessonName: String, themeName: String): LessonFragment {
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
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        lessonName = arguments?.getString(LESSON_NAME_TAG).toString()
        themeName = arguments?.getString(THEME_NAME_TAG).toString()
        this.activity?.toolbar?.title = lessonName
        btn_back.setOnClickListener {
            //TODO отметка о том, что этот урок пройден юзером + если урок последний, то переброс на тест
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, ThemeListFragment.newInstance())
                    ?.commit()
        }
        return view
    }

    override fun initView(lesson: Lesson) {
        tv_lesson_content.text = lesson.text
        btn_back.setOnClickListener {
            //TODO отметка о том, что этот урок пройден юзером + если урок последний, то переброс на тест
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, ThemeListFragment.newInstance())
                    ?.commit()
        }
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