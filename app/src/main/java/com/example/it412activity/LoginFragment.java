package com.example.it412activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.it412activity.databinding.FragmentFirstBinding;

public class LoginFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLoginRegister.setOnClickListener(v ->
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.btnLoginSubmit.setOnClickListener(v -> {
            String inputEmail = binding.etLoginEmail.getText().toString().trim();
            String inputPassword = binding.etLoginPassword.getText().toString().trim();

            // Retrieve JSON from SharedPreferences
            android.content.SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", android.content.Context.MODE_PRIVATE);
            String userJson = prefs.getString("user", null);

            if (userJson != null) {
                Gson gson = new Gson();
                UserAccount savedUser = gson.fromJson(userJson, UserAccount.class);

                if (savedUser.getEmailAddress().equals(inputEmail) &&
                        savedUser.getPassword().equals(inputPassword)) {

                    android.util.Log.d("Login", "Login successful!");
                    android.widget.Toast.makeText(getActivity(), "Login successful!", android.widget.Toast.LENGTH_SHORT).show();

                    // Navigate to home
                    NavHostFragment.findNavController(LoginFragment.this)
                            .navigate(R.id.action_FirstFragment_to_fragment_home);

                } else {
                    android.util.Log.d("Login", "Invalid credentials.");
                    android.widget.Toast.makeText(getActivity(), "Invalid email or password", android.widget.Toast.LENGTH_SHORT).show();
                }
            } else {
                android.widget.Toast.makeText(getActivity(), "No registered user found", android.widget.Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}