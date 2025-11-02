package com.openkeyboard.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.openkeyboard.myapplication.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val RubikFontFamily = FontFamily(
    Font(GoogleFont("Rubik"), provider)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    labelSmall = TextStyle(
        fontFamily = RubikFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp
    )
)
