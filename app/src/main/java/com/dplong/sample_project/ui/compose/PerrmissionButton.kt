package com.dplong.sample_project.ui.compose

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionButton(
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    title: String,
    permissions: List<String>,
) {
    // Camera permission state
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    val context = LocalContext.current

    Column(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        // Title
        Text(title, modifier = titleModifier)
        // Permission status
        if (multiplePermissionsState.allPermissionsGranted) {
            Text("Granted")
        } else if (multiplePermissionsState.shouldShowRationale) {
            Column {
                Text("Open setting to allow permission")
                Text(
                    "open setting",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            // Intent to open wireless settings screen
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts(
                                        "package",
                                        context.packageName,
                                        null,
                                    )
                                }
                            context.startActivity(intent)
                        },
                    ),
                    style = TextStyle(
                        color = Color.Blue,
                        fontStyle = FontStyle.Italic,
                        textDecoration = TextDecoration.Underline,
                    )
                )
            }
        } else {
            Column {
                Text(
                    "Request permission",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            multiplePermissionsState.launchMultiplePermissionRequest()
                        },
                    ),
                    style = TextStyle(
                        color = Color.Blue,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W800,
                    )
                )
            }
        }
    }
}

/**
 * https://developer.android.com/about/versions/14/changes/partial-photo-video-access
 * https://github.com/google/accompanist/blob/main/sample/src/main/java/com/google/accompanist/sample/permissions/RequestMultiplePermissionsSample.kt
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MediaPermissionButton(
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
) {
    // Camera permission state
    val multiplePermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_VIDEO,
            android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
        )
    )
    val imagePermissionState =
        rememberPermissionState(android.Manifest.permission.READ_MEDIA_IMAGES)
    val videoPermissionState =
        rememberPermissionState(android.Manifest.permission.READ_MEDIA_VIDEO)
    val visualPermissionState =
        rememberPermissionState(android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
    val storagePermissionState =
        rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    val context = LocalContext.current

    Column(
        modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        // Title
        Text("MEDIA", modifier = titleModifier)
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            (imagePermissionState.status.isGranted || videoPermissionState.status.isGranted)
        ) {
            // Full access on Android 13 (API level 33) or higher
            Text("All granted")
        } else if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE &&
            visualPermissionState.status.isGranted
        ) {
            // Partial access on Android 14 (API level 34) or higher
            Column {
                Text(
                    "Choose more",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            multiplePermissionsState.launchMultiplePermissionRequest()
                        },
                    ),
                    style = TextStyle(
                        color = Color.Blue,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W800,
                    ),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Open setting to change permission",
                    modifier = Modifier.clickable(
                        enabled = true,
                        onClick = {
                            // Intent to open wireless settings screen
                            val intent =
                                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = Uri.fromParts(
                                        "package",
                                        context.packageName,
                                        null,
                                    )
                                }
                            context.startActivity(intent)
                        },
                    ),
                    style = TextStyle(
                        color = Color.Blue,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W800,
                    ),
                )
            }
        } else if (
            storagePermissionState.status.isGranted
        ) {
            // Full access up to Android 12 (API level 32)
            Text("All granted")
        } else {
            // Access denied
            Text(
                "Request permission",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = {
                        multiplePermissionsState.launchMultiplePermissionRequest()
                    },
                ),
                style = TextStyle(
                    color = Color.Blue,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W800,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPermissionButton() {
    PermissionButton(
        permissions = listOf(android.Manifest.permission.CAMERA),
        title = "Camera",
    )
}