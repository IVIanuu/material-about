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

package com.ivianuu.materialabout.sample

import android.app.Application
import com.bumptech.glide.request.RequestOptions
import com.ivianuu.materialabout.MaterialAboutPlugins
import com.ivianuu.materialabout.glide.GlideAboutIconLoader

/**
 * @author Manuel Wrage (IVIanuu)
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MaterialAboutPlugins.addIconLoader(
            GlideAboutIconLoader(
                RequestOptions()
                    .circleCrop()
            )
        )
    }

}