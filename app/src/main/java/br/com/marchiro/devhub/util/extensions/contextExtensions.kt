package br.com.marchiro.devhub.util.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

fun Context.showError() {
    Toast.makeText(
        this,
        "Ocorreu algum erro",
        Toast.LENGTH_LONG
    ).show()
}


fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}