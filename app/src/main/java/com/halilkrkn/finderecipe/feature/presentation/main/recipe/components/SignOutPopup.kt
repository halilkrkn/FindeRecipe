package com.halilkrkn.finderecipe.feature.presentation.main.recipe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser
import com.halilkrkn.finderecipe.ui.theme.FloralWhite

@Composable
fun SignOutPopup(
    firebaseUser: FirebaseUser?,
    onSignOut: () -> Unit,
) {
    var showPopup by remember { mutableStateOf(true) }

    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(8, 190),
        properties = PopupProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        onDismissRequest = {
            showPopup = false
        }
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .width(250.dp)
                .clickable {
                    showPopup = false
                }
                .background(
                    color = FloralWhite
                )
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.DarkGray,
                    ),
                    shape = RoundedCornerShape(36.dp)
                )
                .clip(RoundedCornerShape(36.dp))
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SignOut(
                firebaseUser = firebaseUser,
                onSignOut = onSignOut
            )
        }
    }
}

@Composable
fun SignOut(
    firebaseUser: FirebaseUser?,
    onSignOut: () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (firebaseUser?.photoUrl != null) {
            AsyncImage(
                model = firebaseUser.photoUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color.DarkGray,
                        ),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (firebaseUser?.displayName != null) {
            Text(
                text = firebaseUser.displayName!!,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (firebaseUser?.email != null) {
            Text(
                text = firebaseUser.email!!,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Button(
            onClick = {
                onSignOut()
            }
        ) {
            Text(text = "Çıkış Yap")
        }
    }
}
