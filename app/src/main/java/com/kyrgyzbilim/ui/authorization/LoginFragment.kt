package com.kyrgyzbilim.ui.authorization

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kyrgyzbilim.MainActivity
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.remote.user.LoginRequestBody
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels {
        InjectorObject.getAuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.to_sign_up_button.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            val nav = Navigation.findNavController(it)
            nav.navigate(action)
        }

        view.sign_in_button.setOnClickListener {
            val pass = password_edit_text.text.toString()
            val login = phone_number_edit_text.text.toString()
            authViewModel.userLoginData = LoginRequestBody(
                login, pass
            )

            authViewModel.login().observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        login_progress_bar.visibility = View.GONE
                        startActivity(Intent(activity, MainActivity::class.java))
                        com.kyrgyzbilim.data.UserData.of(requireContext()).saveToken(it.data.access_token.toString())
                        Log.e(TAG, it.data.access_token.toString() )
                        Thread{
                            Thread.sleep(500)
                            activity?.finish()
                        }.start()
                    }
                    is ApiResult.Error -> {
                        it.throwable.message.toString()
                        Toast.makeText(activity, "Error ",  Toast.LENGTH_SHORT).show()
                        login_progress_bar.visibility = View.GONE
                    }
                    is ApiResult.Loading -> {
                        login_progress_bar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}
