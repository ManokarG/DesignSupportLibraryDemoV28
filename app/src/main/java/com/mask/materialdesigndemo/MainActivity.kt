package com.mask.materialdesigndemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.support.design.chip.Chip
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBottomBar.text = "FAB Gravity End"
        btnBottomBar.setOnClickListener {
            if(bottomBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                btnBottomBar.text = "FAB Gravity Center"
            }else{
                bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                btnBottomBar.text = "FAB Gravity End"
            }
        }

        checkableChip.setOnCheckedChangeListener { _, b ->
            bottomBar.isFabAttached = b
        }


        (0 until chipGroup.childCount)
                .map{ chipGroup.getChildAt(it) as Chip }.forEach {
                        Toast.makeText(this@MainActivity, it.chipText, Toast.LENGTH_SHORT).show()
                }

        (0 until chipGroupCheckable.childCount)
                .map { chipGroupCheckable.getChildAt(it) as Chip }
                .forEach {
                    it.isCheckable = true
                    it.setOnCheckedChangeListener { _, b ->
                        val text = if(b){
                            "${it.chipText} is selected"
                        }else{
                            "${it.chipText} is not selected"
                        }
                        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
                    }
                }

        chipSwitchMode.setOnClickListener {
            if(chipGroupCheckable.isSingleLine){
                chipGroupCheckable.isSingleLine = false
                chipSwitchMode.chipText = "Single Line?"
            }else{
                chipSwitchMode.chipText = "Multiple Line?"
                chipGroupCheckable.isSingleLine = true
            }
        }

        closeChip.setOnCloseIconClickListener {
            Toast.makeText(this@MainActivity, "Close Icon clicked", Toast.LENGTH_SHORT).show()
        }

        chipWithIcon.isCheckable = true
        chipWithIcon.setOnCheckedChangeListener { _, b ->
            if(b){
                bottomBar.setCradleVerticalOffset(0)
            }else{
                bottomBar.setCradleVerticalOffset(40)
            }
        }

        bottomBar.replaceMenu(R.menu.main_menu)
        bottomBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
    }
}
