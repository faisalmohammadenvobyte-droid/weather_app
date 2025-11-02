package com.openkeyboard.myapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.openkeyboard.myapplication.R

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xB2098AEE),
                        Color(0xBA9FCBEA)
                    )
                )
            )
    ) {
        // Circle image in the middle area
        Image(
            painter = painterResource(id = R.drawable.img_circle),
            contentDescription = "Cloud image",
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.TopEnd)
                .offset(x = (50).dp),
            contentScale = ContentScale.Fit
        )

        // Sun image in the middle area
        Image(
            painter = painterResource(id = R.drawable.img_sun),
            contentDescription = "Cloud image",
            modifier = Modifier
                .padding(top = 48.dp)
                .fillMaxWidth()
                .height(280.dp)
                .align(Alignment.TopStart)
                .offset(x = (-250).dp),
            contentScale = ContentScale.Fit
        )
        // Cloud image in the middle area
        Image(
            painter = painterResource(id = R.drawable.cloud_image),
            contentDescription = "Cloud image",
            modifier = Modifier
                .padding(bottom = 200.dp)
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 150.dp), // adjust as needed
            contentScale = ContentScale.Fit
        )

        // Texts over the cloud
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.onboard_heading),
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.onboard_sub_heading),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.DarkGray,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start
                )
            )
        }

        // Button fixed at bottom
        Button(
            onClick = {
            navController.navigate("home")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0C5285)
            )
        ) {
            Text(
                text = "Get Started",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
            )
        }
    }
}