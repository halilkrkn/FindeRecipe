package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.navigation.routes.DetailsRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.AUTHENTICATION
import com.halilkrkn.finderecipe.ui.theme.OxfordBlue

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    var isProfilePopupVisible by remember { mutableStateOf(false) }
    val firebaseAuth: FirebaseAuth = Firebase.auth
    val firebaseUser = firebaseAuth.currentUser

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
            if (firebaseUser?.photoUrl != null) {
                AsyncImage(
                    modifier = Modifier
                        .clickable {
                            isProfilePopupVisible = !isProfilePopupVisible
                        }
                        .height(60.dp)
                        .width(60.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    model = firebaseUser.photoUrl,
                    contentDescription = "profile_image",
                    placeholder = painterResource(id = R.drawable.baseline_person_24)
                )
            } else {
                Icon(
                    modifier = Modifier
                        .clickable {
                            isProfilePopupVisible = !isProfilePopupVisible
                        }
                        .size(60.dp),
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Profile Picture",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Hi, ${if (firebaseUser?.displayName.isNullOrEmpty()) firebaseUser?.email?.substringBefore("@") else firebaseUser?.displayName} 👋",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isProfilePopupVisible) {
            SignOutPopup(
                firebaseUser = firebaseUser,
                onSignOut = {
                    navController.popBackStack()
                    firebaseAuth.signOut()
                    navController.navigate(AUTHENTICATION)
                }
            )
        }

        BadgedBox(
            modifier = Modifier
                .clickable {
                    navController.navigate(DetailsRoutes.Notification.route)
                }
                .padding(end = 8.dp),
            badge = {
                Badge {
                        Text(
                            text = "1",
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