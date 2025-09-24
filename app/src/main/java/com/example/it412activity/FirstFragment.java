package com.example.it412activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.it412activity.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

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
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment)
        );

        binding.btnLoginSubmit.setOnClickListener(v -> {
                UserAccount acct = new UserAccount();

                acct.setEmailAddress(binding.etLoginEmail.getText().toString().trim());
                acct.setPassword(binding.etLoginPassword.getText().toString().trim());

                android.util.Log.d("UserInput", "Email: " + acct.getEmailAddress());
                android.util.Log.d("UserInput", "Password: " + acct.getPassword());

                if ("jomar@gmail.com".equals(acct.getEmailAddress()) &&
                        "admin123".equals(acct.getPassword())) {
                    android.util.Log.d("Login", "Login successful!");
                    android.widget.Toast.makeText(getActivity(), "Login successful!", android.widget.Toast.LENGTH_SHORT).show();
                } else {
                    android.util.Log.d("Login", "Invalid credentials.");
                    android.widget.Toast.makeText(getActivity(), "Invalid email or password", android.widget.Toast.LENGTH_SHORT).show();
                }

            }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}