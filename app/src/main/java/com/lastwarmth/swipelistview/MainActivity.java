package com.lastwarmth.swipelistview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MyModel> data = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;
        initData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final SwipeAdapter myAdapter = new SwipeAdapter(data, this);
        ListView myListView = (ListView) findViewById(R.id.my_list);
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Toast.makeText(mContext, ((MyModel) myAdapter.getItem(position)).getGroupName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        MyModel model1 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg", "天龙八部", "人生万象，尽归于尘。");
        MyModel model2 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg", "射雕英雄传", "气势恢宠的英雄史诗，男女主人公都创了武侠世界的一种类型。 ");
        MyModel model3 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg", "多情剑客无情剑", "小李神话的诞生。");
        MyModel model4 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760756_3304.jpg", "连城诀", "平凡人的悲剧，以拙胜巧的典范、苦境的诞生。");
        MyModel model5 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760755_6715.jpeg", "温柔一刀", "对人性有更加深入的刻画。");
        MyModel model6 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760726_5120.jpg", "倚天屠龙记", "平凡的英雄，叵恻的江湖。");
        MyModel model7 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760726_8364.jpg", "笑傲江湖", "曲终人不见，江上数峰青。");
        MyModel model8 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760725_4031.jpg", "欢乐英雄", "古龙有一类小说讲英雄的境界，这是不错的一部。");
        MyModel model9 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760724_9463.jpg", "萧十一郎", "在古龙的小说中，很少有这种苦情的英雄，风四娘是最杰出的女人。");
        MyModel model10 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760724_2371.jpg", "神雕侠侣", "风陵夜话以后郭襄出场很有吸引力，但似乎有意地褒杨贬郭，感觉不太自然。所以我倒更喜欢武功不高时的杨过。");
        MyModel model11 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760707_4653.jpg", "杀楚", "非常接近于纯文学作品。我看过它的原型，发表在台北的纯文学刊物上。");
        MyModel model12 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760706_6864.jpg", "绝代双骄", "古龙转型的杰作。");
        MyModel model13 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760706_9279.jpg", "女帝奇英传", "女英雄的爱情悲剧，在武侠中极少见。");
        MyModel model14 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760704_2341.jpg", "陆小凤", "与楚留香可称一时瑜亮。");
        MyModel model15 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760704_5707.jpg", "名剑风流", "古龙小说中侦探手法用得最好的一部。");
        MyModel model16 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760685_5091.jpg", "逆水寒", "落难英雄的传奇。");
        MyModel model17 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760685_4444.jpg", "武林外史", "气象不凡，但稍嫌芜杂。古龙的小说一旦写开就常常收不拢。温瑞安也是如此。但古龙有自知之明，温则盲目自信。");
        MyModel model18 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760684_8827.jpg", "流星蝴蝶剑", "可以等待多年，而且一剑不发……");
        MyModel model19 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760683_3691.jpg", "天涯明月刀", "这部书的改革并不太成功，但古龙的探索精神值得尊重。");
        MyModel model20 = new MyModel("http://img.my.csdn.net/uploads/201508/05/1438760683_7315.jpg", "七剑下天山", "标志着梁氏天山派小说的诞生。我印象最深的是易兰珠在牢中回忆生平的几个男人。");
        data.add(model1);
        data.add(model2);
        data.add(model3);
        data.add(model4);
        data.add(model5);
        data.add(model6);
        data.add(model7);
        data.add(model8);
        data.add(model9);
        data.add(model10);
        data.add(model11);
        data.add(model12);
        data.add(model13);
        data.add(model14);
        data.add(model15);
        data.add(model16);
        data.add(model17);
        data.add(model18);
        data.add(model19);
        data.add(model20);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
