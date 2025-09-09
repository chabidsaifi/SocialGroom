package com.example.groomsocials.features.profile.setup

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.groomsocials.core.design.DesignTokens
import com.example.groomsocials.core.design.Typography
import com.example.groomsocials.core.design.ComponentSizes
import com.example.groomsocials.core.design.Spacing

@Composable
fun ProfileSetupScreen(
    onNavigateToMain: () -> Unit
) {
    var currentStep by remember { mutableStateOf(0) }
    var isLoading by remember { mutableStateOf(false) }
    
    val setupSteps = listOf(
        SetupStep.ProfileInfo,
        SetupStep.Interests,
        SetupStep.Modules,
        SetupStep.Permissions
    )
    
    ProfileSetupContent(
        currentStep = currentStep,
        totalSteps = setupSteps.size,
        onNext = {
            if (currentStep < setupSteps.size - 1) {
                currentStep++
            } else {
                isLoading = true
                onNavigateToMain()
            }
        },
        onBack = {
            if (currentStep > 0) {
                currentStep--
            }
        },
        isLoading = isLoading
    )
}

@Composable
private fun ProfileSetupContent(
    currentStep: Int,
    totalSteps: Int,
    onNext: () -> Unit,
    onBack: () -> Unit,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Header
            SetupHeader(
                currentStep = currentStep,
                totalSteps = totalSteps,
                onBack = onBack,
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )
            
            // Step Content
            AnimatedContent(
                targetState = currentStep,
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300)) togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { -it },
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
                },
                label = "setup_step"
            ) { step ->
                when (step) {
                    0 -> ProfileInfoStep(
                        onNext = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                    1 -> InterestsStep(
                        onNext = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                    2 -> ModulesStep(
                        onNext = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                    3 -> PermissionsStep(
                        onNext = onNext,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun SetupHeader(
    currentStep: Int,
    totalSteps: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Progress Bar
        LinearProgressIndicator(
            progress = (currentStep + 1).toFloat() / totalSteps.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp)),
            color = DesignTokens.Primary,
            trackColor = DesignTokens.Neutral200
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Step Counter
        Text(
            text = "Step ${currentStep + 1} of $totalSteps",
            style = Typography.LabelMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun ProfileInfoStep(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var profileImage by remember { mutableStateOf<String?>(null) }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            text = "Let's set up your profile",
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            )
        )
        
        Text(
            text = "Tell us a bit about yourself",
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary
            )
        )
        
        // Profile Picture
        ProfilePictureSection(
            profileImage = profileImage,
            onImageSelected = { profileImage = it },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        
        // Name Field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Username Field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Bio Field
        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Bio (Optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            ),
            maxLines = 3
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Next Button
        Button(
            onClick = onNext,
            enabled = name.isNotEmpty() && username.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue",
                style = Typography.LabelLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun ProfilePictureSection(
    profileImage: String?,
    onImageSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(DesignTokens.Neutral100)
            .border(
                width = 2.dp,
                color = DesignTokens.Primary,
                shape = CircleShape
            )
            .clickable { onImageSelected("profile_image_uri") },
        contentAlignment = Alignment.Center
    ) {
        if (profileImage != null) {
            // Profile image would be displayed here
            Text(
                text = "📷",
                style = Typography.DisplayMedium
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Add profile picture",
                    tint = DesignTokens.Primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Add Photo",
                    style = Typography.LabelMedium.copy(
                        color = DesignTokens.Primary
                    )
                )
            }
        }
    }
}

@Composable
private fun InterestsStep(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedInterests by remember { mutableStateOf(setOf<String>()) }
    
    val interests = listOf(
        "Technology", "Music", "Sports", "Travel", "Food", "Fashion",
        "Art", "Photography", "Gaming", "Fitness", "Books", "Movies",
        "Cooking", "Dancing", "Writing", "Design", "Business", "Education"
    )
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            text = "What are you interested in?",
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            )
        )
        
        Text(
            text = "Select topics that interest you to personalize your feed",
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary
            )
        )
        
        // Interests Grid
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(interests) { interest ->
                InterestChip(
                    interest = interest,
                    isSelected = selectedInterests.contains(interest),
                    onClick = {
                        selectedInterests = if (selectedInterests.contains(interest)) {
                            selectedInterests - interest
                        } else {
                            selectedInterests + interest
                        }
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Next Button
        Button(
            onClick = onNext,
            enabled = selectedInterests.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue",
                style = Typography.LabelLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun InterestChip(
    interest: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) DesignTokens.Primary else DesignTokens.Neutral100,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = interest,
            style = Typography.LabelMedium.copy(
                color = if (isSelected) Color.White else DesignTokens.TextPrimary,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
            )
        )
    }
}

@Composable
private fun ModulesStep(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedModules by remember { mutableStateOf(setOf<String>()) }
    
    val modules = listOf(
        ModuleInfo("Social", "Connect and share with friends", "👥", true),
        ModuleInfo("Messages", "Chat with friends and groups", "💬", true),
        ModuleInfo("Marketplace", "Buy and sell locally", "🛒", false),
        ModuleInfo("Food", "Order food from restaurants", "🍕", false),
        ModuleInfo("Events", "Discover and create events", "🎉", false),
        ModuleInfo("Communities", "Join interest-based groups", "🏘️", false)
    )
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            text = "Choose your modules",
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            )
        )
        
        Text(
            text = "Select the features you want to use. You can change this later.",
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary
            )
        )
        
        // Modules List
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            modules.forEach { module ->
                ModuleCard(
                    module = module,
                    isSelected = selectedModules.contains(module.id) || module.isRequired,
                    isEnabled = !module.isRequired,
                    onToggle = {
                        if (!module.isRequired) {
                            selectedModules = if (selectedModules.contains(module.id)) {
                                selectedModules - module.id
                            } else {
                                selectedModules + module.id
                            }
                        }
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Next Button
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Continue",
                style = Typography.LabelLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun ModuleCard(
    module: ModuleInfo,
    isSelected: Boolean,
    isEnabled: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = isEnabled) { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) DesignTokens.Primary.copy(alpha = 0.1f) else Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = module.emoji,
                style = Typography.HeadlineMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = module.name,
                    style = Typography.TitleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) DesignTokens.Primary else DesignTokens.TextPrimary
                    )
                )
                Text(
                    text = module.description,
                    style = Typography.BodyMedium.copy(
                        color = DesignTokens.TextSecondary
                    )
                )
            }
            
            if (module.isRequired) {
                Text(
                    text = "Required",
                    style = Typography.LabelSmall.copy(
                        color = DesignTokens.TextTertiary
                    )
                )
            } else {
                Switch(
                    checked = isSelected,
                    onCheckedChange = { onToggle() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = DesignTokens.Primary
                    )
                )
            }
        }
    }
}

