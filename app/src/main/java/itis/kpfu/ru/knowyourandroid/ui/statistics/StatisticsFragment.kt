package itis.kpfu.ru.knowyourandroid.ui.statistics

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.LeadersAdapter
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.model.User
import itis.kpfu.ru.knowyourandroid.utils.LESSONS_NUM
import kotlinx.android.synthetic.main.fragment_list.rv_list
import kotlinx.android.synthetic.main.fragment_statistics.included_list
import kotlinx.android.synthetic.main.fragment_statistics.pb_lessons
import kotlinx.android.synthetic.main.fragment_statistics.progress_bar
import kotlinx.android.synthetic.main.fragment_statistics.tv_exp_num
import kotlinx.android.synthetic.main.fragment_statistics.tv_lessons_num

class StatisticsFragment : MvpAppCompatFragment(), StatisticsView {

    companion object {

        fun newInstance(): StatisticsFragment {
            return StatisticsFragment()
        }
    }
    @InjectPresenter
    lateinit var presenter: StatisticsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        presenter.init()
        return inflater.inflate(layout.fragment_statistics, container, false)
    }

    override fun setExp(exp: Int?) {
        if (exp == null) {
            tv_exp_num.text = "0"
        }
        else
            tv_exp_num.text = exp.toString()
    }

    override fun setLessonProgress(progress: Int?) {
        if (progress == null) {
            pb_lessons.progress = 0
            tv_lessons_num.text = "0/$LESSONS_NUM"
        }
        else {
            pb_lessons.progress = progress / LESSONS_NUM
            tv_lessons_num.text = "$progress/$LESSONS_NUM"
        }
    }

    override fun setLeaders(leaders: List<User?>) {
        rv_list.adapter = LeadersAdapter(leaders)
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    override fun changeLoadingState(isLoading: Boolean) {
        if (isLoading) {
            included_list.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
        }
        else {
            included_list.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
        }
    }
}