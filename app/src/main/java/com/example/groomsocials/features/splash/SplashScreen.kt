package com.example.groomsocials.features.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groomsocials.R
import com.example.groomsocials.core.design.DesignTokens
import com.example.groomsocials.core.design.Typography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var isAuthenticated by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    
    // Animation states
    val logoScale = remember { Animatable(0f) }
    val logoAlpha = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }
    
    // Start animations
    LaunchedEffect(Unit) {
        // Logo animation
        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(800, delayMillis = 200)
        )
        logoAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(600, delayMillis = 200)
        )
        
        // Text animation
        delay(400)
        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
        
        // Check authentication status
        delay(800)
        isAuthenticated = checkAuthenticationStatus()
        isLoading = false
        
        // Navigate based on auth status
        delay(400)
        if (isAuthenticated) {
            onNavigateToMain()
        } else {
            onNavigateToOnboarding()
        }
    }
    
    SplashContent(
        logoScale = logoScale.value,
        logoAlpha = logoAlpha.value,
        textAlpha = textAlpha.value,
        isLoading = isLoading
    )
}

@Composable
private fun SplashContent(
    logoScale: Float,
    logoAlpha: Float,
    textAlpha: Float,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DesignTokens.Primary,
                        DesignTokens.PrimaryVariant
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(logoScale)
                    .alpha(logoAlpha)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // App Logo - Replace with actual logo
                Text(
                    text = "GS",
                    style = Typography.DisplayLarge.copy(
                        color = DesignTokens.Primary,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // App Name
            Text(
                text = "GroomSocials",
                style = Typography.HeadlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.alpha(textAlpha)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Tagline
            Text(
                text = "Your Social Super App",
                style = Typography.BodyLarge.copy(
                    color = Color.White.copy(alpha = 0.9f)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(textAlpha)
            )
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Loading indicator
            if (isLoading) {
                LoadingIndicator()
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    // Simple loading dots animation
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { index ->
            val animatedAlpha by remember {
                Animatable(0.3f)
            }.also { animatable ->
                LaunchedEffect(index) {
                    while (true) {
                        animatable.animateTo(1f, tween(600))
                        animatable.animateTo(0.3f, tween(600))
                        delay(200L * index)
                    }
                }
            }
            
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = Color.White.copy(alpha = animatedAlpha),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

private fun checkAuthenticationStatus(): Boolean {
    // TODO: Implement actual authentication check
    // This should check for stored tokens, user session, etc.
    return false
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashContent(
        logoScale = 1f,
        logoAlpha = 1f,
        textAlpha = 1f,
        isLoading = false
    )
}

