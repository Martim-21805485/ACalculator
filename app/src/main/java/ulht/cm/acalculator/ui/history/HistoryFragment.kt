package ulht.cm.acalculator.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.fragment_calculator.*

import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.list_historic
import ulht.cm.acalculator.*
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.ui.adapters.HistoryAdapter
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.listeners.OnListChanged
import ulht.cm.acalculator.ui.utils.RecyclerItemClickListener


class HistoryFragment : Fragment(), operations {

    private val TAG = HistoryFragment::class.java.simpleName
    private lateinit var viewModel: HistoryViewModel
    private var historyAdapter: HistoryAdapter? = null
    private var list = mutableListOf(Operation("1+1", 3.0))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart(){
        super.onStart()
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        viewModel.onCreateList(this)
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        historyAdapter = HistoryAdapter(
            activity as Context,
            R.layout.item_expression,
            list as ArrayList<Operation>
        )
        list_historic.adapter = historyAdapter
        list_historic.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                list_historic,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {

                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        viewModel.onLongClick(position)
                    }
                })
        )
    }

    override fun onDestroy() {
        //viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun returnOperation(lista: List<Operation>) {
        list.clear()
        for(i in lista){
            list.add(i)
        }

        // go to UI thread
        getActivity()?.runOnUiThread(java.lang.Runnable {
            historyAdapter?.notifyDataSetChanged()
        })
    }

}