package com.kuldeep.whatsapp.presentations.userregistrationscreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kuldeep.whatsapp.R
import com.kuldeep.whatsapp.presentations.navigation.Routes
import com.kuldeep.whatsapp.presentations.viewmodels.AuthState
import com.kuldeep.whatsapp.presentations.viewmodels.PhoneAuthViewModel

@Composable
fun AuthScreen(
    navHostController: NavHostController,
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
) {

    val authState by phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = androidx.activity.compose.LocalActivity.current

    var expanded by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("Japan") }
    var countryCode by remember { mutableStateOf("+91") }
    var phoneNumber by remember { mutableStateOf("") }

    var otp by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter your phone number",
            color = colorResource(R.color.teal_200),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Black)) {
                        append("Whatsapp will need to verify your phone number.")
                    }
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Green)) {
                        append("What's")
                    }
                }
            )
        }
        Text(text = "my number", color = Color.Green)
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            TextButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.width(230.dp)) {

                    Text(
                        text = selectedCountry,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown, contentDescription = null,
                        tint = colorResource(R.color.teal_200),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

            }
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 66.dp),
                thickness = 2.dp,
                color = colorResource(R.color.teal_200)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listOf("India", "Japan", "USA", "UK").forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country) },
                        onClick = {
                            selectedCountry = country
                            expanded = false
                        }
                    )
                }
            }
        }

        when (authState) {
            is AuthState.Ideal, is AuthState.Loading, is AuthState.CodeSent -> {
                if (authState is AuthState.CodeSent) {
                    verificationId = (authState as AuthState.CodeSent).verificationId
                }
                if (verificationId == null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(
                            value = countryCode,
                            onValueChange = { countryCode = it },
                            modifier = Modifier.width(70.dp),
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            placeholder = { Text(text = "Phone Number") },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            ),
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if (phoneNumber.isNotEmpty()) {
                                    val fullPhoneNumber = "$countryCode$phoneNumber"
                                    if (activity != null) {
                                        phoneAuthViewModel.sendVerificationCode(
                                            fullPhoneNumber,
                                            activity
                                        )
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter a phone number",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                colorResource(R.color.teal_700)
                            )
                        ) {
                            Text(text = "Send otp")
                        }
                    }
                    if (authState is AuthState.Loading) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CircularProgressIndicator()
                    } else {
                        // otp input field
                        if (verificationId != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "Enter OTP",
                                color = colorResource(R.color.teal_700),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextField(
                                value = otp,
                                onValueChange = { otp = it },
                                placeholder = { Text(text = "Enter OTP") },
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                ),
                            )
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(
                                onClick = {
                                    if (otp.isNotEmpty() && verificationId != null) {
                                        phoneAuthViewModel.verifyCode(otp, context)
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter OTP",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    colorResource(R.color.teal_700)
                                )
                            ) {
                                Text(text = "Verify OTP")
                            }
                            if (authState is AuthState.Loading) {
                                Spacer(modifier = Modifier.height(16.dp))
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }

            is AuthState.Success -> {
                Log.d("PhoneAuth", "Login successfully")
                phoneAuthViewModel.resetAuthState()
                navHostController.navigate(Routes.UserProfileScreen) {
                    popUpTo(Routes.UserRegistrationScreen) {
                        inclusive = true
                    }
                }
            }

            is AuthState.Error -> {
                Log.e("PhoneAuth", "Error: ${(authState as AuthState.Error).message}")
                Toast.makeText(context, (authState as AuthState.Error).message, Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }
}


