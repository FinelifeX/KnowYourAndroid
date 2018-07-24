package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookClass

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.R
import itis.kpfu.ru.knowyourandroid.R.id.nav_methods
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.model.HandbookClass
import itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod.HandbookMethodListFragment
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.fragment_list.progress_bar_list
import kotlinx.android.synthetic.main.fragment_list.rv_list

class HandbookClassListFragment : MvpAppCompatFragment(),
        HandbookClassListView {

    @InjectPresenter
    lateinit var presenter: HandbookClassPresenter

    override fun changeLoadingState(isLoading: Boolean) {
        if (isLoading) {
            rv_list.visibility = View.GONE
            progress_bar_list.visibility = View.VISIBLE
        } else {
            rv_list.visibility = View.VISIBLE
            progress_bar_list.visibility = View.GONE
        }
    }

    fun onItemClick(position: Int) {
        presenter.onItemClick(position)
    }

    override fun showDetails(position: Int) {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container,
                        HandbookMethodListFragment.newInstance(
                                position))
                ?.addToBackStack("MethodList")
                ?.commit()
    }

    override fun setClasses(classes: List<HandbookClass>) {
        rv_list.adapter = HandbookClassAdapter(classes, this)
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    companion object {

        fun newInstance(): HandbookClassListFragment {
            return HandbookClassListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.init()
        this.activity?.toolbar?.title = resources.getString(R.string.nav_methods)
        this.activity?.nav_view?.setCheckedItem(nav_methods)
        return inflater.inflate(layout.fragment_list, container, false)
    }
}