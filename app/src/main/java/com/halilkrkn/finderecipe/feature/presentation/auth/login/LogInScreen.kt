package com.halilkrkn.finderecipe.feature.presentation.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.navigation.routes.AuthRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.MAIN
import com.halilkrkn.finderecipe.feature.presentation.auth.components.EmailTextField
import com.halilkrkn.finderecipe.feature.presentation.auth.components.GoogleButton
import com.halilkrkn.finderecipe.feature.presentation.auth.components.GradientButton
import com.halilkrkn.finderecipe.feature.presentation.auth.components.PasswordTextField
import com.halilkrkn.finderecipe.feature.presentation.components.LoadingProgressBar
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.Razzmatazz

@Composable
fun LogInScreen(
    navController: NavController,
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val context = remember { navController.context }

    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingProgressBar(raw = R.raw.loading)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .blur(if (isLoading) 10.dp else 0.dp)
            .fillMaxHeight()
            .background(
                color = FloralWhite,
            )
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center),
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_sign_in),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                EmailTextField(
                    email = email,
                    onValueChange = { email = it }
                )

                Spacer(modifier = Modifier.padding(3.dp))
                PasswordTextField(
                    password = password,
                    onValueChange = { password = it }
                )

                val gradientColor = listOf(Color(0xFF484BF1), Color(0xFF673AB7))
                val cornerRadius = 16.dp



                Spacer(modifier = Modifier.height(16.dp))
                GradientButton(
                    gradientColors = gradientColor,
                    cornerRadius = cornerRadius,
                    nameButton = "Login",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(MAIN) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                        } else {
                            isLoading = true
                            isError = false
                            errorMessage = ""
                            // LoginViewModel ile giriş işlemini başlat
                        }
                    }
                )


                if (isError) {
                    Text(text = errorMessage, color = Color.Red)
//                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    TextButton(onClick = {
                        navController.navigate(AuthRoutes.SignUp.route)
                        {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }) {
                        Text(
                            text = "Create An Account",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        navController.navigate(AuthRoutes.ForgotPassword.route)
                    }) {
                        Text(
                            text = "Forgot Password",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "or connect with", fontWeight = FontWeight.Medium, color = Color.Gray)
                Spacer(modifier = Modifier.padding(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    GoogleButton(
                        onClick = {
                            Toast.makeText(context, "Google Clicked", Toast.LENGTH_SHORT).show()
                        },
                        text = "Sign In with Google",
                        loadingText = "Signing Up...",
                        iconContentDescription = "Google Icon",
                        buttonColors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.White
                        ),
                        clickProgressBarColor = Razzmatazz,
                        icon = painterResource(id = R.drawable.ic_google),
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}
