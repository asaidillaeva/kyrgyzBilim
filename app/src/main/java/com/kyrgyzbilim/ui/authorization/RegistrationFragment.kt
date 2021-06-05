package com.kyrgyzbilim.ui.authorization

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.kyrgyzbilim.R
import com.kyrgyzbilim.base.ApiResult
import com.kyrgyzbilim.base.InjectorObject
import com.kyrgyzbilim.data.remote.user.RegisterRequestBody
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*


class RegistrationFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels {
        InjectorObject.getAuthViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.to_sign_in_button.setOnClickListener {
            activity?.onBackPressed()
        }

        view.sign_up_button.setOnClickListener {
            val firstName = first_name_edit_text.text.toString()
            val lastName = last_name_edit_text.text.toString()
            val phoneNumber = phone_number_edit_text.text.toString()
            val pass = password_edit_text.text.toString()
            val pass2 = repeat_password_edit_text.text.toString()


            // validate data
            var error = ""

            if( firstName.length < 3){
                error = "field Name could not be less than 3"
            }

            if( lastName.length < 3){
                error = "field Last Name could not be less than 3"
            }

            if( pass != pass2){
                error = "passwords are not same"
            }

            if(error != ""){
                Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            authViewModel.userRegisterData = RegisterRequestBody(
                firstName, lastName, phoneNumber, pass
            )

            authViewModel.register().observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResult.Success -> {
                        register_progress_bar.visibility = View.GONE
                        activity?.onBackPressed()
                    }
                    is ApiResult.Error -> {
                        it.throwable.message.toString()
                        Toast.makeText(activity, "Error ",  Toast.LENGTH_SHORT).show()
                        register_progress_bar.visibility = View.GONE
                        Log.d("tilek", it.throwable.localizedMessage?.toString() + "")
                    }
                    is ApiResult.Loading -> {
                        register_progress_bar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}