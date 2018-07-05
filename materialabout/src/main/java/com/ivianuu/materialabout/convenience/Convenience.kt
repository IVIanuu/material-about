/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.materialabout.convenience

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.airbnb.epoxy.EpoxyController
import com.ivianuu.materialabout.AboutActionModel
import com.ivianuu.materialabout.AboutTitleModel
import com.ivianuu.materialabout.aboutAction
import com.ivianuu.materialabout.aboutTitle

fun EpoxyController.appTitle(appName: CharSequence, applicationIcon: Drawable) {
    aboutTitle {
        title(appName)
        icon(applicationIcon)
    }
}

fun EpoxyController.appTitle(context: Context) {
    val applicationContext = context.applicationContext
    val packageManager = applicationContext.packageManager
    val applicationInfo = applicationContext.applicationInfo
    val appName = packageManager.getApplicationLabel(applicationInfo) ?: ""
    val applicationIcon = packageManager.getApplicationIcon(applicationInfo)
    appTitle(appName, applicationIcon)
}

fun EpoxyController.versionAction(
    context: Context,
    text: String? = null,
    icon: Drawable? = null,
    includeVersionCode: Boolean = true
) {
    var versionName = ""
    var versionCode = 0
    try {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        versionName = pInfo.versionName
        versionCode = pInfo.versionCode
    } catch (ignored: PackageManager.NameNotFoundException) {
        // This shouldn't happen.
    }

    aboutAction {
        text(text)
        subText(versionName + if (includeVersionCode) " ($versionCode)" else "")
        icon(icon)
    }
}

fun AboutActionModel.Builder.rateClickListener(context: Context) {
    clickAction(RateClickAction(context))
}

fun AboutTitleModel.Builder.rateClickListener(context: Context) {
    clickAction(RateClickAction(context))
}