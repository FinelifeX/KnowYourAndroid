package itis.kpfu.ru.knowyourandroid.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.Test
import itis.kpfu.ru.knowyourandroid.model.providers.UserProvider
import itis.kpfu.ru.knowyourandroid.ui.ThemeListFragment
import itis.kpfu.ru.knowyourandroid.utils.POINTS_FOR_CORRECT
import itis.kpfu.ru.knowyourandroid.utils.POINTS_FOR_INCORRECT
import itis.kpfu.ru.knowyourandroid.utils.POINTS_FOR_SKIP
import itis.kpfu.ru.knowyourandroid.utils.THEME_NAME_TAG
import kotlinx.android.synthetic.main.activity_drawer.toolbar
import kotlinx.android.synthetic.main.fragment_test.btn_answer1
import kotlinx.android.synthetic.main.fragment_test.btn_answer2
import kotlinx.android.synthetic.main.fragment_test.btn_answer3
import kotlinx.android.synthetic.main.fragment_test.btn_answer4
import kotlinx.android.synthetic.main.fragment_test.btn_skip
import kotlinx.android.synthetic.main.fragment_test.progress_bar
import kotlinx.android.synthetic.main.fragment_test.tv_number
import kotlinx.android.synthetic.main.fragment_test.tv_question

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

    override fun onStart() {
        super.onStart()
        initButtonList()
        btn_skip.setOnClickListener {
            earnedPoints += POINTS_FOR_SKIP
            toNextQuestion()
        }
    }

    private fun initButtonList() {
        buttonList.add(btn_answer1)
        buttonList.add(btn_answer2)
        buttonList.add(btn_answer3)
        buttonList.add(btn_answer4)
    }

    override fun testData() {
        presenter.loadData(themeName)
    }

    override fun testInfo(test: Test) {
        this.test = test
        initTest()
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
                it.passedTests.add(test.theme)
                UserProvider.updateUser()
            }
            fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, ThemeListFragment.newInstance())
                    ?.commit()
        }
    }

    private fun setQuestionData(){
        val number = "${questionNumber + 1}/${test.questionList.size}"
        tv_number.text = number
        tv_question.text = test.questionList[questionNumber].text
        val answerList = test.questionList[questionNumber].answerList.asList().shuffled()
        for ((i, btn) in buttonList.withIndex()) {
            btn.text = answerList[i].text
            btn.setOnClickListener {
                earnedPoints += if (answerList[i].correct) {
                    POINTS_FOR_CORRECT
                } else {
                    POINTS_FOR_INCORRECT
                }
                toNextQuestion()
            }
        }
    }

    private fun onDataLoaded() {
        progress_bar.visibility = View.GONE
        tv_number.visibility = View.VISIBLE
        tv_question.visibility = View.VISIBLE
        for (btn in buttonList) {
            btn.visibility = View.VISIBLE
        }
        btn_skip.visibility = View.VISIBLE
    }
}