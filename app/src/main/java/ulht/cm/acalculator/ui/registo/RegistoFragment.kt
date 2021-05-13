package ulht.cm.acalculator.ui.registo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.emailInput
import kotlinx.android.synthetic.main.fragment_login.passwordInput
import kotlinx.android.synthetic.main.fragment_registo.*
import ulht.cm.acalculator.R
import ulht.cm.acalculator.ui.callback.login
import ulht.cm.acalculator.ui.callback.registo
import ulht.cm.acalculator.ui.utils.NavigationManager


class RegistoFragment : Fragment(), registo {

    private lateinit var viewModel: RegistoViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registo, container, false)
        viewModel = ViewModelProviders.of(this).get(RegistoViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    @OnClick(R.id.login)
     fun onClickRegisto(view: View){
        Log.i("Login",emailInput.text.toString())
        Log.i("Password",passwordInput.text.toString())
        viewModel.onClickButtonRegisto(this,emailInput.text.toString(),passwordInput.text.toString(),nomeInput.text.toString())

    }

    override fun onRegisto() {
        activity?.let {
            NavigationManager.goToLoginFragment(
                it.supportFragmentManager
            )
        }
    }

}