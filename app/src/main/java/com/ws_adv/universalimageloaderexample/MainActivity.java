package com.ws_adv.universalimageloaderexample;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.DiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView imageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // this tells the image loader library to save images on internal memory
        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(opts)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);

        imageListView = (ListView)findViewById(R.id.imageListView);

        ArrayList<String> urls = new ArrayList<String>();

        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/b/bd/Earthshaker.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/f/fb/Axe.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/d/dd/Sven.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/9/9f/Pudge.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/2/2c/Sand_King.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/7/7b/Slardar.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/d/d6/Beastmaster.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/6/6c/Tidehunter.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/4/4e/Dragon_Knight.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/f/f2/Wraith_King.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/b/b3/Clockwerk.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/6/65/Lifestealer.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/a/ab/Omniknight.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/b/bb/Night_Stalker.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/2/2b/Huskar.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/0/0b/Doom.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/5/5b/Alchemist.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/8/82/Spirit_Breaker.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/4/43/Brewmaster.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/1/15/Lycan.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/7/75/Tiny.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/8/89/Kunkka.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/1/18/Treant_Protector.png");
        urls.add("http://hydra-media.cursecdn.com/dota2.gamepedia.com/c/cf/Chaos_Knight.png");

        imageListView.setAdapter(new ImagesAdapter(this, R.layout.image, urls));
    }

    private class ImagesAdapter extends ArrayAdapter<String> {

        private int resource;

        public ImagesAdapter(Context context, int resource, ArrayList<String> urls) {
            super(context, resource, urls);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null)
            {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(resource, null);
            }

            String url = getItem(position);

            ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);

            imageView.setImageBitmap(null); // <---- this one

            // the following line will take time executing. it may cause old image to display
            // on the image view until the new one get displayed. read how listview and adapter works
            // as a solution the above line added to clear current image.
            // may be the library do that for us not sure
            ImageLoader.getInstance().displayImage(url, imageView);

            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
