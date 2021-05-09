package ulht.cm.acalculator.ui.login

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
import ulht.cm.acalculator.R
import ulht.cm.acalculator.ui.listeners.OnLoginTrue
import ulht.cm.acalculator.ui.utils.NavigationManager


class LoginFragment : Fragment(), OnLoginTrue {

    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
    }

    @OnClick(R.id.login)
     fun onClickLogin(view: View){
        Log.i("Login",emailInput.text.toString())
        Log.i("Password",passwordInput.text.toString())
        viewModel.onClickButtonLogin(emailInput.text.toString(),passwordInput.text.toString())

    }

    override fun onLoginTrue() {
        activity?.let { NavigationManager.goToCalculatorFragment(it.supportFragmentManager) }
    }

}