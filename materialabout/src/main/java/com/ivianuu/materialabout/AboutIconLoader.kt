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

package com.ivianuu.materialabout

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

/**
 * Loads icons
 */
interface AboutIconLoader {

    fun canHandle(icon: Any): Boolean

    fun loadIcon(icon: Any, imageView: ImageView)

}

open class DefaultAboutIconLoader : AboutIconLoader {
    override fun canHandle(icon: Any) =
        icon is Drawable
                || icon is Bitmap
                || icon is Int
                || icon is Uri

    override fun loadIcon(icon: Any, imageView: ImageView) {
        when (icon) {
            is Drawable -> imageView.setImageDrawable(icon)
            is Bitmap -> imageView.setImageBitmap(icon)
            is Int -> imageView.setImageResource(icon)
            is Uri -> imageView.setImageURI(icon)
        }
    }
}