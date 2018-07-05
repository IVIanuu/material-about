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

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_about_card.*
import java.util.*

/**
 * About card list model
 */
class AboutCardModel(
    private val title: CharSequence? =  null,
    private val titleRes: Int = 0,
    private val modelBuilder: ModelBuilder? = null
) : EpoxyModelWithHolder<AboutCardModel.Holder>() {

    init {
        id(UUID.randomUUID().toString())
        layout(R.layout.item_about_card)
    }

    override fun bind(holder: Holder) {
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

            epoxyController.modelBuilder = modelBuilder
        }
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)
        with(holder) {
            item_title.text = null
            epoxyController.modelBuilder = null
        }
    }

    override fun getDefaultLayout() = R.layout.item_about_card

    override fun createNewHolder() = Holder()

    interface ModelBuilder {
        fun buildModels(controller: EpoxyController)
    }

    class Holder : AboutEpoxyHolder() {

        val epoxyController = AboutCardListController()

        override fun bindView(itemView: View) {
            super.bindView(itemView)
            item_recyclerview.layoutManager = LinearLayoutManager(itemView.context)
            item_recyclerview.adapter = epoxyController.adapter
            item_recyclerview.isNestedScrollingEnabled = false
            item_recyclerview.itemAnimator = null
        }
    }

    class Builder {

        private var title: CharSequence? = null
        private var titleRes = 0
        private var modelBuilder: ModelBuilder? = null

        fun title(title: CharSequence?) {
            this.title = title
        }

        fun titleRes(titleRes: Int) {
            this.titleRes = titleRes
        }

        fun models(modelBuilder: ModelBuilder) {
            this.modelBuilder = modelBuilder
        }

        fun models(buildModels: EpoxyController.() -> Unit) {
            this.modelBuilder = object : ModelBuilder {
                override fun buildModels(controller: EpoxyController) {
                    buildModels.invoke(controller)
                }
            }
        }

        fun build() = AboutCardModel(
            title, titleRes, modelBuilder
        )
    }

    class AboutCardListController : EpoxyController() {

        internal var modelBuilder: ModelBuilder? = null
            set(value) {
                field = value
                requestModelBuild()
            }

        override fun buildModels() {
            modelBuilder?.buildModels(this)
        }
    }
}

fun EpoxyController.aboutCard(init: AboutCardModel.Builder.() -> Unit) =
    AboutCardModel.Builder()
            .apply(init)
            .build()
            .also { it.addTo(this) }