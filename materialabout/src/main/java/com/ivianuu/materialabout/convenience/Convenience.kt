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
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.airbnb.epoxy.EpoxyController
import com.ivianuu.materialabout.AboutActionModel
import com.ivianuu.materialabout.AboutTitleModel
import com.ivianuu.materialabout.aboutAction
import com.ivianuu.materialabout.aboutTitle

fun EpoxyController.appTitle(
    appName: CharSequence,
    applicationIcon: Drawable,
    init: (AboutTitleModel.Builder.() -> Unit)? = null
) {
    aboutTitle {
        title(appName)
        icon(applicationIcon)
        tintIcon(false)
        init?.invoke(this)
    }
}

fun EpoxyController.appTitle(
    context: Context,
    init: (AboutTitleModel.Builder.() -> Unit)? = null
) {
    val applicationContext = context.applicationContext
    val packageManager = applicationContext.packageManager
    val applicationInfo = applicationContext.applicationInfo
    val appName = packageManager.getApplicationLabel(applicationInfo) ?: ""
    val applicationIcon = packageManager.getApplicationIcon(applicationInfo)
    appTitle(appName, applicationIcon, init)
}

fun EpoxyController.versionAction(
    context: Context,
    text: String? = null,
    icon: Drawable? = null,
    includeVersionCode: Boolean = true,
    init: (AboutActionModel.Builder.() -> Unit)? = null
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
        init?.invoke(this)
    }
}

fun AboutActionModel.Builder.intentClickAction(context: Context, intent: Intent) {
    clickAction(IntentClickAction(context, intent))
}

fun AboutActionModel.Builder.urlClickAction(context: Context, url: String) {
    clickAction(UrlClickAction(context, url))
}

fun AboutActionModel.Builder.rateClickAction(context: Context) {
    clickAction(RateClickAction(context))
}

fun AboutTitleModel.Builder.emailClickAction(
    context: Context,
    email: String,
    subject: String? = null,
    chooserTitle: String? = null
) {
    clickAction(EmailClickAction(context, email, subject, chooserTitle))
}

fun AboutTitleModel.Builder.intentClickAction(context: Context, intent: Intent) {
    clickAction(IntentClickAction(context, intent))
}

fun AboutTitleModel.Builder.urlClickAction(context: Context, url: String) {
    clickAction(UrlClickAction(context, url))
}

fun AboutTitleModel.Builder.rateClickAction(context: Context) {
    clickAction(RateClickAction(context))
}

fun AboutActionModel.Builder.emailClickAction(
    context: Context,
    email: String,
    subject: String? = null,
    chooserTitle: String? = null
) {
    clickAction(EmailClickAction(context, email, subject, chooserTitle))
}

fun AboutActionModel.Builder.phoneClickAction(
    context: Context,
    number: String
) {
    clickAction(PhoneClickAction(context, number))
}