package com.system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import split_the_bill.app.shared.generated.resources.Geist_Black
import split_the_bill.app.shared.generated.resources.Geist_BlackItalic
import split_the_bill.app.shared.generated.resources.Geist_Bold
import split_the_bill.app.shared.generated.resources.Geist_BoldItalic
import split_the_bill.app.shared.generated.resources.Geist_ExtraBold
import split_the_bill.app.shared.generated.resources.Geist_ExtraBoldItalic
import split_the_bill.app.shared.generated.resources.Geist_ExtraLight
import split_the_bill.app.shared.generated.resources.Geist_ExtraLightItalic
import split_the_bill.app.shared.generated.resources.Geist_Italic
import split_the_bill.app.shared.generated.resources.Geist_Light
import split_the_bill.app.shared.generated.resources.Geist_LightItalic
import split_the_bill.app.shared.generated.resources.Geist_Medium
import split_the_bill.app.shared.generated.resources.Geist_MediumItalic
import split_the_bill.app.shared.generated.resources.Geist_Regular
import split_the_bill.app.shared.generated.resources.Geist_SemiBold
import split_the_bill.app.shared.generated.resources.Geist_SemiBoldItalic
import split_the_bill.app.shared.generated.resources.Geist_Thin
import split_the_bill.app.shared.generated.resources.Geist_ThinItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Bold
import split_the_bill.app.shared.generated.resources.JetBrainsMono_BoldItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_ExtraBold
import split_the_bill.app.shared.generated.resources.JetBrainsMono_ExtraBoldItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_ExtraLight
import split_the_bill.app.shared.generated.resources.JetBrainsMono_ExtraLightItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Italic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Light
import split_the_bill.app.shared.generated.resources.JetBrainsMono_LightItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Medium
import split_the_bill.app.shared.generated.resources.JetBrainsMono_MediumItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Regular
import split_the_bill.app.shared.generated.resources.JetBrainsMono_SemiBold
import split_the_bill.app.shared.generated.resources.JetBrainsMono_SemiBoldItalic
import split_the_bill.app.shared.generated.resources.JetBrainsMono_Thin
import split_the_bill.app.shared.generated.resources.JetBrainsMono_ThinItalic
import split_the_bill.app.shared.generated.resources.Res

@Composable
fun geistFontFamily() = FontFamily(
    Font(Res.font.Geist_Thin, FontWeight.Thin, FontStyle.Normal),
    Font(Res.font.Geist_ThinItalic, FontWeight.Thin, FontStyle.Italic),
    Font(Res.font.Geist_ExtraLight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(Res.font.Geist_ExtraLightItalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(Res.font.Geist_Light, FontWeight.Light, FontStyle.Normal),
    Font(Res.font.Geist_LightItalic, FontWeight.Light, FontStyle.Italic),
    Font(Res.font.Geist_Regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.Geist_Italic, FontWeight.Normal, FontStyle.Italic),
    Font(Res.font.Geist_Medium, FontWeight.Medium, FontStyle.Normal),
    Font(Res.font.Geist_MediumItalic, FontWeight.Medium, FontStyle.Italic),
    Font(Res.font.Geist_SemiBold, FontWeight.SemiBold, FontStyle.Normal),
    Font(Res.font.Geist_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(Res.font.Geist_Bold, FontWeight.Bold, FontStyle.Normal),
    Font(Res.font.Geist_BoldItalic, FontWeight.Bold, FontStyle.Italic),
    Font(Res.font.Geist_ExtraBold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(Res.font.Geist_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(Res.font.Geist_Black, FontWeight.Black, FontStyle.Normal),
    Font(Res.font.Geist_BlackItalic, FontWeight.Black, FontStyle.Italic),
)




@Composable
fun jetBrainsMonoFontFamily() = FontFamily(
    Font(Res.font.JetBrainsMono_Thin, FontWeight.Thin, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_ThinItalic, FontWeight.Thin, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_ExtraLight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_ExtraLightItalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_Light, FontWeight.Light, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_LightItalic, FontWeight.Light, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_Regular, FontWeight.Normal, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_Italic, FontWeight.Normal, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_Medium, FontWeight.Medium, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_MediumItalic, FontWeight.Medium, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_SemiBold, FontWeight.SemiBold, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_SemiBoldItalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_Bold, FontWeight.Bold, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_BoldItalic, FontWeight.Bold, FontStyle.Italic),
    Font(Res.font.JetBrainsMono_ExtraBold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(Res.font.JetBrainsMono_ExtraBoldItalic, FontWeight.ExtraBold, FontStyle.Italic),
)


@Composable
fun appTypography(): Typography {
    val geistFamily = geistFontFamily()
    val jetbrainsMono = jetBrainsMonoFontFamily()
    val defaultTypography = Typography()
    return Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = jetbrainsMono),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = jetbrainsMono),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = jetbrainsMono),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = jetbrainsMono),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = jetbrainsMono),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = jetbrainsMono),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = geistFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = geistFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = geistFamily),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = geistFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = geistFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = geistFamily),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = geistFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = geistFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = geistFamily)
    )
}