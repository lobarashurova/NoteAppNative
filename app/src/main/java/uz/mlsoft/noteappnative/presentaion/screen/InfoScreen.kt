package uz.mlsoft.noteappnative.presentaion.screen

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import uz.mlsoft.noteappnative.R
import uz.mlsoft.noteappnative.databinding.ScreenInfoBinding
import uz.mlsoft.noteappnative.utils.finish

@AndroidEntryPoint
class InfoScreen : Fragment(R.layout.screen_info) {
    private lateinit var binding: ScreenInfoBinding
    var content = "<div>\n" +
            "        <h1>\n" +
            "            Note app\n" +
            "        </h1>\n" +
            "        \n" +
            "        <ul>\n" +
            "            <li>\n" +
            "                \tThis app was created by a coder girl who studied in Gita academy and it should be used to save daily notes of yours.\n" +
            "            </li> \n" +
            "            <li>\n" +
            "                \tIn main screen, you can add categories to add your notes. In addition to that, after clicking " +
            "items of each category, you pass notes screen and there you can add notes \n" +
            "            </li> \n" +
            "            <li>\n" +
            "                \tIn menu screen you can see all notes which you had added before. Also, you can share and rate app there \n" +
            "            </li>\n" +
            "            <li>\n" +
            "               \tIn this app, I used so many web sites to dowloand icons and lottie animtions \n" +
            "            </li>\n" +
            "            <li>\n" +
            "               \tThey are:lottiefiles.com, flaticon.com\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "        <div>\n" +
            "            <h3>\n" +
            "                Framework:\n" +
            "            </h3>\n" +
            "            <ul>\n" +
            "                <li>\n" +
            "                    <b>\n" +
            "                        Android Studio\n" +
            "                    </b>\n" +
            "                </li>\n" +
            "                <li>\n" +
            "                    <b>\n" +
            "                        Kotlin\n" +
            "                    </b>\n" +
            "                </li>\n" +
            "                    <div>\n" +
            "                        <h3>\n" +
            "                            Used technologies:\n" +
            "                        </h3>\n" +
            "                        <ul>\n" +
            "                            <li>\n" +
            "                                Room database\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                One to many connection\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Lottie animation\n" +
            "                            </li>\n" +
            "                            <li>\n" +
            "                                Navigation menu\n" +
            "                            <li>\n" +
            "                                Html editor\n" +
            "                            </li>\n" +
            "                        </ul>\n" +
            "                    </div>\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "    </div>"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text = binding.descriptionText

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)
        } else text.text = Html.fromHtml(content)

        binding.backImage.setOnClickListener {
            finish()
        }
    }


}


