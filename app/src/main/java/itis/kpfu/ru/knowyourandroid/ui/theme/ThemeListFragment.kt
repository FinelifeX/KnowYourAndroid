package itis.kpfu.ru.knowyourandroid.ui.theme

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.model.ThemeGroup
import itis.kpfu.ru.knowyourandroid.ui.theme.mvp.ThemeListPresenter
import itis.kpfu.ru.knowyourandroid.ui.theme.mvp.ThemeListView
import kotlinx.android.synthetic.main.fragment_list.*

class ThemeListFragment : MvpAppCompatFragment(), ThemeListView {

    companion object {

        fun newInstance(): ThemeListFragment {
            return ThemeListFragment()
        }
    }

    @InjectPresenter
    lateinit var presenter: ThemeListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.init()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun setThemes(themes: List<ThemeGroup>) {
        rv_list.layoutManager = LinearLayoutManager(activity)
        rv_list.adapter = ThemeAdapter(themes, activity)
    }

    override fun changeLoadingState(isLoading: Boolean) {
        if (isLoading) {
            rv_list.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
        } else {
            rv_list.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
        }
    }
}