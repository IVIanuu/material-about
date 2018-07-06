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

package com.ivianuu.materialabout.glide

import android.Manifest
import android.support.annotation.RequiresPermission
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ivianuu.materialabout.AboutIconLoader

/**
 * Glide about icon loader
 */
open class GlideAboutIconLoader @RequiresPermission(Manifest.permission.INTERNET) constructor(
    private val requestOptions: RequestOptions? = null
) : AboutIconLoader {

    override fun canHandle(icon: Any) = icon is String

    override fun loadIcon(icon: Any, imageView: ImageView) {
        Log.d("testt", "load icon -> $icon")
        Glide.with(imageView)
            .load(icon as String)
            .apply { requestOptions?.let { apply(requestOptions) } }
            .into(imageView)
    }
}