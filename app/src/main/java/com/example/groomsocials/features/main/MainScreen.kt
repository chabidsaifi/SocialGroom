package com.example.groomsocials.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.groomsocials.core.design.DesignTokens
import com.example.groomsocials.core.design.Typography
import com.example.groomsocials.core.design.ComponentSizes
import com.example.groomsocials.core.design.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle create action */ },
                containerColor = DesignTokens.Primary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> HomeTab()
                1 -> ExploreTab()
                2 -> CreateTab()
                3 -> MessagesTab()
                4 -> ProfileTab()
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        BottomNavItem("Home", Icons.Default.Home, 0),
        BottomNavItem("Explore", Icons.Default.Search, 1),
        BottomNavItem("Create", Icons.Default.Add, 2),
        BottomNavItem("Messages", Icons.Default.Chat, 3),
        BottomNavItem("Profile", Icons.Default.Person, 4)
    )
    
    NavigationBar(
        containerColor = Color.White,
        contentColor = DesignTokens.TextPrimary
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab.index,
                onClick = { onTabSelected(tab.index) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                        tint = if (selectedTab == tab.index) DesignTokens.Primary else DesignTokens.TextTertiary
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        style = Typography.LabelSmall.copy(
                            color = if (selectedTab == tab.index) DesignTokens.Primary else DesignTokens.TextTertiary
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun HomeTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        HomeTopBar()
        
        // Stories Section
        StoriesSection()
        
        // Feed
        FeedSection()
    }
}

@Composable
private fun HomeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo
        Text(
            text = "GroomSocials",
            style = Typography.HeadlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = DesignTokens.Primary
            )
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Notifications
        IconButton(onClick = { /* Handle notifications */ }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = DesignTokens.TextPrimary
            )
        }
    }
}

@Composable
private fun StoriesSection() {
    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        // Stories Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Stories",
                style = Typography.TitleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = DesignTokens.TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            TextButton(onClick = { /* Handle view all stories */ }) {
                Text(
                    text = "View All",
                    style = Typography.LabelMedium.copy(
                        color = DesignTokens.Primary
                    )
                )
            }
        }
        
        // Stories Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Add Story Button
            AddStoryButton()
            
            // Story Items
            repeat(5) { index ->
                StoryItem(
                    username = "User ${index + 1}",
                    isViewed = index % 3 == 0
                )
            }
        }
    }
}

@Composable
private fun AddStoryButton() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(ComponentSizes.StoryRingMedium)
                .background(
                    color = DesignTokens.Neutral200,
                    shape = RoundedCornerShape(ComponentSizes.StoryRingMedium / 2)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add story",
                tint = DesignTokens.TextSecondary,
                modifier = Modifier.size(24.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "Your Story",
            style = Typography.LabelSmall.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun StoryItem(
    username: String,
    isViewed: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(ComponentSizes.StoryRingMedium)
                .background(
                    color = if (isViewed) DesignTokens.Neutral300 else DesignTokens.Primary,
                    shape = RoundedCornerShape(ComponentSizes.StoryRingMedium / 2)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = username.first().toString(),
                style = Typography.TitleMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = username,
            style = Typography.LabelSmall.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun FeedSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Feed Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Feed",
                style = Typography.TitleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = DesignTokens.TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            IconButton(onClick = { /* Handle filter */ }) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filter",
                    tint = DesignTokens.TextSecondary
                )
            }
        }
        
        // Feed Posts
        repeat(3) { index ->
            FeedPost(
                username = "User ${index + 1}",
                timeAgo = "${index + 1}h ago",
                content = "This is a sample post content. It can contain text, images, or videos.",
                likes = (index + 1) * 42,
                comments = (index + 1) * 8
            )
            
            if (index < 2) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun FeedPost(
    username: String,
    timeAgo: String,
    content: String,
    likes: Int,
    comments: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Post Header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(ComponentSizes.AvatarMedium)
                        .background(
                            color = DesignTokens.Primary,
                            shape = RoundedCornerShape(ComponentSizes.AvatarMedium / 2)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = username.first().toString(),
                        style = Typography.LabelLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = username,
                        style = Typography.LabelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = DesignTokens.TextPrimary
                        )
                    )
                    Text(
                        text = timeAgo,
                        style = Typography.LabelSmall.copy(
                            color = DesignTokens.TextTertiary
                        )
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                IconButton(onClick = { /* Handle more options */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = DesignTokens.TextSecondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Post Content
            Text(
                text = content,
                style = Typography.BodyMedium.copy(
                    color = DesignTokens.TextPrimary
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Post Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Like Button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Like",
                            tint = DesignTokens.Error,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = likes.toString(),
                            style = Typography.LabelMedium.copy(
                                color = DesignTokens.TextSecondary
                            )
                        )
                    }
                    
                    // Comment Button
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Comment,
                            contentDescription = "Comment",
                            tint = DesignTokens.TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = comments.toString(),
                            style = Typography.LabelMedium.copy(
                                color = DesignTokens.TextSecondary
                            )
                        )
                    }
                }
                
                // Share Button
                IconButton(onClick = { /* Handle share */ }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = DesignTokens.TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun ExploreTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Explore Tab",
            style = Typography.HeadlineMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun CreateTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Create Tab",
            style = Typography.HeadlineMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun MessagesTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Messages Tab",
            style = Typography.HeadlineMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

@Composable
private fun ProfileTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile Tab",
            style = Typography.HeadlineMedium.copy(
                color = DesignTokens.TextSecondary
            )
        )
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val index: Int
)

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

