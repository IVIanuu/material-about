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

/**
 * Material about plugins
 */
object MaterialAboutPlugins {

    private val iconLoaders = mutableSetOf<AboutIconLoader>()

    init {
        addIconLoader(DefaultAboutIconLoader())
    }

    fun addIconLoader(iconLoader: AboutIconLoader) {
        iconLoaders.add(iconLoader)
    }

    fun removeIconLoader(iconLoader: AboutIconLoader) {
        iconLoaders.remove(iconLoader)
    }

    fun getIconLoader(icon: Any) =
        iconLoaders.firstOrNull { it.canHandle(icon) }

}