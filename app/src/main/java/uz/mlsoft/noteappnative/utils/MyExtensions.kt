package uz.mlsoft.noteappnative.utils

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import timber.log.Timber
import uz.mlsoft.noteappnative.R


fun TextView.setHtml(value: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        if (Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT).length > 36) {
            this.text = "${Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT).substring(0, 35)} ..."
        } else {
            this.text = Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT)
        }
    } else {
        if (Html.fromHtml(value, Html.FROM_HTML_MODE_COMPACT).length > 36) {
            this.text = "${Html.fromHtml(value).substring(0, 35)}..."
        } else {
            this.text = Html.fromHtml(value)
        }
    }
}


fun AppCompatActivity.createFragment(id: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(id, fragment)
        .commit()
}

fun Fragment.createFragment(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .replace(R.id.fragmentContainerView, fragment)
        .commit()
}

fun Fragment.startFragment(fragment: Fragment) {
    parentFragmentManager.beginTransaction()
        .addToBackStack(fragment::class.java.name)
        .replace(R.id.fragmentContainerView, fragment)
        .commit()
}

fun Fragment.finish() {
    parentFragmentManager.popBackStack()
}

fun Fragment.playStoreUrl() =
    "Dowloand now there!: " + "https://play.google.com/store/apps/details?id=${context?.applicationContext?.packageName}"


fun myTimber(message: String) {
    Timber.tag("TTT").d(message)
}


