package com.jagdish.sequentialtabdemo

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jagdish.sequentialtabdemo.adapter.ViewPagerAdapter
import com.jagdish.sequentialtabdemo.fragments.FirstFragment
import com.jagdish.sequentialtabdemo.fragments.FourthFragment
import com.jagdish.sequentialtabdemo.fragments.SecondFragment
import com.jagdish.sequentialtabdemo.fragments.ThirdFragment


class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var toolbar: Toolbar

    var totalPages = 4;
    var lastIndex = 0;
    var autoSelected = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewPager = findViewById(R.id.viewPager) as ViewPager
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FirstFragment(), "MSN")
        adapter.addFragment(SecondFragment(), "YAHOO")
        adapter.addFragment(ThirdFragment(), "TIMES NOW")
        adapter.addFragment(FourthFragment(), "BING")
        viewPager.adapter = adapter

        viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
//                Toast.makeText(applicationContext, "Current page selected index " + position, Toast.LENGTH_SHORT).show()
                if (position != lastIndex) {
                    autoSelected = false
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    public fun loadNextPage() {
        var currentIndex = viewPager.currentItem
        if (currentIndex < totalPages) {
            this.lastIndex = currentIndex + 1;

            // Added wait handler to see the loaded pages
            // If internet speed is good the page will loaded faster and user will not able to see data
            val handler = Handler()
            handler.postDelayed(Runnable {
                viewPager.setCurrentItem(lastIndex);
            }, 2500)
        }
    }


}
