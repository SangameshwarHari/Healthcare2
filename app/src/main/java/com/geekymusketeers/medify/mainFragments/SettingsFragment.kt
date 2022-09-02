package com.geekymusketeers.medify.mainFragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekymusketeers.medify.R
import com.geekymusketeers.medify.auth.SignIn_Activity
import com.geekymusketeers.medify.databinding.FragmentHomeBinding
import com.geekymusketeers.medify.databinding.FragmentSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        binding.logoutButton.setOnClickListener {
            logoutFun()
        }

        binding.createUPI.setOnClickListener {
            startActivity(Intent(requireActivity(), UPImanager::class.java))
        }






        return binding.root
    }

    @SuppressLint("CommitPrefEdits")
    private fun logoutFun() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(context, SignIn_Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        requireActivity().finish()

    }
}