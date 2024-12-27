package com.fadhil.storyappexpert.core.navigation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

interface ModuleNavigator {
    fun <T> T.navigateToStoryMapsActivity(resultLauncher: ActivityResultLauncher<Intent>? = null) where T : AppCompatActivity, T : ModuleNavigator {
        startStoryMapsActivity(ActivityClassPath.StoryMaps, resultLauncher)
    }

    fun <T> T.navigateToStoryMapsActivity(resultLauncher: ActivityResultLauncher<Intent>? = null) where T : Fragment, T : ModuleNavigator {
        startStoryMapsActivity(ActivityClassPath.StoryMaps, resultLauncher)
    }
}

private fun FragmentActivity.startStoryMapsActivity(
    activityClassPath: ActivityClassPath,
    resultLauncher: ActivityResultLauncher<Intent>?
) {
    val intent = activityClassPath.getIntent(this)
    if (resultLauncher != null) {
        resultLauncher.launch(intent)
    } else {
        ActivityCompat.startActivity(this, intent, null)
    }
}

private fun Fragment.startStoryMapsActivity(
    activityClassPath: ActivityClassPath,
    resultLauncher: ActivityResultLauncher<Intent>?
) {
    val intent = activityClassPath.getIntent(requireContext())
    if (resultLauncher != null) {
        resultLauncher.launch(intent)
    } else {
        ActivityCompat.startActivity(requireContext(), intent, null)
    }
}