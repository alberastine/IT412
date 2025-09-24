package com.example.it412activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.it412activity.databinding.FragmentSecondBinding;

public class RegisterFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegisterCancel.setOnClickListener(v ->
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );

        binding.btnRegisterSubmit.setOnClickListener(v -> {
            // Collect user inputs
            String email = binding.etRegisterEmail.getText().toString().trim();
            String password = binding.etRegisterPassword.getText().toString().trim();
            String firstName = binding.etRegisterFirstName.getText().toString().trim();
            String lastName = binding.etRegisterLastName.getText().toString().trim();
            String mobile = binding.etRegisterMobile.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                android.widget.Toast.makeText(getActivity(), "Email and Password are required!", android.widget.Toast.LENGTH_SHORT).show();
                return;
            }

            // Create UserAccount object
            UserAccount acct = new UserAccount(email, password, lastName, firstName, mobile);

            // Convert object to JSON
            Gson gson = new Gson();
            String userJson = gson.toJson(acct);

            // Save JSON string in SharedPreferences
            android.content.SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE);
            android.content.SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user", userJson);
            editor.apply();

            android.widget.Toast.makeText(getActivity(), "Registration successful!", android.widget.Toast.LENGTH_SHORT).show();

            // Navigate back to login
            NavHostFragment.findNavController(RegisterFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}