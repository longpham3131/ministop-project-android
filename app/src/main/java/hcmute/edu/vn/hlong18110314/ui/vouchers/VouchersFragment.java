package hcmute.edu.vn.hlong18110314.ui.vouchers;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.hlong18110314.R;

public class VouchersFragment extends Fragment {

    private VouchersViewModel mViewModel;
    private TabLayout tabLayout ;
    private ViewPager viewPager;

    public static VouchersFragment newInstance() {
        return new VouchersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(VouchersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vouchers, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);

        viewPager = (ViewPager) root.findViewById(R.id.viewpagerVouchers);
        tabLayout = (TabLayout) root.findViewById(R.id.tabsVouchers);
        Log.d("view","11111");
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

//        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VouchersViewModel.class);


        // TODO: Use the ViewModel
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Vouvhers_VouchersFragment(), "Vouchers");
        adapter.addFragment(new Vouchers_TaskFragment(), "Nhiệm vụ");
        Log.d("Adapter", adapter.mFragmentList.toString());

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter  {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
//    private void setIcon()
//    {
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_child_care_24);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_border_clear_24);
//    }
}