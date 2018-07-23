package itis.kpfu.ru.knowyourandroid.ui.handbook.handbookMethod

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import itis.kpfu.ru.knowyourandroid.R.layout
import itis.kpfu.ru.knowyourandroid.model.HandbookMethod
import itis.kpfu.ru.knowyourandroid.utils.CLASS_POSITION_TAG
import kotlinx.android.synthetic.main.fragment_list.rv_list

class HandbookMethodListFragment : MvpAppCompatFragment(), HandbookMethodListView {

    @InjectPresenter
    lateinit var presenter: HandbookMethodPresenter

    override fun setMethods(methods: List<HandbookMethod>) {
        rv_list.adapter = HandbookMethodAdapter(methods)
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    companion object {

        fun newInstance(position: Int): HandbookMethodListFragment {
            val fragment = HandbookMethodListFragment()
            val bundle = Bundle()
            bundle.putInt(CLASS_POSITION_TAG, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        arguments?.let {
            val position = it.getInt(CLASS_POSITION_TAG)
            presenter.init(position)
        }
        return inflater.inflate(layout.fragment_list, container, false)
    }
}