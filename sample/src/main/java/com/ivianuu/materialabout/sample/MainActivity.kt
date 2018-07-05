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

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.ivianuu.materialabout.aboutAction
import com.ivianuu.materialabout.aboutCard
import com.ivianuu.materialabout.convenience.appTitle
import com.ivianuu.materialabout.convenience.rateClickListener
import com.ivianuu.materialabout.convenience.versionAction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.buildModelsWith2 {
            aboutCard {
                models {
                    appTitle(this@MainActivity)
                    versionAction(this@MainActivity)
                }
            }

            aboutCard {
                models {
                    aboutAction {
                        text("Manuel Wrage")
                        subText("Ostfildern, Germany")
                    }

                    aboutAction {
                        text("Write an Email")
                    }
                }
            }

            for (i in 0 until 100) {
                aboutCard {
                    models {
                        appTitle(this@MainActivity)

                        versionAction(this@MainActivity, "Version")

                        aboutAction {
                            iconRes(R.drawable.abc_ic_menu_share_mtrl_alpha)
                            text("Rate")
                            subText("Rate this app if you like it.")
                            rateClickListener(this@MainActivity)
                        }

                        aboutAction {
                            text("My Action")
                            subText("My Subtext")
                            clickAction { Log.d("testt", "on click") }
                        }
                    }
                }
            }
        }
    }

    fun EpoxyRecyclerView.buildModelsWith2(callback: EpoxyController.() -> Unit) {
        buildModelsWith(callback)
    }
}
