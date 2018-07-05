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

import android.graphics.drawable.Drawable
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_about_title.*
import java.util.*

/**
 * About action model
 */
class AboutTitleModel(
    private val title: CharSequence? = null,
    private val titleRes: Int = 0,
    private val desc: CharSequence? = null,
    private val descRes: Int = 0,
    private val icon: Drawable? = null,
    private val iconRes: Int = 0,
    private val clickAction: AboutClickAction? = null,
    private val longClickAction: AboutClickAction? = null
) : EpoxyModelWithHolder<AboutEpoxyHolder>() {

    init {
        id(UUID.randomUUID().toString())
        layout(R.layout.item_about_title)
    }

    override fun createNewHolder() = AboutEpoxyHolder()

    override fun getDefaultLayout() = R.layout.item_about_action

    override fun bind(holder: AboutEpoxyHolder) {
        super.bind(holder)
        with(holder) {
            when {
                title != null -> {
                    item_title.visibility = View.VISIBLE
                    item_title.text = title
                }
                titleRes != 0 -> {
                    item_title.visibility = View.VISIBLE
                    item_title.setText(titleRes)
                }
                else -> {
                    item_title.visibility = View.GONE
                    item_title.text = null
                }
            }

            when {
                desc != null -> {
                    item_desc.visibility = View.VISIBLE
                    item_desc.text = desc
                }
                descRes != 0 -> {
                    item_desc.visibility = View.VISIBLE
                    item_desc.setText(descRes)
                }
                else -> {
                    item_desc.visibility = View.GONE
                    item_desc.text = null
                }
            }

            when {
                icon != null -> {
                    item_image.visibility = View.VISIBLE
                    item_image.setImageDrawable(icon)
                }
                iconRes != 0 -> {
                    item_image.visibility = View.VISIBLE
                    item_image.setImageResource(iconRes)
                }
                else -> item_image.visibility = View.GONE
            }

            val clickAction = clickAction
            if (clickAction != null) {
                containerView.setOnClickListener { clickAction.onClick() }
            } else {
                containerView.setOnClickListener(null)
            }

            val longClickAction = longClickAction
            if (longClickAction != null) {
                containerView.setOnLongClickListener {
                    longClickAction.onClick()
                    true
                }
            } else {
                containerView.setOnLongClickListener(null)
            }
        }
    }

    override fun unbind(holder: AboutEpoxyHolder) {
        super.unbind(holder)
        with(holder) {
            item_title.text = null
            item_desc.text = null
            item_image.setImageDrawable(null)
            containerView.setOnClickListener(null)
            containerView.setOnLongClickListener(null)
        }
    }

    class Builder {

        private var title: CharSequence? = null
        private var titleRes = 0
        private var desc: CharSequence? = null
        private var descRes = 0
        private var icon: Drawable? = null
        private var iconRes = 0
        private var clickAction: AboutClickAction? = null
        private var longClickAction: AboutClickAction? = null

        fun title(title: CharSequence?) {
            this.title = title
        }

        fun titleRes(titleRes: Int) {
            this.titleRes = titleRes
        }

        fun desc(desc: CharSequence?) {
            this.desc = desc
        }

        fun descRes(descRes: Int) {
            this.descRes = descRes
        }

        fun icon(icon: Drawable?) {
            this.icon = icon
        }

        fun iconRes(iconRes: Int) {
            this.iconRes = iconRes
        }

        fun clickAction(clickAction: AboutClickAction?) {
            this.clickAction = clickAction
        }

        fun clickAction(onClick: () -> Unit) {
            clickAction(object : AboutClickAction {
                override fun onClick() {
                    onClick.invoke()
                }
            })
        }

        fun longClickAction(longClickAction: AboutClickAction?) {
            this.longClickAction = longClickAction
        }

        fun longClickAction(onClick: () -> Unit) {
            longClickAction(object : AboutClickAction {
                override fun onClick() {
                    onClick.invoke()
                }
            })
        }

        fun build() = AboutTitleModel(
            title, titleRes, desc, descRes, icon, iconRes, clickAction, longClickAction
        )
    }
}

fun EpoxyController.aboutTitle(init: AboutTitleModel.Builder.() -> Unit) =
        AboutTitleModel.Builder()
            .apply(init)
            .build()
            .also { it.addTo(this) }