@Composable
private fun PermissionsStep(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var permissionsGranted by remember { mutableStateOf(setOf<String>()) }
    
    val permissions = listOf(
        PermissionInfo("Camera", "Take photos and videos", "📷", "camera"),
        PermissionInfo("Microphone", "Record audio and video calls", "🎤", "microphone"),
        PermissionInfo("Location", "Find nearby events and places", "📍", "location"),
        PermissionInfo("Notifications", "Get updates and messages", "🔔", "notifications")
    )
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Title
        Text(
            text = "Enable permissions",
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            )
        )
        
        Text(
            text = "Allow access to these features for the best experience",
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary
            )
        )
        
        // Permissions List
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            permissions.forEach { permission ->
                PermissionCard(
                    permission = permission,
                    isGranted = permissionsGranted.contains(permission.id),
                    onGrant = {
                        permissionsGranted = permissionsGranted + permission.id
                    }
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Next Button
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Get Started",
                style = Typography.LabelLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun PermissionCard(
    permission: PermissionInfo,
    isGranted: Boolean,
    onGrant: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isGranted) DesignTokens.Success.copy(alpha = 0.1f) else Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = permission.emoji,
                style = Typography.HeadlineMedium,
                modifier = Modifier.padding(end = 16.dp)
            )
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = permission.name,
                    style = Typography.TitleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (isGranted) DesignTokens.Success else DesignTokens.TextPrimary
                    )
                )
                Text(
                    text = permission.description,
                    style = Typography.BodyMedium.copy(
                        color = DesignTokens.TextSecondary
                    )
                )
            }
            
            if (isGranted) {
                Text(
                    text = "✓",
                    style = Typography.HeadlineSmall.copy(
                        color = DesignTokens.Success
                    )
                )
            } else {
                Button(
                    onClick = onGrant,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DesignTokens.Primary
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Allow",
                        style = Typography.LabelMedium.copy(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

data class ModuleInfo(
    val name: String,
    val description: String,
    val emoji: String,
    val isRequired: Boolean
) {
    val id: String = name.lowercase()
}

data class PermissionInfo(
    val name: String,
    val description: String,
    val emoji: String,
    val id: String
)

enum class SetupStep {
    ProfileInfo, Interests, Modules, Permissions
}

@Preview(showBackground = true)
@Composable
fun ProfileSetupScreenPreview() {
    ProfileSetupContent(
        currentStep = 0,
        totalSteps = 4,
        onNext = {},
        onBack = {},
        isLoading = false
    )
}

