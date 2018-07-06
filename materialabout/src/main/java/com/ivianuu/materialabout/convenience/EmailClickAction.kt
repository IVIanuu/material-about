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
import android.net.Uri

/**
 * Email click action
 */
open class EmailClickAction(
    context: Context,
    email: String,
    subject: String? = null,
    chooserTitle: CharSequence? = null
) : IntentClickAction(
    context,
    Intent.createChooser(
        Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email")).apply {
            subject.let { putExtra(Intent.EXTRA_SUBJECT, it) }
        }, chooserTitle
    )
)