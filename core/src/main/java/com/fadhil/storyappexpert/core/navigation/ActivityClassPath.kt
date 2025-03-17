package com.fadhil.storyappexpert.core.navigation

import android.content.Context
import android.content.Intent
import com.gaelmarhic.quadrant.QuadrantConstants.ADD_STORY_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.HOME_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.LOGIN_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.REGISTER_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.SPLASH_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.STORY_MAPS_ACTIVITY
import com.gaelmarhic.quadrant.QuadrantConstants.FAVORIT_STORY_ACTIVITY

enum class ActivityClassPath(private val className: String) {
    Splash(SPLASH_ACTIVITY),
    Register(REGISTER_ACTIVITY),
    Login(LOGIN_ACTIVITY),
    Home(HOME_ACTIVITY),

    AddStory(ADD_STORY_ACTIVITY),
    StoryMaps(STORY_MAPS_ACTIVITY),
    FavoriteStory(FAVORIT_STORY_ACTIVITY);

    fun getIntent(context: Context) = Intent(context, Class.forName(className))
}