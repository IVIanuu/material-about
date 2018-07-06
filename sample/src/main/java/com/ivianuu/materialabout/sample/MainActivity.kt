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
import com.ivianuu.materialabout.convenience.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.buildModelsWith2 {
            aboutCard {
                models {
                    appTitle(this@MainActivity)
                    versionAction(this@MainActivity, "Version") {
                        icon(R.drawable.ic_info)
                    }
                }
            }

            aboutCard {
                models {
                    title("Author")

                    aboutAction {
                        text("Manuel Wrage")
                        subText("Ostfildern, Germany")
                        icon(R.drawable.ic_person)
                    }
                    aboutAction {
                        text("Write an email")
                        icon(R.drawable.ic_email)
                        emailClickAction(this@MainActivity, "IVIanuu@gmail.com")
                    }
                    aboutAction {
                        text("Add to Google+ circles")
                        icon(R.drawable.ic_google_plus)
                        urlClickAction(this@MainActivity, "https://plus.google.com/+ManuMoncleaah")
                    }
                    aboutAction {
                        text("Follow on Github")
                        icon(R.drawable.ic_github)
                        urlClickAction(this@MainActivity, "https://github.com/IVIanuu/")
                    }
                }
            }

            aboutCard {
                title("Other Apps")

                models {
                    aboutAction {
                        text("OnePlus Gestures")
                        subText("OnePlus Gestures allows you to control your device via intuitive gestures.")
                        icon("https://lh3.googleusercontent.com/ZGQjLsxCDfw-pOMgUEI5-869P2jMeBy2QQHAJ6FlorcTrd8YnyxXGtU127Q_YcRQlVU=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.oneplusgestures"
                        )
                    }

                    aboutAction {
                        text("Pie Controls")
                        subText("Pie Controls known from Paranoid Android is a status and navigation bar replacement.")
                        icon("https://lh3.googleusercontent.com/tGUt_URTn1PRUZXk5VuPMpY9WpfT1e8cZzf0bmeQtU3uvhQCEf1FAWHxUKF4JbkAUTA=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.pie"
                        )
                    }

                    aboutAction {
                        text("Hide Navigation Bar")
                        subText("Hide Navigation Bar allows you to hide your navigation bar without root to get more screen estate or use gesture control apps.")
                        icon("https://lh3.googleusercontent.com/ppC3dUtl2JzTs2zooTH6hjhNGqKipq4z8RB3FP4EsRGvAlutSzKOv3UeYt2DSot7kko2=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.hidenavbar"
                        )
                    }

                    aboutAction {
                        text("Immersive Mode Manager")
                        subText("Immersive Mode Manager allows you to enable immersive in a either global or per app fashion without root.")
                        icon("https://lh3.googleusercontent.com/VIIfx5naZsNVEjhdOdQ7rcT2xcy0H8EEPUu4FvJn_FoU2NYPylCoYI27NHgVA_ParQ=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.immersivemodemanager"
                        )
                    }

                    aboutAction {
                        text("Now Playing Stories")
                        subText("Now Playing Stories supercharges your Google Pixel 2 device by keeping track of your now playing songs.")
                        icon("https://lh3.googleusercontent.com/VCWmMtpptPmRZG7hG8KQJfNTKWI88v26PdQlNhx_seqkdaw_rv60oOKNyWGtFsNTITM=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.nowplayinghistory"
                        )
                    }

                    aboutAction {
                        text("Unique Controls")
                        subText("Unique Controls brings you the famous Halo and Pie features known from Paranoid Android.")
                        icon("https://lh3.googleusercontent.com/T9oIjQmQKwaBPWsRREaMqQroFdm8RD5v8Fx4j6i995KjJIAKLAfEYuC4ar5n3_6kAa0=s180-rw")
                        tintIcon(false)
                        urlClickAction(
                            this@MainActivity,
                            "https://play.google.com/store/apps/details?id=com.ivianuu.halo"
                        )
                    }
                }
            }

            for (i in 0 until 100) {
                aboutCard {
                    models {
                        appTitle(this@MainActivity)

                        versionAction(this@MainActivity, "Version")

                        aboutAction {
                            icon(R.drawable.abc_ic_menu_share_mtrl_alpha)
                            text("Rate")
                            subText("Rate this app if you like it.")
                            rateClickAction(this@MainActivity)
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
