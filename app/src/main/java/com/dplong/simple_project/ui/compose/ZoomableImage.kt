package com.dplong.simple_project.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateRotation
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun ZoomableImageView(
    painter: Painter,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    imageAlign: Alignment = Alignment.Center,
    shape: Shape = RectangleShape,
    maxScale: Float = 1f,
    minScale: Float = 3f,
    contentScale: ContentScale = ContentScale.Fit,
    isRotation: Boolean = false,
    isZoomable: Boolean = true,
    scrollState: ScrollableState? = null,
    onScaleChange: (Float) -> Unit = {},
    onOffsetChange: (Offset) -> Unit = {},
    canPan: Boolean = false,
) {
    val coroutineScope = rememberCoroutineScope()

    val scale = remember { mutableFloatStateOf(1f) }
    val rotationState = remember { mutableFloatStateOf(1f) }
    val offsetX = remember { mutableFloatStateOf(1f) }
    val offsetY = remember { mutableFloatStateOf(1f) }

    Box(
        modifier = Modifier
            .clip(shape)
            .background(backgroundColor)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { /* NADA :) */ },
                onDoubleClick = {
                    if (scale.floatValue >= 2f) {
                        scale.floatValue = 1f
                        offsetX.floatValue = 1f
                        offsetY.floatValue = 1f
                    } else scale.floatValue = 3f
                    onScaleChange(scale.floatValue)
                    onOffsetChange(Offset(x = offsetX.floatValue, y = offsetY.floatValue))
                },
            )
            .pointerInput(Unit) {
                if (isZoomable) {
                    awaitEachGesture {
                        awaitFirstDown()
                        do {
                            val event = awaitPointerEvent()
                            scale.floatValue *= event.calculateZoom()
                            if (scale.floatValue > 1 || canPan) {
                                scrollState?.run {
                                    coroutineScope.launch {
                                        setScrolling(false)
                                    }
                                }
                                val offset = event.calculatePan()
                                offsetX.floatValue += offset.x
                                offsetY.floatValue += offset.y
                                rotationState.floatValue += event.calculateRotation()
                                scrollState?.run {
                                    coroutineScope.launch {
                                        setScrolling(true)
                                    }
                                }
                            } else {
                                scale.floatValue = 1f
                                offsetX.floatValue = 1f
                                offsetY.floatValue = 1f
                            }
                            onScaleChange(scale.floatValue)
                            onOffsetChange(Offset(x = offsetX.floatValue, y = offsetY.floatValue))
                        } while (event.changes.any { it.pressed })
                    }
                }
            }

    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
                .align(imageAlign)
                .graphicsLayer {
                    if (isZoomable) {
                        scaleX = maxOf(maxScale, minOf(minScale, scale.floatValue))
                        scaleY = maxOf(maxScale, minOf(minScale, scale.floatValue))
                        if (isRotation) {
                            rotationZ = rotationState.floatValue
                        }
                        translationX = offsetX.floatValue
                        translationY = offsetY.floatValue
                    }
                }
        )
    }
}

suspend fun ScrollableState.setScrolling(value: Boolean) {
    scroll(scrollPriority = MutatePriority.PreventUserInput) {
        when (value) {
            true -> Unit
            else -> awaitCancellation()
        }
    }
}