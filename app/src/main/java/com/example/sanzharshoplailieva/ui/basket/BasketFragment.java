package com.example.sanzharshoplailieva.ui.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sanzharshoplailieva.R;
import com.example.sanzharshoplailieva.databinding.FragmentDashboardBinding;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sanzharshoplailieva.models.ModelM;
import com.example.sanzharshoplailieva.ui.home.ShopAdapter;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ArrayList<ModelM> basket_products;
    ShopAdapter adapter;
    NavController navController;
    Double total_sum = 0.0;
    LottieAnimationView lotty_buying;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BasketViewModel dashboardViewModel =
                new ViewModelProvider(this).get(BasketViewModel.class);


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lotty_buying=binding.lottyBuying;
//        lotty_buying.setAnimation(R.raw.lottybuying);
        if (getArguments() != null) {
            basket_products = new ArrayList<>();
            basket_products = getArguments().getParcelableArrayList("keysss_basket");
        }
        if (basket_products != null) {
            binding.placeHolder.setVisibility(View.GONE);
            adapter = new ShopAdapter(requireActivity(), basket_products);
            binding.rvBasket.setAdapter(adapter);
        } else {
            binding.placeHolder.setVisibility(View.VISIBLE);
        }

        try {
            for (int i = 0; i <basket_products.size(); i++) {
                total_sum += basket_products.get(i).getModelPrice();
            }
            binding.basketTotalCount.setText(String.valueOf(total_sum - 1.1));
        } catch (NullPointerException ex) {
            binding.basketTotalCount.setText("0.00");
            binding.placeHolder.setVisibility(View.VISIBLE);
            Log.e("TAG", "error" + ex.getLocalizedMessage());
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnBack.setOnClickListener(v -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_dashboard_to_navigation_home);
        });

        binding.btnPay.setOnClickListener(v1 -> {
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_payment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}