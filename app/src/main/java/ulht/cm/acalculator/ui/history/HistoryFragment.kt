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

import kotlinx.android.synthetic.main.fragment_history.*
import ulht.cm.acalculator.*
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.ui.adapters.HistoryAdapter
import ulht.cm.acalculator.ui.listeners.OnListChanged
import ulht.cm.acalculator.ui.utils.RecyclerItemClickListener


class HistoryFragment : Fragment(), OnListChanged {

    private val TAG = HistoryFragment::class.java.simpleName
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        viewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart(){
        super.onStart()
        viewModel.registerListener(this)
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter =  HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(viewModel.onCreateList()) )
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

    override fun onListChanged(value: List<Operation>) {
        list_historic.layoutManager = LinearLayoutManager(activity as Context)
        list_historic.adapter =  HistoryAdapter(activity as Context, R.layout.item_expression, ArrayList(viewModel.onCreateList()) )
    }

}