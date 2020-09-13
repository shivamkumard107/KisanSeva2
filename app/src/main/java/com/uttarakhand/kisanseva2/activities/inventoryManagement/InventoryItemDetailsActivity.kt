package com.uttarakhand.kisanseva2.activities.inventoryManagement

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.uttarakhand.kisanseva2.R
import com.uttarakhand.kisanseva2.fragments.ItemInfoFragment
import com.uttarakhand.kisanseva2.fragments.RatingReviewsFragment
import com.uttarakhand.kisanseva2.model.Item

class InventoryItemDetailsActivity : AppCompatActivity() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        val gson = Gson()
        val item = gson.fromJson<Item>(intent.getStringExtra("item"), Item::class.java)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = getString(R.string.item_detail_heading)
        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, item, this)

        mViewPager = findViewById<View>(R.id.view_pager) as ViewPager
        mViewPager?.adapter = mSectionsPagerAdapter
        val tabLayout = findViewById<View>(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)
        tabLayout.setTabTextColors(getColor(android.R.color.white), getColor(R.color.colorAccent))
    }


    class SectionsPagerAdapter(fm: FragmentManager, private val item: Item, private val context: Context) :
            FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            var fragment: Fragment =
                    ItemInfoFragment(item)
            when (position) {
                0 -> fragment =
                        ItemInfoFragment(item)
                1 -> fragment =
                        RatingReviewsFragment()
            }
            return fragment
        }

        override fun getCount(): Int {
            // Show 2 total pages.
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return context.getString(R.string.tab1_item_details)
                1 -> return context.getString(R.string.tab2_reviews)
            }
            return null
        }
    }
}