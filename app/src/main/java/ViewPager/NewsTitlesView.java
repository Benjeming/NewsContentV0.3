package ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.ludans.newscontent.R;

import java.util.ArrayList;

import adapter.NewsTitleListViewAdapter;
import domain.NewsBean;
import domain.NewsBean1;

@SuppressLint("ValidFragment")
public class NewsTitlesView extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<NewsBean1> mData;
    private FragmentManager fm ;
    private NewsContentView newsContentView = null;
    private static final String TAG = "NewsTitlesView";
    private Bundle bundle = new Bundle();


    public NewsTitlesView(ArrayList<NewsBean1> mData, FragmentManager fm) {
        this.mData = mData;
        this.fm = fm;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_news_titles, container, false);
        ListView  listView = view.findViewById(R.id.list_title);

        NewsTitleListViewAdapter myAdapter = new NewsTitleListViewAdapter(mData, getActivity());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        newsContentView = new NewsContentView();
        Log.e(TAG, "onItemClick: 创建了NewsContent ." );

        bundle.putString("content",mData.get(position).getCotent());
        newsContentView.setArguments(bundle);
        Log.e(TAG, "onItemClick: Bundle数据传输成功" );

        FragmentTransaction ft = fm.beginTransaction();
//        ft.setCustomAnimations(R., R.anim.fragment_slide_left_exit);
        ft.replace(R.id.fl_content,newsContentView);
        ft.addToBackStack(null);
        ft.commit();

    }
}
