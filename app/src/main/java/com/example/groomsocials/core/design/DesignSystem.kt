package com.example.groomsocials.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Design System Tokens
object DesignTokens {
    
    // Colors - Primary Palette
    val Primary = Color(0xFF6366F1) // Indigo
    val PrimaryVariant = Color(0xFF4F46E5)
    val PrimaryLight = Color(0xFF818CF8)
    val PrimaryDark = Color(0xFF4338CA)
    
    // Secondary Palette
    val Secondary = Color(0xFFEC4899) // Pink
    val SecondaryVariant = Color(0xFFDB2777)
    val SecondaryLight = Color(0xFFF472B6)
    val SecondaryDark = Color(0xFFBE185D)
    
    // Neutral Palette
    val Neutral50 = Color(0xFFFAFAFA)
    val Neutral100 = Color(0xFFF5F5F5)
    val Neutral200 = Color(0xFFE5E5E5)
    val Neutral300 = Color(0xFFD4D4D4)
    val Neutral400 = Color(0xFFA3A3A3)
    val Neutral500 = Color(0xFF737373)
    val Neutral600 = Color(0xFF525252)
    val Neutral700 = Color(0xFF404040)
    val Neutral800 = Color(0xFF262626)
    val Neutral900 = Color(0xFF171717)
    
    // Semantic Colors
    val Success = Color(0xFF10B981) // Green
    val Warning = Color(0xFFF59E0B) // Amber
    val Error = Color(0xFFEF4444) // Red
    val Info = Color(0xFF3B82F6) // Blue
    
    // Background Colors
    val Background = Color(0xFFFFFFFF)
    val BackgroundDark = Color(0xFF0F0F0F)
    val Surface = Color(0xFFFAFAFA)
    val SurfaceDark = Color(0xFF1A1A1A)
    
    // Text Colors
    val TextPrimary = Color(0xFF171717)
    val TextSecondary = Color(0xFF525252)
    val TextTertiary = Color(0xFF737373)
    val TextDisabled = Color(0xFFA3A3A3)
    
    val TextPrimaryDark = Color(0xFFFAFAFA)
    val TextSecondaryDark = Color(0xFFD4D4D4)
    val TextTertiaryDark = Color(0xFFA3A3A3)
    val TextDisabledDark = Color(0xFF737373)
    
    // Social Media Colors
    val Instagram = Color(0xFFE4405F)
    val Facebook = Color(0xFF1877F2)
    val Twitter = Color(0xFF1DA1F2)
    val LinkedIn = Color(0xFF0A66C2)
    val YouTube = Color(0xFFFF0000)
    val TikTok = Color(0xFF000000)
}

// Typography Scale
object Typography {
    val FontFamily = FontFamily.Default
    
    // Display Styles
    val DisplayLarge = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    )
    
    val DisplayMedium = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    )
    
    val DisplaySmall = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    )
    
    // Headline Styles
    val HeadlineLarge = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    )
    
    val HeadlineMedium = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    )
    
    val HeadlineSmall = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    )
    
    // Title Styles
    val TitleLarge = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    )
    
    val TitleMedium = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    )
    
    val TitleSmall = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
    
    // Body Styles
    val BodyLarge = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    
    val BodyMedium = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )
    
    val BodySmall = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    )
    
    // Label Styles
    val LabelLarge = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    )
    
    val LabelMedium = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    
    val LabelSmall = TextStyle(
        fontFamily = FontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
}

// Spacing Scale
object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 32.dp
    val xxl = 48.dp
    val xxxl = 64.dp
}

// Elevation Scale
object Elevation {
    val none = 0.dp
    val xs = 1.dp
    val sm = 2.dp
    val md = 4.dp
    val lg = 8.dp
    val xl = 16.dp
    val xxl = 24.dp
}

// Border Radius Scale
object BorderRadius {
    val none = 0.dp
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val full = 999.dp
}

// Component Sizes
object ComponentSizes {
    // Button Heights
    val ButtonSmall = 32.dp
    val ButtonMedium = 40.dp
    val ButtonLarge = 48.dp
    val ButtonXLarge = 56.dp
    
    // Input Heights
    val InputSmall = 36.dp
    val InputMedium = 44.dp
    val InputLarge = 52.dp
    
    // Icon Sizes
    val IconSmall = 16.dp
    val IconMedium = 24.dp
    val IconLarge = 32.dp
    val IconXLarge = 48.dp
    
    // Avatar Sizes
    val AvatarSmall = 24.dp
    val AvatarMedium = 32.dp
    val AvatarLarge = 40.dp
    val AvatarXLarge = 56.dp
    val AvatarXXLarge = 80.dp
    
    // Story Ring Sizes
    val StoryRingSmall = 60.dp
    val StoryRingMedium = 80.dp
    val StoryRingLarge = 100.dp
    
    // FAB Sizes
    val FABSmall = 40.dp
    val FABMedium = 56.dp
    val FABLarge = 64.dp
}

// Local Composition for Design System
val LocalDesignSystem = staticCompositionLocalOf { DesignTokens }

@Composable
fun ProvideDesignSystem(content: @Composable () -> Unit) {
    androidx.compose.runtime.CompositionLocalProvider(
        LocalDesignSystem provides DesignTokens,
        content = content
    )
}

