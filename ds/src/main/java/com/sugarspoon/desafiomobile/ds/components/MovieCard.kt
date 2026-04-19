package com.sugarspoon.desafiomobile.ds.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sugarspoon.desafiomobile.ds.R
import com.sugarspoon.desafiomobile.ds.theme.BackgroundDark

@Composable
fun MovieCard(
    modifier: Modifier,
    title: String? = null,
    url: String? = null,
    badgeText: String? = null,
) {
    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = url,
                    contentDescription = title,
                    modifier = Modifier.height(220.dp).fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(R.drawable.ic_placeholder_empty_movie)
                )
                
                badgeText?.let {
                    Surface(
                        color = BackgroundDark.copy(alpha = 0.7f),
                        modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = badgeText,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            
            Text(
                text = title.orEmpty(),
                color = Color.White,
                modifier = Modifier.padding(8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}