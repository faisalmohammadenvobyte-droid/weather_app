package com.openkeyboard.myapplication.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.openkeyboard.myapplication.R
import com.openkeyboard.myapplication.presentation.WeatherViewModel
import com.openkeyboard.myapplication.utils.Constants.API_KEY

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
){
    val uiState by weatherViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        weatherViewModel.fetchWeather(lat = 44.34, lon = 10.99, apiKey = API_KEY)
    }

    Column() {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Settings", color = Color(0xFF31507F))
            },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = "Go back",
                        tint = Color(0xFF31507F)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface
            )
        )

        UserProfileRow(
            userName = "Example User",
            userEmail = "mock.email@gmail.com"
        ) {

        }

        AppSettingsListWithSwitches()

        val uiState by weatherViewModel.uiState.collectAsState()

        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.weather != null -> Text("Temp: ${uiState.weather?.temperature}")
            uiState.error != null -> Text("Error: ${uiState.error}")
        }
    }

}

@Composable
fun UserProfileRow(
    userName: String,
    userEmail: String,
    onUserProfileClicked: () -> Unit
){
    Surface(
        onClick = onUserProfileClicked,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "User Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = userEmail,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    contentDescription = "Navigate to profile details",
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
            }
        }

    }
}

@Composable
fun AppSettingsListWithSwitches() {
    var isNotificationsEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(true) }

    Column {
        SettingsRow(
            title = "Location",
            subtitle = "alex, egypt",
            endContentIcon = painterResource(R.drawable.ic_arrow_forward),
            onClick = {  }
        )
        SettingsRow(
            title = "Notifications",
            switchState = isNotificationsEnabled,
            onSwitchToggle = { isNotificationsEnabled = it }
        )
        SettingsRow(
            title = "Language",
            subtitle = "EN",
            endContentIcon = painterResource(R.drawable.ic_arrow_forward),
            onClick = {  }
        )
        SettingsRow(
            title = "Temperature",
            subtitle = "c",
            endContentIcon = painterResource(R.drawable.ic_arrow_forward),
            onClick = {  }
        )

        SettingsRow(
            title = "Dark mode",
            switchState = isDarkModeEnabled,
            onSwitchToggle = { isDarkModeEnabled = it }
        )

        SettingsRow(
            title = "Help",
            endContentIcon = painterResource(R.drawable.ic_arrow_forward),
            onClick = {  }
        )

        SettingsRow(
            title = "Log Out",
            endContentIcon = painterResource(R.drawable.ic_log_out),
            onClick = { }
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Version 8.2.12",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}

@Composable
fun SettingsRow(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null, // Made nullable for switch/toggle rows
    subtitle: String? = null,
    endContentIcon: Painter? = null,
    switchState: Boolean? = null,
    onSwitchToggle: ((Boolean) -> Unit)? = null
) {
    // Determine the click action: If a switch is present, the row click acts as the switch toggle.
    val rowClick: (() -> Unit)? = when {
        onSwitchToggle != null && switchState != null -> {
            // If a switch exists, clicking the row toggles the switch
            { onSwitchToggle(!switchState) }
        }
        onClick != null -> {
            // Otherwise, use the standard onClick action
            onClick
        }
        else -> null
    }

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .then(if (rowClick != null) Modifier.clickable(onClick = rowClick) else Modifier)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 1. Title Text (Left)
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f) // Push content to the right
            )

            // 2. End Content
            when {
                // Option A: Display a Switch
                switchState != null && onSwitchToggle != null -> {
                    Switch(
                        checked = switchState,
                        onCheckedChange = onSwitchToggle,
                        modifier = Modifier.size(48.dp) // Standard switch size
                    )
                }

                // Option B: Display a Subtitle
                subtitle != null -> {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    // If a subtitle is present, an icon can follow it
                    if (endContentIcon != null) {
                        Icon(
                            painter = endContentIcon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.LightGray
                        )
                    }
                }

                // Option C: Display a Navigation/Logout Icon only
                endContentIcon != null -> {
                    Icon(
                        painter = endContentIcon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.LightGray
                    )
                }
            }
        }
    }
}