package com.example.groomsocials.features.auth

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.groomsocials.core.design.DesignTokens
import com.example.groomsocials.core.design.Typography
import com.example.groomsocials.core.design.ComponentSizes
import com.example.groomsocials.core.design.Spacing

@Composable
fun AuthScreen(
    onNavigateToProfileSetup: () -> Unit
) {
    var isLogin by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }
    
    AuthContent(
        isLogin = isLogin,
        isLoading = isLoading,
        onToggleMode = { isLogin = !isLogin },
        onAuthSuccess = {
            isLoading = true
            // Simulate auth process
            onNavigateToProfileSetup()
        }
    )
}

@Composable
private fun AuthContent(
    isLogin: Boolean,
    isLoading: Boolean,
    onToggleMode: () -> Unit,
    onAuthSuccess: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            
            // Logo and Welcome
            WelcomeSection(
                isLogin = isLogin,
                modifier = Modifier.padding(bottom = 48.dp)
            )
            
            // Auth Form
            AnimatedContent(
                targetState = isLogin,
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { if (isLogin) -it else it },
                        animationSpec = tween(300)
                    ) + fadeIn(animationSpec = tween(300)) togetherWith
                    slideOutHorizontally(
                        targetOffsetX = { if (isLogin) it else -it },
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
                },
                label = "auth_form"
            ) { loginMode ->
                if (loginMode) {
                    LoginForm(
                        isLoading = isLoading,
                        onLogin = onAuthSuccess,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    SignupForm(
                        isLoading = isLoading,
                        onSignup = onAuthSuccess,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Social Login
            SocialLoginSection(
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Toggle Mode
            ToggleAuthMode(
                isLogin = isLogin,
                onToggle = onToggleMode,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    }
}

@Composable
private fun WelcomeSection(
    isLogin: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = DesignTokens.Primary,
                    shape = RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "GS",
                style = Typography.HeadlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Title
        Text(
            text = if (isLogin) "Welcome Back!" else "Create Account",
            style = Typography.HeadlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.TextPrimary
            ),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Subtitle
        Text(
            text = if (isLogin) "Sign in to continue" else "Join the community",
            style = Typography.BodyLarge.copy(
                color = DesignTokens.TextSecondary
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun LoginForm(
    isLoading: Boolean,
    onLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Forgot Password
        Text(
            text = "Forgot Password?",
            style = Typography.LabelMedium.copy(
                color = DesignTokens.Primary
            ),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* Handle forgot password */ }
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Login Button
        Button(
            onClick = onLogin,
            enabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = "Sign In",
                    style = Typography.LabelLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Composable
private fun SignupForm(
    isLoading: Boolean,
    onSignup: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var acceptTerms by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
        
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showPassword) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { showConfirmPassword = !showConfirmPassword }) {
                    Icon(
                        imageVector = if (showConfirmPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (showConfirmPassword) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DesignTokens.Primary,
                unfocusedBorderColor = DesignTokens.Neutral300
            )
        )
        
        // Terms and Conditions
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = { acceptTerms = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = DesignTokens.Primary
                )
            )
            Text(
                text = "I agree to the Terms of Service and Privacy Policy",
                style = Typography.BodySmall.copy(
                    color = DesignTokens.TextSecondary
                ),
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Signup Button
        Button(
            onClick = onSignup,
            enabled = name.isNotEmpty() && email.isNotEmpty() && 
                    password.isNotEmpty() && confirmPassword.isNotEmpty() && 
                    acceptTerms && !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(ComponentSizes.ButtonLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = DesignTokens.Primary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = "Create Account",
                    style = Typography.LabelLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

@Composable
private fun SocialLoginSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(DesignTokens.Neutral300)
            )
            Text(
                text = "or continue with",
                style = Typography.BodySmall.copy(
                    color = DesignTokens.TextTertiary
                )
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(DesignTokens.Neutral300)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Social Login Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SocialLoginButton(
                text = "Google",
                onClick = { /* Handle Google login */ },
                modifier = Modifier.weight(1f)
            )
            SocialLoginButton(
                text = "Apple",
                onClick = { /* Handle Apple login */ },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SocialLoginButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(ComponentSizes.ButtonMedium),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = DesignTokens.TextPrimary
        )
    ) {
        Text(
            text = text,
            style = Typography.LabelMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun ToggleAuthMode(
    isLogin: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isLogin) "Don't have an account? " else "Already have an account? ",
            style = Typography.BodyMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
        Text(
            text = if (isLogin) "Sign Up" else "Sign In",
            style = Typography.BodyMedium.copy(
                color = DesignTokens.Primary,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.clickable { onToggle() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthContent(
        isLogin = true,
        isLoading = false,
        onToggleMode = {},
        onAuthSuccess = {}
    )
}

