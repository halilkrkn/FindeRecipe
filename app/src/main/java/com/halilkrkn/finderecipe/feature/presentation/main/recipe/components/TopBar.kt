package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.ui.theme.OxfordBlue

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(RoundedCornerShape(12.dp)),
                model = "https://avatars.githubusercontent.com/u/42476890?v=4",
                contentDescription = "profile_image",
                placeholder = painterResource(id = R.drawable.baseline_person_24),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Hi, Halil 👋",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
            )
        }

        BadgedBox(
            modifier = Modifier.padding(end = 8.dp),
            badge = {
                Badge {
                    Text(
                        text = "3",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.NotificationsNone,
                contentDescription = "Notifications",
                tint = OxfordBlue
            )
        }
    }
}