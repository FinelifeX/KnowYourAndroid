package itis.kpfu.ru.knowyourandroid.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Lesson
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.ui.test.TestFragment
import itis.kpfu.ru.knowyourandroid.ui.theme.ThemeListFragment
import itis.kpfu.ru.knowyourandroid.utils.IS_LAST_TAG
import itis.kpfu.ru.knowyourandroid.utils.LESSON_NAME_TAG
import itis.kpfu.ru.knowyourandroid.utils.THEME_NAME_TAG
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.fragment_lesson.btn_back
import kotlinx.android.synthetic.main.fragment_lesson.iv_lesson
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_content

class LessonFragment : MvpAppCompatFragment(), LessonView {

    private lateinit var lessonName: String
    private lateinit var themeName: String
    private var isLast: Boolean = false

    private val user = UserProvider.getCurrentUser()

    @InjectPresenter
    lateinit var presenter: LessonPresenter

    companion object {

        fun newInstance(lessonName: String, themeName: String, themeSize: Int, lessonIndex: Int): LessonFragment {
            val fragment = LessonFragment()
            val bundle = Bundle()
            bundle.putString(LESSON_NAME_TAG, lessonName)
            bundle.putString(THEME_NAME_TAG, themeName)
            bundle.putBoolean(IS_LAST_TAG, themeSize == (lessonIndex + 1))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        lessonName = arguments?.getString(LESSON_NAME_TAG).toString()
        themeName = arguments?.getString(THEME_NAME_TAG).toString()
        isLast = arguments?.getBoolean(IS_LAST_TAG) == true
        this.activity?.toolbar?.title = lessonName
        return view
    }

    override fun initView(lesson: Lesson) {
        onDataLoaded()
        tv_lesson_content.text = lesson.content
        if (isLast) {
            btn_back.text = resources.getText(R.string.start_test)
        }
        btn_back.setOnClickListener {
            user?.let {
                if (!user.passedLessons.contains(lessonName)) user.passedLessons.add(lessonName)
                UserProvider.updateUser()
            }
            if (isLast) {
                fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.container, TestFragment.newInstance(themeName))
                        ?.commit()
            } else {
                fragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.container, ThemeListFragment.newInstance())
                        ?.commit()
            }
        }
        //TODO: работа с несколькими картинками
        if (lesson.imgReferences.isNotEmpty()) {
            Glide.with(this)
                    .load(lesson.imgReferences[0])
                    .into(iv_lesson)
        }
    }

    override fun lessonData() {
        presenter.getLessonInfo(lessonName)
    }

    private fun onDataLoaded() {
        progress_bar.visibility = View.GONE
        tv_lesson_content.visibility = View.VISIBLE
        btn_back.visibility = View.VISIBLE
        iv_lesson.visibility = View.VISIBLE
    }
}