package com.imandroid.streambox.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCancellationBehavior
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.imandroid.streambox.core.designsystem.theme.AppTheme

private const val SHIMMER_ASSET = "streambox_shimmer.json"

@Composable
fun StreamBoxImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (imageUrl.isNullOrBlank()) {
        ImagePlaceholder(modifier = modifier)
        return
    }

    val context = LocalContext.current
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            ShimmerPlaceholder(
                modifier = Modifier.fillMaxSize()
            )
        },
        error = {
            ImagePlaceholder(
                modifier = Modifier.fillMaxSize(),
                message = "Image unavailable"
            )
        }
    )
}

@Composable
private fun ImagePlaceholder(
    modifier: Modifier,
    message: String? = null
) {
    Box(
        modifier = modifier
            .background(AppTheme.colors.background.tertiary),
        contentAlignment = Alignment.Center
    ) {
        if (message != null) {
            Text(
                text = message,
                style = AppTheme.typography.labelSmall,
                color = AppTheme.colors.text.secondary,
                modifier = Modifier.padding(AppTheme.spacers.xs)
            )
        }
    }
}

@Composable
private fun ShimmerPlaceholder(
    modifier: Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset(SHIMMER_ASSET)
    )
    val animatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        animatable.animate(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            cancellationBehavior = LottieCancellationBehavior.Immediately
        )
    }

    LottieAnimation(
        composition = animatable.composition,
        progress = { animatable.progress },
        modifier = modifier
    )
}
