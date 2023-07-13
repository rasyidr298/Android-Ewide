package com.ewide.test.rasyidr.utilities.extenstion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

fun FragmentActivity.navigateFragment(
    frameLayout: Int,
    fragment: Fragment
) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameLayout, fragment)
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        .commit()
}