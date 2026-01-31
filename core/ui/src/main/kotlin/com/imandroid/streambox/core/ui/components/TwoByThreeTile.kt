package com.imandroid.streambox.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.shape.RoundedCornerShape
import com.imandroid.streambox.core.designsystem.theme.AppTheme

@Composable
fun TwoByThreeTile(
    title: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    showTitle: Boolean = false
) {
    Box(
        modifier = modifier
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(AppTheme.spacers.sm))
            .background(AppTheme.colors.background.secondary)
    ) {
        StreamBoxImage(
            imageUrl = imageUrl,
            contentDescription = title,
            modifier = Modifier.fillMaxSize()
        )

        if (showTitle) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .background(AppTheme.colors.background.primary.copy(alpha = 0.7f))
                    .padding(AppTheme.spacers.xs)
            ) {
                Text(
                    text = title,
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colors.text.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
