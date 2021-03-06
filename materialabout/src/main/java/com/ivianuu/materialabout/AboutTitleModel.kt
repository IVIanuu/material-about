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

import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
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
    private val icon: Any? = null,
    private val iconColor: Int = 0,
    private val iconColorRes: Int = 0,
    private val tintIcon: Boolean = true,
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

            val icon = icon
            if (icon != null) {
                item_image.visibility = View.VISIBLE

                val iconLoader = MaterialAboutPlugins.getIconLoader(icon)
                        ?: throw IllegalStateException("no icon loader found for $icon")
                iconLoader.loadIcon(icon, item_image)
            } else {
                item_image.visibility = View.GONE
            }


            if (tintIcon) {
                when {
                    iconColor != 0 -> {
                        item_image.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN)
                    }
                    iconColorRes != 0 -> {
                        item_image.setColorFilter(
                            ContextCompat.getColor(containerView.context, iconColorRes),
                            PorterDuff.Mode.SRC_IN
                        )
                    }
                    else -> {
                        val ta =
                            containerView.context.obtainStyledAttributes(intArrayOf(android.R.attr.textColorSecondary))
                        val color = ta.getColor(0, 0)
                        ta.recycle()
                        item_image.setColorFilter(color, PorterDuff.Mode.SRC_IN)
                    }
                }
            } else {
                item_image.clearColorFilter()
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

            val clicksAllowed = clickAction != null || longClickAction != null
            containerView.isClickable = clicksAllowed
            containerView.isFocusable = clicksAllowed
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
        private var icon: Any? = null
        private var iconColor = 0
        private var iconColorRes = 0
        private var tintIcon = true
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

        fun icon(icon: Any?) {
            this.icon = icon
        }

        fun iconColor(iconColor: Int) {
            this.iconColor = iconColor
        }

        fun iconColorRes(iconColorRes: Int) {
            this.iconColorRes = iconColorRes
        }

        fun tintIcon(tintIcon: Boolean) {
            this.tintIcon = tintIcon
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
            title, titleRes, desc, descRes, icon,
            iconColor, iconColorRes, tintIcon,
            clickAction, longClickAction
        )
    }
}

fun EpoxyController.aboutTitle(init: AboutTitleModel.Builder.() -> Unit) =
        AboutTitleModel.Builder()
            .apply(init)
            .build()
            .also { it.addTo(this) }