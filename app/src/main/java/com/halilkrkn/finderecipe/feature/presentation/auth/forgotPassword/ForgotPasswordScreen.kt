package com.halilkrkn.finderecipe.feature.presentation.auth.forgotPassword

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.feature.navigation.routes.AuthRoutes
import com.halilkrkn.finderecipe.feature.presentation.auth.components.EmailTextField
import com.halilkrkn.finderecipe.feature.presentation.auth.components.GradientButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPassswordViewModel = hiltViewModel(),
) {

    var email by rememberSaveable { mutableStateOf("") }
    val state = viewModel.forgotPasswordState.value
    val sendForgotEmail = viewModel.sendForgotEmail.value


    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {


        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_forgot),
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
                    text = "Reset Password",
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

                Spacer(modifier = Modifier.padding(10.dp))
                GradientButton(
                    nameButton = "Submit",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                    onClick = {
                        if (email.isEmpty()) {
                            Toast.makeText(
                                navController.context,
                                "Please enter your email",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            // ForgotPassword İşlemi
                            keyboard?.hide()
                            scope.launch {
                                viewModel.forgotPasswordWithEmail(email)
                            }
                        }
                        navController.navigate(AuthRoutes.SignIn.route)

                    },
                    state = state.isLoading
                )

                if (sendForgotEmail) {
                    scope.launch {
                        delay(1000L)
                        navController.navigate(AuthRoutes.SignIn.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }

                        Toast.makeText(context, "Has Been Send Forgot Password Email", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
                TextButton(onClick = {
                    navController.navigate(AuthRoutes.SignUp.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }) {
                    Text(
                        modifier = Modifier,
                        text = "Sign Up?",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}
