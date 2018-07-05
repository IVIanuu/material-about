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
import kotlinx.android.synthetic.main.item_about_action.*
import java.util.*

/**
 * About action model
 */
class AboutActionModel(
    private val text: CharSequence? = null,
    private val textRes: Int = 0,
    private val subText: CharSequence? = null,
    private val subTextRes: Int = 0,
    private val icon: Drawable? = null,
    private val iconRes: Int = 0,
    private val clickAction: AboutClickAction? = null,
    private val longClickAction: AboutClickAction? = null
) : EpoxyModelWithHolder<AboutEpoxyHolder>() {

    init {
        id(UUID.randomUUID().toString())
        layout(R.layout.item_about_action)
    }

    override fun bind(holder: AboutEpoxyHolder) {
        super.bind(holder)
        with(holder) {
            when {
                text != null -> {
                    item_text.visibility = View.VISIBLE
                    item_text.text = text
                }
                textRes != 0 -> {
                    item_text.visibility = View.VISIBLE
                    item_text.setText(textRes)
                }
                else -> {
                    item_text.visibility = View.GONE
                    item_text.text = null
                }
            }

            when {
                subText != null -> {
                    item_subtext.visibility = View.VISIBLE
                    item_subtext.text = subText
                }
                subTextRes != 0 -> {
                    item_subtext.visibility = View.VISIBLE
                    item_subtext.setText(subTextRes)
                }
                else -> {
                    item_subtext.visibility = View.GONE
                    item_subtext.text = null
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
            item_text.text = null
            item_subtext.text = null
            item_image.setImageDrawable(null)
            containerView.setOnClickListener(null)
            containerView.setOnLongClickListener(null)
        }
    }

    override fun getDefaultLayout() = R.layout.item_about_action

    override fun createNewHolder() = AboutEpoxyHolder()

    class Builder {

        private var text: CharSequence? = null
        private var textRes = 0
        private var subText: CharSequence? = null
        private var subTextRes = 0
        private var icon: Drawable? = null
        private var iconRes = 0
        private var clickAction: AboutClickAction? = null
        private var longClickAction: AboutClickAction? = null

        fun text(text: CharSequence?) {
            this.text = text
        }

        fun textRes(textRes: Int) {
            this.textRes = textRes
        }

        fun subText(subText: CharSequence?) {
            this.subText = subText
        }

        fun subTextRes(subTextRes: Int) {
            this.subTextRes = subTextRes
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

        fun build() = AboutActionModel(
            text, textRes, subText, subTextRes, icon, iconRes,
            clickAction, longClickAction
        )
    }
}

fun EpoxyController.aboutAction(init: AboutActionModel.Builder.() -> Unit) =
        AboutActionModel.Builder()
            .apply(init)
            .build()
            .also { it.addTo(this) }