package com.jagdish.sequentialtabdemo

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.jagdish.sequentialtabdemo.adapter.ViewPagerAdapter
import com.jagdish.sequentialtabdemo.fragments.FirstFragment
import com.jagdish.sequentialtabdemo.fragments.SecondFragment
import com.jagdish.sequentialtabdemo.fragments.ThirdFragment

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var toolbar : Toolbar

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
        adapter.addFragment(FirstFragment(), "First")
        adapter.addFragment(SecondFragment(), "Second")
        adapter.addFragment(ThirdFragment(), "Third")
        viewPager.adapter = adapter
    }
}
