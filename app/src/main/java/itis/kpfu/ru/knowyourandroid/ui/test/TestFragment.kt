package itis.kpfu.ru.knowyourandroid.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.ui.theme.ThemeListFragment
import itis.kpfu.ru.knowyourandroid.utils.*
import kotlinx.android.synthetic.main.activity_drawer.toolbar
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment : MvpAppCompatFragment(), TestView {

    private lateinit var themeName: String
    private var questionNumber: Int = 0
    private var buttonList: MutableList<Button> = ArrayList()
    private lateinit var test: Test
    private val user = UserProvider.getCurrentUser()
    private var earnedPoints = 0

    @InjectPresenter
    lateinit var presenter: TestPresenter

    companion object {

        fun newInstance(themeName: String): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            args.putString(THEME_NAME_TAG, themeName)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        themeName = arguments?.getString(THEME_NAME_TAG).toString()
        this.activity?.toolbar?.title = themeName
        return view
    }

    override fun testData() {
        presenter.loadData(themeName)
    }

    override fun testInfo(test: Test) {
        this.test = test
        initTest()
    }

    override fun errorNoData() {
        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, ThemeListFragment.newInstance())
                ?.commit()
        Toast.makeText(this.context, "OOOPS!", Toast.LENGTH_LONG).show()
    }

    private fun initButtonList() {
        buttonList.add(btn_answer1)
        buttonList.add(btn_answer2)
        buttonList.add(btn_answer3)
        buttonList.add(btn_answer4)
    }

    private fun initTest() {
        onDataLoaded()
        setQuestionData()
    }

    private fun toNextQuestion() {
        if (questionNumber + 1 != test.questionList.size) {
            questionNumber++
            setQuestionData()
        } else {
            //TODO экран с результатом теста
            if (earnedPoints < 0) earnedPoints = 0
            user?.let {
                it.exp += earnedPoints
                it.passedTests.add(test.title)
                UserProvider.updateUser()
            }
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, ThemeListFragment.newInstance())
                    ?.commit()
        }
    }

    private fun setQuestionData() {
        val number = "${questionNumber + 1}/${test.questionList.size}"
        tv_number.text = number
        tv_question.text = test.questionList[questionNumber]?.content
        val answerList = test.questionList[questionNumber]?.answerList?.shuffled()
        for ((i, btn) in buttonList.withIndex()) {
            btn.text = answerList?.get(i)?.content
            btn.setOnClickListener {
                earnedPoints += if (answerList?.get(i)?.correct == true) {
                    POINTS_FOR_CORRECT
                } else {
                    POINTS_FOR_INCORRECT
                }
                toNextQuestion()
            }
        }
    }

    private fun onDataLoaded() {
        initButtonList()
        btn_skip.setOnClickListener {
            earnedPoints += POINTS_FOR_SKIP
            toNextQuestion()
        }
        progress_bar.visibility = View.GONE
        tv_number.visibility = View.VISIBLE
        tv_question.visibility = View.VISIBLE
        for (btn in buttonList) {
            btn.visibility = View.VISIBLE
        }
        btn_skip.visibility = View.VISIBLE
    }
}