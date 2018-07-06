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
import com.ivianuu.materialabout.AboutClickAction
import com.ivianuu.materialabout.MaterialAboutPlugins

/**
 * Intent click action
 */
open class IntentClickAction(
    private val context: Context,
    private val intent: Intent
) : AboutClickAction {

    override fun onClick() {
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            MaterialAboutPlugins.exceptionHandler.onException(e)
        }
    }
}