package com.halilkrkn.finderecipe.feature.presentation.auth.signIn

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.halilkrkn.finderecipe.R
import com.halilkrkn.finderecipe.core.constant.Constants.GOOGLE_SECRET_KEY
import com.halilkrkn.finderecipe.feature.navigation.routes.AuthRoutes
import com.halilkrkn.finderecipe.feature.navigation.util.Graphs.MAIN
import com.halilkrkn.finderecipe.feature.presentation.auth.components.EmailTextField
import com.halilkrkn.finderecipe.feature.presentation.auth.components.GoogleButton
import com.halilkrkn.finderecipe.feature.presentation.auth.components.GradientButton
import com.halilkrkn.finderecipe.feature.presentation.auth.components.PasswordTextField
import com.halilkrkn.finderecipe.ui.theme.Coral
import com.halilkrkn.finderecipe.ui.theme.FloralWhite
import com.halilkrkn.finderecipe.ui.theme.Razzmatazz
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val errorMessage by remember { mutableStateOf("") }

    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.signInState.value
    val googleSignInState = viewModel.googleState.value
    val firebaseAuth: FirebaseAuth = Firebase.auth


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val account = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                scope.launch {
                    viewModel.signInWithGoogle(credentials)
                }
            } catch (apiException: ApiException) {
                print(apiException)
            }
        }

    LaunchedEffect(key1 = googleSignInState.success, key2 = state.isSuccess, key3 = state.isError) {
        scope.launch {
            if (firebaseAuth.currentUser != null || googleSignInState.success != null) {
                Toast.makeText(context, "Sign In Success", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
                navController.navigate(MAIN)
                viewModel.resetSignInState()
            } else if (state.isError.isNotEmpty()) {
                val error = state.isError.ifEmpty { googleSignInState.error }
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(key1 = googleSignInState.error) {
        if (googleSignInState.error.isNotEmpty()) {
            Toast.makeText(context, googleSignInState.error, Toast.LENGTH_SHORT).show()
        }
    }

    val googleSignIn = {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_SECRET_KEY)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        launcher.launch(googleSignInClient.signInIntent)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 20.dp),
                color = Coral,
                strokeWidth = 2.dp,
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
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

                Spacer(modifier = Modifier.height(16.dp))
                GradientButton(
                    nameButton = "Login",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                    onClick = {
                        keyboard?.hide()
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            scope.launch {
                                navController.popBackStack()
                                viewModel.signInWithEmailAndPassword(email, password)
                            }
                        }
                    },
                    state = state.isLoading
                )


                if (googleSignInState.error.isNotEmpty() || state.isError.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red)
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        navController.navigate(AuthRoutes.SignUp.route)
                    }) {
                        Text(
                            text = "Create An Account",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
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
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "or connect with", fontWeight = FontWeight.SemiBold, color = Color.Gray)
                Spacer(modifier = Modifier.padding(16.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    GoogleButton(
                        onClick = {
                            googleSignIn()
                        },
                        state = googleSignInState.loading,
                        text = "Sign In with Google",
                        loadingText = "Signing Up...",
                        iconContentDescription = "Google Icon",
                        buttonColors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent
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
