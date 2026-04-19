package com.sugarspoon.desafiomobile.feature.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sugarspoon.desafiomobile.ds.theme.MoviesTheme

@Composable
fun BottomNavigationBar(
    selectedItem: HomeNavigationItem,
    onItemSelected: (HomeNavigationItem) -> Unit
) {
    val items = HomeNavigationItem.entries

    NavigationBar {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                NavigationBarItem(
                    modifier = Modifier.fillParentMaxWidth(1f / items.size.coerceAtLeast(1)),
                    selected = selectedItem == item,
                    onClick = { onItemSelected(item) },
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) }
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    MoviesTheme {
        BottomNavigationBar(
            selectedItem = HomeNavigationItem.HOME,
            onItemSelected = {},
        )
    }
}
