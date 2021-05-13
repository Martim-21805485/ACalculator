package ulht.cm.acalculator.ui.calculator

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Optional
import kotlinx.android.synthetic.main.fragment_calculator.*
import ulht.cm.acalculator.R
import ulht.cm.acalculator.data.local.room.entities.Operation
import ulht.cm.acalculator.ui.adapters.HistoryAdapter
import ulht.cm.acalculator.ui.callback.operations
import ulht.cm.acalculator.ui.listeners.OnDisplayChanged


class CalculatorFragment : Fragment(), OnDisplayChanged, operations {

    private lateinit var viewModel: CalculatorViewModel
    private val TAG = CalculatorFragment::class.java.simpleName
    private var historyAdapter: HistoryAdapter? = null
    private var list = mutableListOf(Operation("1+1", 3.0))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)
        viewModel = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        val orientation = this.resources.configuration.orientation
        if (orientation != Configuration.ORIENTATION_PORTRAIT) {
            viewModel.onShowList(this)
            list_historic.layoutManager = LinearLayoutManager(activity as Context)
            historyAdapter = HistoryAdapter(
                activity as Context,
                R.layout.item_expression,
                list as ArrayList<Operation>
            )
            list_historic.adapter = historyAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    @Optional
    @OnClick(
        R.id.button_0,
        R.id.button_1,
        R.id.button_2,
        R.id.button_3,
        R.id.button_4,
        R.id.button_5,
        R.id.button_6,
        R.id.button_7,
        R.id.button_8,
        R.id.button_9,
        R.id.button_dot,
        R.id.button_adition,
        R.id.button_less,
        R.id.button_multiply,
        R.id.button_divide
    )
    fun onClickSymbol(view: View) {
        text_visor.text = viewModel.onClickSymbol(view.tag.toString())
    }

    @OnClick(R.id.button_equals)
    fun onClickEquals(view: View) {
        text_visor.text = viewModel.onClickEquals()
    }

    @OnClick(R.id.button_back)
    fun onClickDeleteLast(view: View) {
        text_visor.text = viewModel.onClickDeleteLastChar()
    }

    @OnClick(R.id.button_C)
    fun onClickClear(view: View) {
        text_visor.text = viewModel.onClickDelete()
    }

    @OnClick(R.id.button_H)
    fun onClickHist(view: View) {

    }

    override fun onDisplayChanged(value: String?) {
        value.let { text_visor.text = it }
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