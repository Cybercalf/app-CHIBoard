package com.example.twenty_sixth_april;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutLicenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static AboutLicenseFragment singletonInstance;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // 在此Fragment中的按钮
    private Button bt_close_license;

    private AboutLicenseFragment() {

    }

    /**
     * 使用单例模式
     * @return singletonInstance of AboutLicenceFragment
     */
    public static AboutLicenseFragment getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new AboutLicenseFragment();
        }
        return singletonInstance;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutLicenceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutLicenseFragment newInstance(String param1, String param2) {
        AboutLicenseFragment fragment = new AboutLicenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_license, container, false);
        TextView textView = view.findViewById(R.id.tv_license);
        textView.setText(License.description);
        // 为button设置监听器
        bt_close_license = view.findViewById(R.id.bt_close_license);
        bt_close_license.setOnClickListener(v -> {
            // 让创建的Fragment出栈。入栈操作见AboutActivity
            assert getFragmentManager() != null;
            getFragmentManager().popBackStack();
        });
        return view;
    }
}