package com.example.groomsocials.features.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.groomsocials.core.design.DesignTokens
import com.example.groomsocials.core.design.Typography
import com.example.groomsocials.core.design.ComponentSizes
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen(
    onNavigateToAuth: () -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }
    val pageOffset = remember { Animatable(0f) }
    
    val onboardingPages = listOf(
        OnboardingPage(
            title = "Welcome to GroomSocials",
            description = "Your all-in-one social platform for connecting, sharing, and discovering amazing content.",
            icon = "🎉"
        ),
        OnboardingPage(
            title = "Connect & Share",
            description = "Share your stories, create amazing content, and connect with friends and communities.",
            icon = "📱"
        ),
        OnboardingPage(
            title = "Discover Everything",
            description = "Explore posts, find events, order food, shop locally, and discover new experiences.",
            icon = "🌟"
        ),
        OnboardingPage(
            title = "Privacy First",
            description = "Your privacy matters. We give you complete control over your data and content.",
            icon = "🔒"
        )
    )
    
    LaunchedEffect(currentPage) {
        pageOffset.animateTo(
            targetValue = currentPage.toFloat(),
            animationSpec = tween(300)
        )
    }
    
    OnboardingContent(
        pages = onboardingPages,
        currentPage = currentPage,
        onPageChange = { currentPage = it },
        onGetStarted = onNavigateToAuth
    )
}

@Composable
private fun OnboardingContent(
    pages: List<OnboardingPage>,
    currentPage: Int,
    onPageChange: (Int) -> Unit,
    onGetStarted: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Page Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            
            // Page Indicator
            PageIndicator(
                totalPages = pages.size,
                currentPage = currentPage,
                modifier = Modifier.padding(bottom = 48.dp)
            )
            
            // Page Content
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                OnboardingPageContent(
                    page = pages[currentPage],
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Navigation Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Skip Button
                if (currentPage < pages.size - 1) {
                    TextButton(
                        onClick = onGetStarted,
                        modifier = Modifier.alpha(0.7f)
                    ) {
                        Text(
                            text = "Skip",
                            style = Typography.LabelLarge.copy(
                                color = DesignTokens.TextSecondary
                            )
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(80.dp))
                }
                
                // Next/Get Started Button
                Button(
                    onClick = {
                        if (currentPage < pages.size - 1) {
                            onPageChange(currentPage + 1)
                        } else {
                            onGetStarted()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DesignTokens.Primary
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.height(ComponentSizes.ButtonLarge)
                ) {
                    Text(
                        text = if (currentPage < pages.size - 1) "Next" else "Get Started",
                        style = Typography.LabelLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icon/Emoji
        Text(
            text = page.icon,
            style = Typography.DisplayLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        // Title
        Text(
            text = page.title,
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Description
        Text(
            text = page.description,
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary,
                lineHeight = 24.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PageIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val isActive = index == currentPage
            val width = if (isActive) 24.dp else 8.dp
            
            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(width)
                    .background(
                        color = if (isActive) DesignTokens.Primary else DesignTokens.Neutral300,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}

data class OnboardingPage(
    val title: String,
    val description: String,
    val icon: String
)

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingContent(
        pages = listOf(
            OnboardingPage(
                title = "Welcome to GroomSocials",
                description = "Your all-in-one social platform for connecting, sharing, and discovering amazing content.",
                icon = "🎉"
            )
        ),
        currentPage = 0,
        onPageChange = {},
        onGetStarted = {}
    )
}

